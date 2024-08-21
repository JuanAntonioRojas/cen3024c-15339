package cop2805;
import static cop2805.Values.*;
import static cop2805.Create.*;


import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import java.net.Socket;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.json.JSONObject;




public class Client {
//  FRAME PROPERTIES

    static String title = "  FINAL PROJECT CLIENT 2: FEEDBACK FORM.";           //  Title
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();  //  Position
    static int topLft_X = (int) (screenSize.getWidth() * 0.4);          //  Top Left corner X value
    static int topLft_Y = (int) (screenSize.getHeight() * 0.4);         //  Top Left corner Y value
    static int winWid = 700, winHgt = winWid;     //  Dimensions:  Width & Height. Golden ratio
    static int[] frame_X_Y_W_H = {topLft_X, topLft_Y, winWid, winHgt};  //  Positioning the window FramE
    static Color bgLtBlue = new Color(150, 220, 250);           //  Frame color:

    //  Fonts
    static Font fntTitle = new Font("Arial", Font.BOLD, 36);  // Font family: Arial, Style: Bold, Size: 24
    static Font fntLabel = new Font("Arial", Font.BOLD, 18);  // Font family: Arial, Style: Bold, Size: 24
    static Color fntColor = new Color(0, 0, 128);                //  Font color: Navy Blue




    //  POSITIONING LABEL, TEXT FIELDS, BUTTON.

    //  (grid system: NONE):    X,   Y,  Wd, Ht, Tab#
    static int[] aLblTitle = {180,  20, 400, 60, 101};
    static int[] aLblName  = { 50, 100, 150, 30, 102};
    static int[] aTxtName  = {240, 100, 300, 30,   1};
    static int[] aLblEmail = { 50, 160, 150, 30, 103};
    static int[] aTxtEmail = {240, 160, 300, 30,   2};
    static int[] aChkPromo = {240, 190, 300, 30,   3};
    static int[] aLbAgeGrp = { 50, 250, 150, 30, 104};
    static int[] aSlAgeGrp = {240, 250, 100, 30,   4};
    static int[] aLblRatng = { 50, 310, 100, 30, 105};
    static int[] aRadRatn1 = {240, 310,  40, 30,   5};
    static int[] aRadRatn2 = {300, 310,  40, 30,   6};
    static int[] aRadRatn3 = {360, 310,  40, 30,   7};
    static int[] aRadRatn4 = {420, 310,  40, 30,   8};
    static int[] aRadRatn5 = {480, 310,  40, 30,   9};
    static int[] aLbFeedBk = { 50, 370, 190, 30, 110};
    static int[] aTAFeedBk = {240, 370, 300,140,  10};



    //  Buttons
    static int[] aBtnSubmt = {230, 560, 100,  40,   9};
    static int[] aBtnReset = {380, 560, 100,  40,  10};





    //  In/Out Socket Communications
    private static BufferedReader input;
    private static PrintWriter output;




    //  CREATE THE COMPONENTS

    //  Frame
    static Create.Frame frame1 = new Create.Frame(title, frame_X_Y_W_H, bgLtBlue);

    //  Form Title
    TxtLb lblTitle = new TxtLb(frame1, aLblTitle, bgLtBlue, fntTitle, fntColor, "FEEDBACK  SURVEY");


    //  Name
    TxtLb lblName = new TxtLb(frame1, aLblName, bgLtBlue, fntLabel, fntColor, "Name:");
    private static final TxtFd txtName = new TxtFd(frame1, aTxtName, fntLabel, fntColor, "");


    //  Email
    TxtLb lblEmail = new TxtLb(frame1, aLblEmail, bgLtBlue, fntLabel, fntColor, "Email Address:");
    private static final TxtFd txtEmail = new TxtFd(frame1, aTxtEmail, fntLabel, fntColor, "");


    //  Promotional Check Box
    private static final ChkBox chkPromo = new ChkBox(frame1, aChkPromo, bgLtBlue, "Receive promotional emails", true, "RecEmail");



    //  Select Age Group
    TxtLb lblAgeGr = new TxtLb(frame1, aLbAgeGrp, bgLtBlue, fntLabel, fntColor, "Age Group:");
    static String[] aAgeGrp = {" 60 plus", " 50 - 59", " 40 - 49", " 30 - 39", " 20 - 29",  " 0 - 20"};
    private static final Select selAgeGrp = new Select(frame1, aSlAgeGrp, fntLabel, fntColor, aAgeGrp, aAgeGrp[4]);


    //  Rating
    TxtLb lblRatng = new TxtLb(frame1, aLblRatng, bgLtBlue, fntLabel, fntColor, "Rating:");
    Radio radRate1 = new Radio(frame1, aRadRatn1, bgLtBlue, "1", fntLabel, fntColor, false, "Rating");
    Radio radRate2 = new Radio(frame1, aRadRatn2, bgLtBlue, "2", fntLabel, fntColor, false, "Rating");
    Radio radRate3 = new Radio(frame1, aRadRatn3, bgLtBlue, "3", fntLabel, fntColor, false, "Rating");
    Radio radRate4 = new Radio(frame1, aRadRatn4, bgLtBlue, "4", fntLabel, fntColor, false, "Rating");
    Radio radRate5 = new Radio(frame1, aRadRatn5, bgLtBlue, "5", fntLabel, fntColor, true,  "Rating");


    //  Feedback
    TxtLb lbFeedBk = new TxtLb(frame1, aLbFeedBk, bgLtBlue, fntLabel, fntColor, "Feedback (Optional):");
    static TextA TAFeedBk = new TextA(frame1, aTAFeedBk, fntLabel, fntColor, "");



    //  Buttons
    Buttn btnSubmit = new Buttn(frame1, aBtnSubmt, fntLabel, fntColor, "Submit", new SubmitData());
    Buttn btnReset  = new Buttn(frame1, aBtnReset, fntLabel, fntColor, "Reset",  new Reset());








    public Client()
    {
        //  Server Client Connection
        try {
            client = new Socket(HOST, PORT);

            //  Send and receive to and from server. Setting up the Input and Output properly
            input  = new BufferedReader(new InputStreamReader(client.getInputStream()));
            output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

            //  Tell cmd interface that we're connected.
            prtLn("Connected to Server.");
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    private static class SubmitData implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //  Get the Text Fields content.
                String inpName  = txtName.getText();
                String inpEmail = txtEmail.getText();

                //  Get the CheckBox selection
                String promo = ChkBox.getSelectedCheckboxText("RecEmail");

                //  Get the Dropdown List selection
                String ageGrp = selAgeGrp.getSelectedItem();

                //  Get the Radio Buttons selection
                int rating = Integer.parseInt(getSelectedRadioButtonText("Rating"));

                //  Get the Text Area content.
                String feedback = TAFeedBk.getText();

                //  Send to server
                SendFormData(inpName, inpEmail, promo, ageGrp, rating, feedback);
            }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }



    public static void SendFormData(String name, String email, String promo, String ageGroup, int rating, String feedback) throws IOException
    {
        //  Prepare the JSON (key:value pairs) array to be sent
        JSONObject json = new JSONObject();
        json.put("name",    name);
        json.put("email",   email);
        json.put("promo",   promo);
        json.put("ageGroup",ageGroup);
        json.put("rating",  rating);
        json.put("feedback",feedback);


        //  SEND AWAY THIS JSON TO THE SERVER:
        output.println(json);


        //  READ RESPONSE FROM THE SERVER
        String serverResponse = input.readLine();
        //  This is the server response code in the server:
        //  String response = String.format("Name: %s\nEmail: %s\nPromo: %s\nAge Group: %s\nRating: %d\nFeedback: %s",
        //      name, email, promo, ageGroup, rating, feedback);


        //  SHOW/WRITE THE SERVER RESPONSE IN A POPUP MESSAGE
        int response = javax.swing.JOptionPane.showConfirmDialog(null, "Thank you for your valuable feedback!\n\n" +
                serverResponse, "Server Response", javax.swing.JOptionPane.DEFAULT_OPTION);

        if (response == javax.swing.JOptionPane.OK_OPTION) {
            Close();
        }

    }


    private class Reset implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the Text Fields to blank
            txtName.clearText();
            txtEmail.setText("");

            // Set the Text Area content to blank
            TAFeedBk.setText("");
        }
    }


    private static void Close() throws IOException {
        prtLn("Good bye server.");
        //  Send a "q" to quit the server:
        output.println("q");
        input.close();
        client.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        new Client();
    }
}
