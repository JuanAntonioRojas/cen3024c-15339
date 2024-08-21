package cop2805;

import static cop2805.Create.*;
import static cop2805.posForms.*;
import static cop2805.Constants.*;
import static cop2805.posForms.chkNotify;
import static cop2805.Tools.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


public class Search_Old
{
    //  FRAME PROPERTIES

    static String title = "     SEARCH FOR A CLIENT";        //  Title

    //  Position
    static double tpLfX = SCREEN.getWidth() * 1.85;          //  Top Left corner X value
    static double tpLfY = SCREEN.getHeight() * 0.25;         //  Top Left corner Y value


    //  Dimensions
    static double wnWd = 500.0, wnHt = 700.0;  //  Width & Height. Golden ratio

    //  Positioning the window Frame
    static double[] framePos = { tpLfX, tpLfY, wnWd, wnHt};

    //  Positioning the Title
    public static final int[] posTitle = {70, 30, 400, 30, 101};


    //  Photo
    static String pathPhoto = "img/blank.png";




    public Search_Old()
    {
        //  WINDOW FRAME:

        WinFrame frame3 = new WinFrame(title, framePos, LGREEN);


        //  Page Title
        TxtLb lblTitle = new TxtLb(frame3, posTitle, LTBLUE, FNTITLE, LTBLUE, "SEARCH  &  UPDATE  CLIENT");


        //  FORM

        //  Line 1: Name

        TxtLb lblIDTag = new TxtLb(frame3, aLblIDTag, LTBLUE, FNLABEL, LTBLUE, "ID");
        TxtFd txtIDNum = new TxtFd(frame3, aLblIDVal, LTRGRN, FNLABEL, LTBLUE, "");

        TxtLb lblFirst = new TxtLb(frame3, aLblFirst, LTBLUE, FNLABEL, LTBLUE, "First Name");
        TxtFd txtFirst = new TxtFd(frame3, aTxtFirst, LTRGRN, FNLABEL, LTBLUE, "");

        TxtLb lblMiddl = new TxtLb(frame3, aLblMiddl, LTBLUE, FNLABEL, LTBLUE, "MI");
        TxtFd txtMiddl = new TxtFd(frame3, aTxtMiddl, LTRGRN, FNLABEL, LTBLUE, "");

        TxtLb lblLastN = new TxtLb(frame3, aLblLastN, LTBLUE, FNLABEL, LTBLUE, "Last Name");
        TxtFd txtLastN = new TxtFd(frame3, aTxtLastN, LTRGRN, FNLABEL, LTBLUE, "");



        //  Person's Photo
        TxtLb lblPhoto = new TxtLb(frame3, aBtnPhoto, LTBLUE, FNLABEL, LTBLUE, "Photo");

        //  Photo
        ImageLabel iLbPhoto  = new ImageLabel(frame3, pathPhoto, possPhoto);



        //  Line 2: Phone

        TxtLb lblPhon1 = new TxtLb(frame3, aLblPhon1, LTBLUE, FNLABEL, LTBLUE, "Phone 1");
        TxtFd txtPhon1 = new TxtFd(frame3, aTxtPhon1, LTRGRN, FNLABEL, LTBLUE, "");

        TxtLb lblPhon2 = new TxtLb(frame3, aLblPhon2, LTBLUE, FNLABEL, LTBLUE, "Phone 2");
        TxtFd txtPhon2 = new TxtFd(frame3, aTxtPhon2, LTRGRN, FNLABEL, LTBLUE, "");



        //  Line 3: Address

        TxtLb lblAddr  = new TxtLb(frame3, aLblAddrs, LTBLUE, FNLABEL, LTBLUE, "Address");
        TxtFd txtAddrs = new TxtFd(frame3, aTxtAddrs, LTRGRN, FNLABEL, LTBLUE, "");

        TxtLb lblCity  = new TxtLb(frame3, aLablCity, LTBLUE, FNLABEL, LTBLUE, "City");
        TxtFd textCity = new TxtFd(frame3, aTextCity, LTRGRN, FNLABEL, LTBLUE, "");

        TxtLb lblState = new TxtLb(frame3, aLblState, LTBLUE, FNLABEL, LTBLUE, "State");
        TxtFd txtState = new TxtFd(frame3, aTxtState, LTRGRN, FNLABEL, LTBLUE, "");

        TxtLb lblZipCd = new TxtLb(frame3, aLblZipCd, LTBLUE, FNLABEL, LTBLUE, "Zip");
        TxtFd txtZipCd = new TxtFd(frame3, aTxtZipCd, LTRGRN, FNLABEL, LTBLUE, "");



        //  Line 4: Email

        TxtLb lblEmail = new TxtLb(frame3, lablEmail, LTBLUE, FNLABEL, LTBLUE, "Email");
        TxtFd txtEmail = new TxtFd(frame3, textEmail, LTRGRN, FNLABEL, LTBLUE, "");


        //  Date of Birth Spinners
        TxtLb lblDOB = new TxtLb(frame3, lDateOBth, LTBLUE, FNLABEL, LTBLUE, "Date of Birth");
        //  Month
        String[] aMonth = { "", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        SpnTxt spinrMon = new SpnTxt(frame3, spinerDay, LTRGRN, FNLABEL, LTBLUE, aMonth, aMonth[0]);
        //  Day
        String[] aDay = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
        SpnTxt spinrDay = new SpnTxt(frame3, spinerMon, LTRGRN, FNLABEL, LTBLUE, aDay, aDay[0]);
        //  Year
        TxtFd textYear = new TxtFd(frame3, text_Year, LTRGRN, FNLABEL, LTBLUE, "");



        //  Line 5: Gender

        //  Gender
        ButtonGroup gender = new ButtonGroup();
        Radio radiMale = new Radio(frame3, gender, radioMale, LTBLUE, FNLABEL, LTBLUE, false, "Male");
        Radio radFemal = new Radio(frame3, gender, radFemale, LTBLUE, FNLABEL, LTBLUE, false, "Female");


        //  Marital Status Check Boxes
        ButtonGroup marStatus = new ButtonGroup();
        Radio chSingle = new Radio(frame3, marStatus, radSingle, LTBLUE, FNLABEL, LTBLUE, false, "Single");
        Radio chMaried = new Radio(frame3, marStatus, radMaried, LTBLUE, FNLABEL, LTBLUE, false, "Married");
        Radio chDivord = new Radio(frame3, marStatus, radSepard, LTBLUE, FNLABEL, LTBLUE, false, "Separated");

        Radio chWidowd = new Radio(frame3, marStatus, radWidowd, LTBLUE, FNLABEL, LTBLUE, false, "Widow");
        Radio chSepard = new Radio(frame3, marStatus, radDivord, LTBLUE, FNLABEL, LTBLUE, false, "Divorced");
        Radio chCmonLw = new Radio(frame3, marStatus, radCmonLw, LTBLUE, FNLABEL, LTBLUE, false, "Com.Law Marr.");



        //  Line 6: Password

        TxtLb lblPswd  = new TxtLb(frame3, lblPasswd, LTBLUE, FNLABEL, LTBLUE, "Password");
        TxtFd pswPaswd = new TxtFd(frame3, pswPasswd, LTRGRN, FNLABEL, LTBLUE, "");

        TxtLb lblConf  = new TxtLb(frame3, lblConfir, LTBLUE, FNLABEL, LTBLUE, "Confirm");
        TxtFd pswConfr = new TxtFd(frame3, pswConfir, LTRGRN, FNLABEL, LTBLUE, "");



        //  Notification Check Boxes
        ButtonGroup notify = new ButtonGroup();
        TxtLb lblNotif = new TxtLb(frame3, lblNotify, LTBLUE, FNLABEL, LTBLUE, "Receive");
        ChkBx chkNotif = new ChkBx(frame3, notify, chkNotify, FNLABEL, LTBLUE, false, "Notifications");



        //  Line 7:  Text Area for additional notes

        TxtLb lblNotes = new TxtLb(frame3, aLblNotes, LTBLUE, FNLABEL, LTBLUE, "Notes (Optional):");
        TextA txtNotes = new TextA(frame3, aTxtNotes, LTRGRN, FNLABEL, LTBLUE, "");




        //  Line 8:  LONG STRING TO BE SUBMITTED TO THE SERVER

        int[] aBtnSubmit = {200, 580, 90, 40,  17};

        Buttn btnSubmit = new Create.Buttn(frame3, aBtnSubmit, LTBLUE, FNLABEL, LTBLUE, "Search_Old", new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder data;
                try {
                    //  COLLECT ALL THE DATA TO BE SENT TO THE SERVER

                    //  create the data collecting string
                    data = new StringBuilder();


                    if (!txtFirst.getText().equals("")) {
                        String First = txtFirst.getText().trim();
                        data.append("`First` LIKE '%").append(First).append("&'");
                    }
                    if (!txtMiddl.getText().equals("")) {
                        String Middl = txtMiddl.getText().trim();
                        data.append(" AND `Middl` LIKE '%").append(Middl).append("&'");
                    }
                    if (!txtLastN.getText().equals("")) {
                        String LastN = txtLastN.getText().trim();
                        data.append(" AND `LastN` LIKE '%").append(LastN).append("&'");
                    }

                    if (!txtPhon1.getText().equals("")) {
                        String Phon1 = txtPhon1.getText().trim();
                        data.append(" AND `Phon1` LIKE '%").append(Phon1).append("&'");
                    }
                    if (!txtPhon2.getText().equals("")) {
                        String Phon2 = txtPhon2.getText().trim();
                        data.append(" AND `Phon2` LIKE '%").append(Phon2).append("&'");
                    }

                    if (!txtAddrs.getText().equals("")) {
                        String Addrs = txtAddrs.getText().trim();
                        data.append(" AND `Addrs` LIKE '%").append(Addrs).append("&'");
                    }
                    if (!textCity.getText().equals("")) {
                        String City = textCity.getText().trim();
                        data.append(" AND `City` LIKE '%").append(City).append("&'");
                    }
                    if (!txtState.getText().equals("")) {
                        String State = txtState.getText().trim();
                        data.append(" AND `State` LIKE '%").append(State).append("&'");
                    }
                    if (!txtZipCd.getText().equals("")) {
                        String ZipCd = txtZipCd.getText().trim();
                        data.append(" AND `ZipCd` LIKE '%").append(ZipCd).append("&'");
                    }

                    if (!txtEmail.getText().equals("")) {
                        String Email = txtEmail.getText().trim();
                        data.append(" AND `Email` LIKE '%").append(Email).append("&'");
                    }

                    if (!spinrMon.getSelectedItem().equals("")) {
                        String SpMon = spinrMon.getSelectedItem();
                        data.append(" AND `SpMon` LIKE '%").append(SpMon).append("&'");
                    }
                    if (!spinrDay.getSelectedItem().equals("")) {
                        String SpDay = spinrDay.getSelectedItem();
                        data.append(" AND `SpDay` LIKE '%").append(SpDay).append("&'");
                    }
                    if (!textYear.getText().equals("")) {
                        String YearB = textYear.getText().trim();
                        data.append(" AND `YearB` LIKE '%").append(YearB).append("&'");
                    }


                    String Gendr = getSelectedRadioButtonText(gender.toString());
                    if ((gender.equals(Gendr))) {
                        data.append(" AND `Gendr` LIKE '%").append(Gendr).append("&'");
                    } else {
                        data.append("");
                    }


                    String mStatus = getSelectedRadioButtonText(marStatus.toString());
                    if ((marStatus.equals(mStatus))) {
                        data.append(" AND `mStat` LIKE '%").append(mStatus).append("&'");
                    } else {
                        data.append("");
                    }


                    // Get the selected checkbox text
                    String cNotify = ChkBx.getSelectedCheckboxText("notify");
                    if ((chkNotify.equals(cNotify))) {
                        data.append(" AND `Notif` LIKE '%").append(cNotify).append("&'");
                    } else {
                        data.append("");
                    }

                    //  The long string we're sending to the server
                    String DataSent = data.toString();

                    //  end of string data



                    //  SERVER CLIENT CONNECTION

                    Socket client = new Socket(HOST, PORTCLIENT);

                    //  Tell cmd interface that we're connected.
                    prtln("Connected to Server.");


                    //  PREPARE TO SEND AND RECEIVE, TO AND FROM SERVER. Setting up the input and output gateways
                    BufferedReader brINPUT = new BufferedReader(new InputStreamReader(client.getInputStream()));
                    PrintWriter pwOUTPUT = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);


                    //  SEND A 'SEARCH' REQUEST, TO SET UP THE SERVER
                    pwOUTPUT.println("search,");


                    //  SEND DATA TO THE SERVER
                    pwOUTPUT.println(DataSent);
                    prtln("Sent To Server the following data:\n" + DataSent);


                    //  SHOW/WRITE THE SERVER RESPONSE IN THE FORM
                    String serverResponse = brINPUT.readLine();


                    /*
                    THE FOLLOWING Code IS WRONG.
                    WHAT I NEED TO DO IS CREATE A TABLE WITH THE RETURNED RESULTS
                    */

                    //  EXTRACT CSV DATA FROM THE SERVER'S STRING
                    String[] values = serverResponse.split(",");


                    //  PLACE CSV DATA VALUES IN EACH CORRESPONDING BOX

                    //  Name & photo
                    txtIDNum.setText(values[0]);
                    txtFirst.setText(values[1]);
                    txtMiddl.setText(values[2]);
                    txtLastN.setText(values[3]);
                    iLbPhoto.setText(values[4]);
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
                    setSelectedRadioButton(gender, values[15]);
                    setSelectedRadioButton(marStatus, values[16]);

                    pswPaswd.setText(values[17]);
                    pswConfr.setText(values[18]);

                    // Set selection in the Notify checkbox

                    if ((values[19].equals("Notifications"))) {
                        chkNotif.setSelected(true);
                    } else {
                        chkNotif.setSelected(false);
                    }


                    // Set the selected notes in the DB
                    txtNotes.setText(values[20]);

                    //  Close the connection
                    pwOUTPUT.close();
                    client.close();

                    // Optionally, display a message indicating the data has been sent
                    prtln("Data sent to the server: " + data.toString());

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
        });  //  end of Submit button



        //  RESET

        int[] aBtnReset  = {70, 580, 90, 40,  16};

        Create.Buttn btnReset = new Create.Buttn(frame3, aBtnReset, LTGRAY, FNLABEL, LTBLUE, "Reset", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtFirst.clear();
                txtMiddl.clear();
                txtLastN.clear();
                txtPhon1.clear();
                txtPhon2.clear();
                txtAddrs.clear();
                textCity.clear();
                txtState.clear();
                txtZipCd.clear();
                txtEmail.clear();
                pswPaswd.clear();
                pswConfr.clear();
                txtNotes.clear();
            }
        });


        //  UPDATE

        int[] aBtnUpdate  = {330, 580,  90, 40,  16};

        Create.Buttn btnUpdate = new Create.Buttn(frame3, aBtnUpdate, LTGRAY, FNLABEL, LTBLUE, "Update", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { }
        });
    }  //  end of public Search_Old() constructor

    public static void main(String[] args) {
        Search_Old newSearch = new Search_Old();
    }

}























