package cop2805;

import static cop2805.Action.performReset;
import static cop2805.Create.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import static cop2805.Constants.*;
//import static cop2805.Constants.client;
import static cop2805.Tools.logEvent;

public class AddNew
{
    public static class btnAddNew implements ActionListener
    {

        //  Adding the necessary variables
        private WinFrame frame;
        private SpnTxt spnTitle;
        private TxtFd txtFirst;
        private TxtFd txtMiddl;
        private TxtFd txtLastN;
        private TxtFd txtPhon1;
        private TxtFd txtPhon2;
        private TxtFd txtAddrs;
        private TxtFd textCity;
        private TxtFd txtState;
        private TxtFd txtZipCd;
        private TxtFd txtEmail;
        private SpnTxt spinrMon;
        private SpnTxt spinrDay;
        private TxtFd textYear;
        private ButtonGroup grpGender;
        private ButtonGroup grpStatus;
        private TxtFd pswPaswd;
        private TxtFd pswConfr;
        private ButtonGroup grpNotify;
        private TextA txtNotes;


        public btnAddNew(WinFrame frame, SpnTxt spnTitle, TxtFd txtFirst, TxtFd txtMiddl, TxtFd txtLastN, TxtFd txtPhon1,
                         TxtFd txtPhon2, TxtFd txtAddrs, TxtFd textCity, TxtFd txtState, TxtFd txtZipCd, TxtFd txtEmail,
                         SpnTxt spinrMon, SpnTxt spinrDay, TxtFd textYear, ButtonGroup grpGender, ButtonGroup grpStatus,
                         TxtFd pswPaswd, TxtFd pswConfr, ButtonGroup grpNotify, TextA txtNotes)
        {
            // Initialize variables
            this.frame = frame;
            this.spnTitle = spnTitle;
            this.txtFirst = txtFirst;
            this.txtMiddl = txtMiddl;
            this.txtLastN = txtLastN;
            this.txtPhon1 = txtPhon1;
            this.txtPhon2 = txtPhon2;
            this.txtAddrs = txtAddrs;
            this.textCity = textCity;
            this.txtState = txtState;
            this.txtZipCd = txtZipCd;
            this.txtEmail = txtEmail;
            this.spinrMon = spinrMon;
            this.spinrDay = spinrDay;
            this.textYear = textYear;
            this.grpGender = grpGender;
            this.grpStatus = grpStatus;
            this.pswPaswd = pswPaswd;
            this.pswConfr = pswConfr;
            this.grpNotify = grpNotify;
            this.txtNotes = txtNotes;
        }



        @Override
        public void actionPerformed(ActionEvent e)
        {

            //  COLLECT ALL THE DATA, TO BE SENT TO THE SERVER

            String Title = spnTitle.getSelectedItem();
            String First = txtFirst.getText().trim();
            String Middl = txtMiddl.getText().trim();
            String LastN = txtLastN.getText().trim();
            //String Photo = txtPhoto.getText();

            String Phon1 = txtPhon1.getText().trim();
            String Phon2 = txtPhon2.getText().trim();

            String Addrs = txtAddrs.getText().trim();
            String City  = textCity.getText().trim();
            String State = txtState.getText().trim();
            String ZipCd = txtZipCd.getText().trim();

            String Email = txtEmail.getText().trim();
            String SpMon = spinrMon.getSelectedItem();
            String SpDay = spinrDay.getSelectedItem();
            String YearB = textYear.getText().trim();

            String Gendr = getSelectedRadioButtonText(grpGender);
            String Marit = getSelectedRadioButtonText(grpStatus);

            String Paswd = pswPaswd.getText().trim();
            String Confr = pswConfr.getText().trim();

            String Notif = getSelectedRadioButtonText(grpNotify);

            String Notes = txtNotes.getText().trim();



            //  LONG STRING TO BE SUBMITTED TO THE SERVER
            //      (empty one for photo after LastN)
            String SendToSvr  = "null, " + "'" + Title + "', '" + First + "', '" + Middl + "', '" + LastN + "', '', '" +
                    Phon1 + "', '" + Phon2 + "', '" + Addrs + "', '" + City  + "', '" + State + "', '" + ZipCd + "', '" +
                    Email + "', '" + SpMon + "', '" + SpDay + "', '" + YearB + "', '" + Gendr + "', '" + Marit + "', '" +
                    Paswd + "', '" + Confr + "', '" + Notif + "', '" + Notes + "'";



            //  SERVER CLIENT CONNECTION
            try
            {
                client = new Socket(HOST, PORTCLIENT);

                //  Send and receive to and from server. Setting up the Input and Output properly
                BufferedReader brINPUT = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter pwOUTPUT = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

                //  Get the port on the client's side of the connection
                int clientPort = client.getPort();

                //  Tell cmd interface that we're connected.
                logEvent("Connected to Server on port: " + clientPort + ".\n");


                //  SEND THE GATHERED DATA TO THE SERVER
                pwOUTPUT.println(SendToSvr);
                logEvent("This client has sent the following data to the server:\n" + SendToSvr );
                logEvent("The server responded with:\n" + brINPUT.readLine() );


                //  SHOW/WRITE THE SERVER RESPONSE IN A POPUP MESSAGE
                String serverResponse = brINPUT.readLine();

                Object[] options = {"Add more", "Quit"};

                int response = JOptionPane.showOptionDialog(null, serverResponse + " was registered!",
                        "   REGISTRATION CONFIRMATION", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        null, options, options[0] );


                //  RESET OR CLOSE THE COMMUNICATION.

                //  Empty the form to start again
                if (response == 0) {
                    performReset( txtFirst, txtMiddl, txtLastN, txtPhon1, txtPhon2, txtAddrs, textCity, txtState, txtZipCd,
                            txtEmail, spinrMon, spinrDay, textYear, null, null, pswPaswd, pswConfr, null, txtNotes);
                }
                else {  //  When the user chooses the "Quit" option
                    logEvent("Disconnecting from server.");
                    pwOUTPUT.println("q"); //  Send a "q" to quit the server:
                    brINPUT.close();
                    client.close();        // Close the communication after clicking OK.
                    frame.dispose();       // Close the JFrame
                    System.exit(0);
                }
            }
            catch(IOException eIO) { eIO.printStackTrace(); }
        }





        //  Mapping works best when we have multiple ButtonGroup objects stored in a map and need to get their selections dynamically.
        Map<String, ButtonGroup> buttonGroupMap = new HashMap<>();


        //  This method iterates through the group buttons, and returns the text of the selected button.
        public String getSelectedRadioButtonText(ButtonGroup group) {
            // Get the model of the selected button
            ButtonModel selectedModel = group.getSelection();

            // Return the text of the selected button, if one is selected
            if (selectedModel != null) {
                for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
                    AbstractButton button = buttons.nextElement();
                    if (button.getModel() == selectedModel) {
                        return button.getText();
                    }
                }
            }
            return null; // If no button is selected
        }
    };  //  end of Submit button
}


























