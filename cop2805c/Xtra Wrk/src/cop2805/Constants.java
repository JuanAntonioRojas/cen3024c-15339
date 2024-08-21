package cop2805;

import java.awt.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Constants {



    //  WINDOW FRAME
    static Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();  //  Computer screen size
    static double GOLDEN = 0.618033;






    //  RED:
    static final Color DRKRED = new Color(125,   0,   0);
    static final Color ONLRED = new Color(255,   0,   0);
    static final Color LMAUVE = new Color(224, 176, 255);
    static final Color LITRED = new Color(255, 204, 204);
    static final Color LPEACH = new Color(255, 218, 185);
    static final Color LBGEIG = new Color(255, 253, 208);  //  Light beige/cream

    //  GREEN
    static final Color DGREEN = new Color(  0,  25,   0);  //  Dark green
    static final Color MGREEN = new Color(120, 221, 171);  //  Medium green
    static final Color LGREEN = new Color(204, 255, 235);  //  Light green
    static final Color LTRGRN = new Color(210, 255, 245);  //  Lighter green
    static final Color VLGREE = new Color(210, 255, 245);  //  Very Light green

    //  BLUE
    static final Color NAVYBL = new Color(  0,   0, 100);  //  Navy Blue
    static final Color MDBLUE = new Color( 50, 200, 255);  //  Medium Blue
    static final Color LTBLUE = new Color(150, 225, 250);  //  Light Blue
    static final Color LTPBLU = new Color(180, 235, 245);  //  Light Pastel Blue
    static final Color VLBLUE = new Color(230, 249, 255);  //  Very Light Blue

    //  YELLOW
    public static final Color LTYELL = new Color(255, 255, 200);  //  Light Yellow
    public static final Color VSYELL = new Color(255, 255, 240);  //  Very Soft Yellow


    //  GRAY
    public static final Color DKGRAY = new Color( 64,  64,  64);  //  Dark Gray
    public static final Color MDGRAY = new Color(128, 128, 128);  //  Medium Gray
    public static final Color LTGRAY = new Color(192, 192, 192);  //  Light Gray
    public static final Color LTRGRY = new Color(230, 230, 230);  //  Lighter Gray
    public static final Color VSGRAY = new Color(245, 245, 245);  //  Very Soft Gray
    public static final Color WHITE  = new Color(255, 255, 255);  //  WHITE


    //  Shadow colors
    public static final Color SHAD255 = new Color(0, 0,0, 255);   //  BLACK Shadow
    public static final Color SHAD128 = new Color(0, 0, 0, 128);  //  Button shade 1/2
    public static final Color SHADE64 = new Color(0, 0, 0,  64);  //  1/4
    public static final Color NOCOLOR = new Color(230, 230, 230, 0);  //  Button Shade Color


    //  Button Plain and Hover colors





    //  FONT
    static Font FNTITLE  = new Font("Arial", Font.BOLD, 24);  // Font family: Arial, Style: Bold, Size: 24
    static Font FNLABEL  = new Font("Arial", Font.BOLD, 14);  // Font family: Arial, Style: Bold, Size: 24




    //  NETWORKING
    public static final String HOST = "localhost";
    public static final int PORTCLIENT = 4000;
    public static final int PORTIMAGES = 5000;

    public static String line = "";
    public static final String STOP = "q";
    public static Socket client;
    public static ServerSocket server;




    //  DATABASE
    public static final String DB = "clients";
    public static final String PORTSQL = HOST + ":3306";
    public static final String MYSQLURL = "jdbc:mysql://" + PORTSQL + "/" + DB;    //  MySql Url;
    public static final String USER = "root";
    public static final String PSWD = "";
    public static final String CLASSFORNAME = "com.mysql.cj.jdbc.Driver";






    //  DISTANCES
    static int[] d = {
            //0,1,  2,  3,  4,  5,  6,  7,  8,  9,  10,  11,  12,  13,  14,  15,  16,  17,  18,  19
            1, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, 110, 120, 130, 140, 150, 160, 170, 180, 190,

            //20, 21,  22,  23,  24,  25,  26,  27,  28,  29,  30,  33,  32,  33,  34,  35,  36,  37,  38,  39
            200, 210, 220, 230, 240, 250, 260, 270, 280, 290, 300, 330, 320, 330, 340, 350, 360, 370, 380, 390,

            //30, 32,  32,  33,  34,  35,  36,  37,  38,  39,  40,  43,  42,  44,  44,  45,  46,  47,  48,  49
            300, 310, 320, 330, 340, 350, 360, 370, 380, 390, 400, 430, 420, 430, 440, 450, 460, 470, 480, 490,

            //50, 51,  52,  53,  54,  55,  56,  57,  58,  59,  60,  66,  62,  66,  64,  65,  66,  67,  68,  69
            500, 510, 520, 530, 540, 550, 560, 570, 580, 590, 600, 630, 620, 630, 640, 650, 660, 670, 680, 690,

            //70, 72,  72,  73,  74,  75,  76,  77,  78,  79,  80,  83,  82,  84,  88,  85,  86,  87,  88,  89
            700, 710, 720, 730, 740, 750, 760, 770, 780, 790, 800, 830, 820, 830, 840, 850, 860, 870, 880, 890,

            //90, 92,  92,  93,  94,  95,  96,  97,  98,  99, 100, 103, 102, 104, 108, 105, 106, 107, 108, 109
            900, 910, 920, 930, 940, 950, 960, 970, 980, 990,1000,1030,1020,1030,1040,1050,1060,1070,1080,1090,
    };

}



























