package cop2805;


import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import static cop2805.Create.*;
import static cop2805.Constants.*;
import static cop2805.Services.*;
import static cop2805.Tools.*;


public class Welcome
{
    //  FRAME

    static String title = "  WELCOME  TO  ACME";         //  Title

    //  Position
    static double tpLfX = SCREEN.getWidth() * 1.6;     //  Top Left corner X value
    static double tpLfY = SCREEN.getHeight() * 0.01;     //  Top Left corner Y value

    //  Dimensions
    static double wnWd = 800.0, wnHt = 900;  // * GOLDEN;   //  Width & Height. Golden ratio

    //  Positioning the window Frame
    static double[] framePos = { tpLfX, tpLfY, wnWd, wnHt };




    //  LOGO
    static int[] aImgLogo = {d[7], d[6], d[51], d[41], 1};
    static String pathLogo = "img/Acme.jpg";




    //  TITLE
    static int[] aLblTitle = { d[64], d[8], d[19], d[5], 101};




    //  BUTTONS
    static int[] aBgrPnlBtn = {d[64], d[15], d[20], d[21], 0};
    static int[] aBtnCreate = {d[3], d[2], d[15], d[5], 1};
    static int[] aBtnReadTb = {d[3], d[8], d[15], d[5], 2};
    static int[] aBtnUpdate = {d[3], d[14], d[15], d[5], 3};




    //  BODY SLOGAN / PITCH
    static int[] aBgrPnlSlo = {d[7], d[50], d[75], d[50], 102};
    static int[] aLblSlogan = {  5, d[4], d[75], d[23], 103};
    static String txtMoto = "“We create happiness”";
    static String txtCatchphrase = "That’s our motto at ACME Inc., and it permeates everything we do." +
            "We can only achieve that magic touch by pushing the limits of what's physically possible and deliver " +
            "what's never been seen before!<br>Are you ready to try an ACME tool and make a smashing impact?";
    static String txtMostValued = "Check out our most valued (beardy) engineers:";
    // Create an HTML string
    static String htmlSlogan = "<html>"
            + "<div style='background-color: white; color: navy; padding:15px;'>"
            + "<div style='font-size: 24px; font-weight: bold; text-align: center;'>" + txtMoto + "</div><br>"
            + "<div style='font-size: 15px; font-weight: normal; text-align: left;'>" + txtCatchphrase + "</div><br>"
            + "<div style='font-size: 18px; font-weight: bold; text-align: center;'>" + txtMostValued + "</div><br>"
            + "</div>"
            + "</html>";


    //  Photos
    static String DenR = "img/DennisRitchie.jpg";
    static String KenT = "img/KenThompson.jpg";
    static String GarK = "img/GaryKildall.jpg";
    static String SWoz = "img/SteveWozniak.jpg";
    static String Jobs = "img/SteveJobs.jpg";

    static int[] aPosIBDen = {d[ 3], d[27], d[11], d[11], 5};
    static int[] aPosIBKen = {d[15], d[27], d[11], d[11], 4};
    static int[] aPosIBGar = {d[27], d[27], d[11], d[11], 6};
    static int[] aPosIBWoz = {d[39], d[27], d[11], d[11], 7};
    static int[] aPosIBJob = {d[61], d[27], d[11], d[11], 8};




    //  MAIN

    public static void main(String[] args)
    {

        // Create the main window frame
        WinFrame frame0 = new WinFrame(title, framePos, LTBLUE);


        //  LOGO IMAGE
        ImageLabel btnPhoto = new ImageLabel(frame0, pathLogo, aImgLogo);


        //  TITLE
        String title = "<html><div style='text-align: center;'><b><i>CLIENT<br>&nbsp;REGISTRATION</b></i></div></html>";
        TxtLb lblTitle1 = new TxtLb(frame0, aLblTitle, LTBLUE, FNTITLE, NAVYBL, title);



        //  THE BUTTONS

        Panl pnelBtnBg = new Panl(frame0, aBgrPnlBtn, VSYELL);

        Buttn AddN = new Buttn(frame0, aBtnCreate, MDBLUE, FNLABEL, NAVYBL, "Add New",  e -> new Add() );

        Buttn Tabl = new Buttn(frame0, aBtnReadTb, LTGRAY, FNLABEL, NAVYBL, "Read Table",  e -> new Table() );

        Buttn Srch = new Buttn(frame0, aBtnUpdate, LGREEN, FNLABEL, NAVYBL, "Update User", e -> new Search() );

        pnelBtnBg.add(AddN);
        pnelBtnBg.add(Tabl);
        pnelBtnBg.add(Srch);



        //  BODY

        Panl pnelSlogan = new Panl(frame0, aBgrPnlSlo, VSYELL);

        pTxtLb lblSlogan = new pTxtLb(frame0, aLblSlogan, LTBLUE, FNTITLE, NAVYBL, htmlSlogan);

        pnelSlogan.add(lblSlogan);


        //  IMAGE BUTTONS
        ImgButton ibKen = new ImgButton(frame0, KenT, aPosIBKen, SHADE64);
        ImgButton ibDen = new ImgButton(frame0, DenR, aPosIBDen, SHADE64);
        ImgButton ibGar = new ImgButton(frame0, GarK, aPosIBGar, SHADE64);
        ImgButton ibWoz = new ImgButton(frame0, SWoz, aPosIBWoz, SHADE64);
        ImgButton ibJob = new ImgButton(frame0, Jobs, aPosIBJob, SHADE64);

        pnelSlogan.add(ibKen);
        pnelSlogan.add(ibDen);
        pnelSlogan.add(ibGar);
        pnelSlogan.add(ibWoz);
        pnelSlogan.add(ibJob);

        ibKen.addActionListener(e -> {
            // Code to execute when the button is clicked
            System.out.println("Image button clicked!");
        });



        //  START THE XAMPP's Apache and MySQL (assuming XAMPP itself has already started)
        //  Server myserver = new Server();  //  This will start the server from here, but I'd rather start it separately.
        //      It's a good practice to separate the server logic from the client application.



        //  detecting the OS
        String os = System.getProperty("os.name").toLowerCase();

        //  Window listener to handle the window closing event (STOP XAMPP services)
        frame0.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (os.contains("win")) { stopWinApacheMySQL(os); }
                else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) { StopMacLinApacheMySQL(); }
                else { prtln("Unknown operating system."); }
            }
        });
    }
}



















//