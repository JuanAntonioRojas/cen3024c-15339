package cop2805;

import static cop2805.Create.*;
import static cop2805.Constants.*;
import static cop2805.Tools.*;
import static cop2805.posForms.*;
import static cop2805.Action.*;
import static cop2805.AddNew.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

public class Add
{
    //  Frame title
    static String frameTitle = "     CLIENT    REGISTRATION";

    //  Positioning the window Frame

    //   Top Left corner X and Y values
    static double tpLfX = SCREEN.getWidth() * 1.54;
    static double tpLfY = SCREEN.getHeight() * 0.3;

    //  Dimensions
    static double wnWd = 500.0, wnHt = 700.0;  //  Width & Height. Golden ratio
    static double[] framePos = { tpLfX, tpLfY, wnWd, wnHt};

    static Color bgrColor = LTBLUE;
    static Color fldColor = VLBLUE;
    static Color fntColor = NAVYBL;
    static Color colPhoto = LTBLUE;



    //  WINDOW FRAME:


    WinFrame frame1 = new WinFrame(frameTitle, framePos, bgrColor);


    //  Page Title
    static String pageTitle = "CLIENT REGISTRATION";

    //  Position Title
    static int[] posPageTitle = {100, 30, 400, 30, 101};
    TxtLb lblTitle = new TxtLb(frame1, posPageTitle, bgrColor, FNTITLE, fntColor, pageTitle);


    //  FORM

    //  Line 1

    //TxtLb lblIDTag = new TxtLb(frame1, aLblIDTag, bgrColor, FNLABEL, fntColor, "ID");
    //TxtLb lblIDNum = new TxtLb(frame1, aLblIDVal, bgrColor, FNLABEL, fntColor, "");

    //  Title
    TxtLb lblPTitl = new TxtLb(frame1, aLblTitle, bgrColor, FNLABEL, fntColor, "Title");  //  Personal Titles
    String[] aTitl = {"", "Mr.", "Ms.", "Dr.", "Prof.", "Rev.", "Eng.", "Esq.", "Hon.", "Adm.", "Gen.", "Col.", "Maj.", "Cmdr.", "Capt.", "Lt."};
    SpnTxt spnTitle = new SpnTxt(frame1, aTxtTitle, fldColor, FNLABEL, fntColor, aTitl, aTitl[0]);

    //  Name

    TxtLb lblFirst = new TxtLb(frame1, aLblFirst, bgrColor, FNLABEL, fntColor, "First Name");
    TxtFd txtFirst = new TxtFd(frame1, aTxtFirst, fldColor, FNLABEL, fntColor, "");

    TxtLb lblMiddl = new TxtLb(frame1, aLblMiddl, bgrColor, FNLABEL, fntColor, "MI");
    TxtFd txtMiddl = new TxtFd(frame1, aTxtMiddl, fldColor, FNLABEL, fntColor, "");

    TxtLb lblLastN = new TxtLb(frame1, aLblLastN, bgrColor, FNLABEL, fntColor, "Last Name");
    TxtFd txtLastN = new TxtFd(frame1, aTxtLastN, fldColor, FNLABEL, fntColor, "");


    //  PHOTO. Tools. Line 50.

    //  Button to go find the Person's Photo, and only place it in the form. Uploading it is separate.
    Buttn btnPhoto = new Buttn(frame1, aBtnPhoto, colPhoto, FNLABEL, fntColor, "+ Photo", e -> {
        try { ImageChooser(frame1); }    //  Tools. Line 54
        catch (IOException | InterruptedException ex) { throw new RuntimeException(ex); }
    });


    //  Line 2: Phone

    TxtLb lblPhon1 = new TxtLb(frame1, aLblPhon1, bgrColor, FNLABEL, fntColor, "Phone 1");
    TxtFd txtPhon1 = new TxtFd(frame1, aTxtPhon1, fldColor, FNLABEL, fntColor, "");

    TxtLb lblPhon2 = new TxtLb(frame1, aLblPhon2, bgrColor, FNLABEL, fntColor, "Phone 2");
    TxtFd txtPhon2 = new TxtFd(frame1, aTxtPhon2, fldColor, FNLABEL, fntColor, "");


    //  Line 3: Address

    TxtLb lblAddr = new TxtLb(frame1, aLblAddrs, bgrColor, FNLABEL, fntColor, "Address");
    TxtFd txtAddrs = new TxtFd(frame1, aTxtAddrs, fldColor, FNLABEL, fntColor, "");

    TxtLb lblCity = new TxtLb(frame1, aLablCity, bgrColor, FNLABEL, fntColor, "City");
    TxtFd textCity = new TxtFd(frame1, aTextCity, fldColor, FNLABEL, fntColor, "");

    TxtLb lblState = new TxtLb(frame1, aLblState, bgrColor, FNLABEL, fntColor, "State");
    TxtFd txtState = new TxtFd(frame1, aTxtState, fldColor, FNLABEL, fntColor, "");

    TxtLb lblZipCd = new TxtLb(frame1, aLblZipCd, bgrColor, FNLABEL, fntColor, "Zip");
    TxtFd txtZipCd = new TxtFd(frame1, aTxtZipCd, fldColor, FNLABEL, fntColor, "");


    //  Line 4: Email

    TxtLb lblEmail = new TxtLb(frame1, lablEmail, bgrColor, FNLABEL, fntColor, "Email");
    TxtFd txtEmail = new TxtFd(frame1, textEmail, fldColor, FNLABEL, fntColor, "");


    //  Date of Birth Spinners
    TxtLb lblDOB = new TxtLb(frame1, lDateOBth, bgrColor, FNLABEL, fntColor, "Date of Birth");
    //  Month
    String[] aMonth = {"", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    SpnTxt spinrMon = new SpnTxt(frame1, spinerDay, fldColor, FNLABEL, fntColor, aMonth, aMonth[0]);
    //  Days: Create a list of days from 1 to 31
    String[] aDay = {"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};

    SpnTxt spinrDay = new SpnTxt(frame1, spinerMon, fldColor, FNLABEL, fntColor, aDay, aDay[0]);
    //  Year
    TxtFd textYear = new TxtFd(frame1, text_Year, fldColor, FNLABEL, fntColor, "");


    //  Radio Buttons

    //  Line 5: Gender
    ButtonGroup grpGender = new ButtonGroup();
    //buttonGroup.put("gender", grpGender);  //  adding an entry to a Map (collection) where "gender" is the key, and grpGender (a ButtonGroup object) is the value.
    Radio radiMale = new Radio(frame1, grpGender, radioMale, bgrColor, FNLABEL, fntColor, false, "Male");
    Radio radFemal = new Radio(frame1, grpGender, radFemale, bgrColor, FNLABEL, fntColor, false, "Female");


    //  Line 5: Marital Status
    ButtonGroup grpStatus = new ButtonGroup();
    //buttonGroup.put("marital", marStatus);
    Radio rdSingle = new Radio(frame1, grpStatus, radSingle, bgrColor, FNLABEL, fntColor, false, "Single");
    Radio rdMaried = new Radio(frame1, grpStatus, radMaried, bgrColor, FNLABEL, fntColor, false, "Married");
    Radio rdDivord = new Radio(frame1, grpStatus, radSepard, bgrColor, FNLABEL, fntColor, false, "Separated");

    Radio rdWidowd = new Radio(frame1, grpStatus, radWidowd, bgrColor, FNLABEL, fntColor, false, "Widowed");
    Radio rdSepart = new Radio(frame1, grpStatus, radDivord, bgrColor, FNLABEL, fntColor, false, "Divorced");
    Radio rdCmonLw = new Radio(frame1, grpStatus, radCmonLw, bgrColor, FNLABEL, fntColor, false, "Com.Law Marr.");


    //  Line 6: Password

    TxtLb lblPswd = new TxtLb(frame1, lblPasswd, bgrColor, FNLABEL, fntColor, "Password");
    TxtFd pswPaswd = new TxtFd(frame1, pswPasswd, fldColor, FNLABEL, fntColor, "");

    TxtLb lblConf = new TxtLb(frame1, lblConfir, bgrColor, FNLABEL, fntColor, "Confirm");
    TxtFd pswConfr = new TxtFd(frame1, pswConfir, fldColor, FNLABEL, fntColor, "");


    //  NOTIFICATION

    TxtLb lblNotif = new TxtLb(frame1, lblNotify, bgrColor, FNLABEL, fntColor, "Receive");

    ButtonGroup grpNotify = new ButtonGroup();
    //buttonGroup.put("notify", grpNotify);
    CustomRadio rdNotif = new CustomRadio(frame1, grpNotify, radNotify, bgrColor, FNLABEL, fntColor, false, "Notifications");


    //  Line 7:  Text Area for additional notes

    TxtLb lblNotes = new TxtLb(frame1, aLblNotes, bgrColor, FNLABEL, fntColor, "Notes (Optional):");
    TextA txtNotes = new TextA(frame1, aTxtNotes, fldColor, FNLABEL, fntColor, "");




    //  Button Reset & Submit
    static int[] aBtnReset = {120, 580,  90, 40,  21};
    static int[] aBtnSubmt = {280, 580,  90, 40,  22};


    //  RESET

    Buttn btnReset = new Buttn(frame1, aBtnReset, LTGRAY, FNLABEL, fntColor, "Reset", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            performReset( txtFirst, txtMiddl, txtLastN, txtPhon1, txtPhon2, txtAddrs, textCity, txtState, txtZipCd, txtEmail,
                    spinrMon, spinrDay, textYear, grpGender, grpStatus, pswPaswd, pswConfr, grpNotify, txtNotes);
        }
    });

    //  SUBMIT.  This is handled by AddNew.java

    /*Buttn addNew = new Buttn(frame1, aBtnSubmt, MGREEN, FNLABEL, fntColor, "Submit", new btnAddNew(  //  AddNew.java. Line 49
            frame1, spnTitle, txtFirst, txtMiddl, txtLastN, txtPhon1, txtPhon2, txtAddrs, textCity, txtState, txtZipCd, txtEmail,
            spinrMon, spinrDay, textYear, grpGender, grpStatus, pswPaswd, pswConfr, grpNotify, txtNotes));*/

    Buttn addNew = new Buttn(frame1, aBtnSubmt, MGREEN, FNLABEL, fntColor, "Submit", new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            // Pass the imagePath and other form data to AddNew
            new btnAddNew(
                    frame1, spnTitle, txtFirst, txtMiddl, txtLastN, txtPhon1, txtPhon2, txtAddrs, textCity, txtState, txtZipCd, txtEmail,
                    spinrMon, spinrDay, textYear, grpGender, grpStatus, pswPaswd, pswConfr, grpNotify, txtNotes).actionPerformed(e);
        }
    });




}
