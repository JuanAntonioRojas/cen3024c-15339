package cop2805;

import static cop2805.Constants.*;
import static cop2805.Create.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import static cop2805.Tools.*;

public class Update {
    public static class btnSearch implements ActionListener
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
        private SpnTxt spnMonth;
        private SpnTxt spinrDay;
        private TxtFd textYear;
        private ButtonGroup radGendr;
        private ButtonGroup radMarSt;
        private ButtonGroup radNotif;
        private TextA txtANote;

        public btnSearch(WinFrame frame, SpnTxt spnTitle, TxtFd txtFirst, TxtFd txtMiddl, TxtFd txtLastN, TxtFd txtPhon1, TxtFd txtPhon2, TxtFd txtAddrs,
                         TxtFd textCity, TxtFd txtState, TxtFd txtZipCd, TxtFd txtEmail, SpnTxt spinrMon, SpnTxt spinrDay, TxtFd textYear,
                         ButtonGroup radGender, ButtonGroup radMarSta, TxtFd pswPaswd,  TxtFd pswConfr, ButtonGroup radNotif)
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
            this.spnMonth = spinrMon;
            this.spinrDay = spinrDay;
            this.textYear = textYear;
            this.radGendr = radGender;
            this.radMarSt = radMarSta;
            this.radNotif = radNotif;
        }


        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder data;

            int values = 0;  //  counter for appending " AND "

            try {
                //  COLLECT ALL THE DATA TO BE SENT TO THE SERVER

                //  create the data collecting string
                data = new StringBuilder("WHERE ");


                if (!txtFirst.getText().equals("")) {
                    String First = txtFirst.getText().trim();
                    data.append("`First` LIKE '%").append(First).append("%'");
                    values++;
                }
                if (!txtMiddl.getText().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String Middl = txtMiddl.getText().trim();
                    data.append("`Middl` LIKE '%").append(Middl).append("%'");
                    values++;
                }
                if (!txtLastN.getText().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String LastN = txtLastN.getText().trim();
                    data.append("`LastN` LIKE '%").append(LastN).append("%'");
                    values++;
                }

                if (!txtPhon1.getText().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String Phon1 = txtPhon1.getText().trim();
                    data.append("`Phon1` LIKE '%").append(Phon1).append("%'");
                    values++;
                }
                if (!txtPhon2.getText().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String Phon2 = txtPhon2.getText().trim();
                    data.append("`Phon2` LIKE '%").append(Phon2).append("%'");
                    values++;
                }

                if (!txtAddrs.getText().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String Addrs = txtAddrs.getText().trim();
                    data.append("`Addrs` LIKE '%").append(Addrs).append("%'");
                    values++;
                }
                if (!textCity.getText().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String City = textCity.getText().trim();
                    data.append("`City` LIKE '%").append(City).append("%'");
                    values++;
                }
                if (!txtState.getText().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String State = txtState.getText().trim();
                    data.append("`State` LIKE '%").append(State).append("%'");
                    values++;
                }
                if (!txtZipCd.getText().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String ZipCd = txtZipCd.getText().trim();
                    data.append("`ZipCd` LIKE '%").append(ZipCd).append("%'");
                    values++;
                }

                if (!txtEmail.getText().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String Email = txtEmail.getText().trim();
                    data.append("`Email` LIKE '%").append(Email).append("%'");
                    values++;
                }

                if (!spnMonth.getSelectedItem().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String SpMon = spnMonth.getSelectedItem();
                    data.append("`SpMon` LIKE '%").append(SpMon).append("%'");
                    values++;
                }

                if (!spinrDay.getSelectedItem().equals("0")) {
                    if (values > 0) { data.append(" AND "); }
                    String SpDay = spinrDay.getSelectedItem();
                    data.append("`SpDay` LIKE '%").append(SpDay).append("%'");
                    values++;
                }

                if (!textYear.getText().equals("")) {
                    if (values > 0) { data.append(" AND "); }
                    String YearB = textYear.getText().trim();
                    data.append("`YearB` LIKE '%").append(YearB).append("%'");
                    values++;
                }


                String Gendr = getSelectedRadioButtonText(radGendr.toString());
                if (radGendr.equals(Gendr)) {
                    if (values > 0) { data.append(" AND "); }
                    data.append("`Gendr` LIKE '%").append(Gendr).append("&'");
                } else {
                    data.append("");
                }


                String mStatus = getSelectedRadioButtonText(radMarSt.toString());
                if (radMarSt.equals(mStatus)) {
                    if (values > 0) { data.append(" AND "); }
                    data.append("`mStat` LIKE '%").append(mStatus).append("&'");
                    values++;
                } else {
                    data.append("");
                }


                // Get the selected checkbox text
                String cNotify = getSelectedRadioButtonText(radNotif.toString());
                if ((radNotif.equals(cNotify))) {
                    if (values > 0) { data.append(" AND "); }
                    data.append("`Notif` LIKE '%").append(cNotify).append("%'");
                } else {
                    data.append("");
                }

                //  The long string we're sending to the server
                String Data2BSent = data.toString();

                //  -> END OF STRING DATA





                //  SERVER - CLIENT  CONNECTION

                Socket client = new Socket(HOST, PORTCLIENT);

                //  Get the port on the client's side of the connection
                int clientPort = client.getPort();

                //  Tell cmd interface that we're connected.
                logEvent(" ->  Connected to Server on port: " + clientPort + ".\n");




                //  PREPARE TO SEND AND RECEIVE, TO AND FROM SERVER

                //  Send and receive to and from server. Setting up the Input and Output properly
                BufferedReader brINPUT = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter pwOUTPUT = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);


                //  SEND DATA TO THE SERVER
                pwOUTPUT.println(Data2BSent);


                //  SHOW/WRITE THE SERVER RESPONSE IN THE FORM
                logEvent("Seeking from the Server a user with the following data: " + Data2BSent);

                //  What the server sent back:
                String back = brINPUT.readLine();
                logEvent(back);

                /*
                THE FOLLOWING Code IS WRONG.
                WHAT I NEED TO DO IS CREATE A TABLE WITH THE RETURNED RESULTS
                */

                /*/  EXTRACT CSV DATA FROM THE SERVER'S STRING
                String[] values = serverResponse.split(",");


                //  PLACE CSV DATA VALUES IN EACH CORRESPONDING BOX

                //  Name & photo
                //txtIDNum.setText(values[0]);
                txtFirst.setText(values[1]);
                txtMiddl.setText(values[2]);
                txtLastN.setText(values[3]);
                //iLbPhoto.setText(values[4]);
                //  Phones
                txtPhon1.setText(values[5]);
                txtPhon2.setText(values[5]);
                //  Address & email
                txtAddrs.setText(values[6]);
                textCity.setText(values[7]);
                txtState.setText(values[8]);
                txtZipCd.setText(values[10]);
                txtEmail.setText(values[11]);

                // Set the selected Date of Birth
                spinrMon.setValue(values[12]);
                spinrDay.setValue(values[13]);
                textYear.setText(values[14]);

                // Set the selected radio button
                //setSelectedRadioButton(gender, values[15]);
                //setSelectedRadioButton(marStatus, values[16]);

                pswPaswd.setText(values[17]);
                pswConfr.setText(values[18]);

                // Set selection in the Notify checkbox

                if ((values[19].equals("Notifications"))) {
                    //chkNotif.setSelected(true);
                } else {
                    //chkNotif.setSelected(false);
                }


                // Set the selected notes in the DB
                txtNotes.setText(values[20]);
                */



                //  CLOSE THE CONNECTION
                pwOUTPUT.close();
                client.close();

            }
            catch (Exception ex) { ex.printStackTrace(); }
        };

        //  Set the selected radio button to the received value from the DB's long string ("values[]")
        public static void setSelectedRadioButton(ButtonGroup group, String value) {
            // Create a list from the ButtonGroup elements
            List<AbstractButton> buttons = new ArrayList<>();
            for (Enumeration<AbstractButton> e = group.getElements(); e.hasMoreElements();) {
                buttons.add(e.nextElement());
            }

            // Use a for loop with index to iterate through the list
            for (int i = 0; i < buttons.size(); i++) {
                AbstractButton button = buttons.get(i);
                if (button.getText().equalsIgnoreCase(value)) {
                    button.setSelected(true);
                    break;
                }
            }
        }
    };  //  end of Submit button
}



































