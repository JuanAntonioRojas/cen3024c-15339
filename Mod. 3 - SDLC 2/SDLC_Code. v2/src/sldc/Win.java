package sldc;

import java.util.Scanner;

import static sldc.Frosting.*;
import static sldc.Tools.*;
import static sldc.Tools.topBoxDouble;


public class Win {

    static String[] MENUS = {"ADD BOOK", "ADD LIST", "DEL. BOOK", "VIEW ALL", "EXIT"};

    public static void WindowTopMenu()
    {
        String mSpc = oldColor(0, 0, 0, 30, 43, " ");                 //  Space before
        String Mn1 = mnWord(1, 0, fBlk, fBlk, bYlw, MENUS[0]);
        String Mn2 = mnWord(4, 4, fBlk, fBlk, bYlw, MENUS[1]);
        String Mn3 = mnWord(4, 0, fBlk, fBlk, bYlw, MENUS[2]);
        String Mn4 = mnWord(4, 0, fBlk, fBlk, bYlw, MENUS[3]);
        String Mn5 = mnWord(4, 1, fBlk, fBlk, bYlw, MENUS[4]);

        //  Display Menu Bar:
        printWriter.println(mSpc + Mn1 + Mn2 + Mn3 + Mn4 + Mn5 + Rp("\t", 3) + DiaHora() + "\t\033[0m");
    }




    //   Blank Screen Lines
    public static void Blank_Screen_Lines(int n) {
        for (int i = 1; i <= n; i++) {
            Sp(124, 17, 80);
            nl(1);
        }
    }


    //  The splash screen builder
    public static void Splash_Library_Management_System()
    {
        // Call the functions to print all 3 words
        prtLibrary();
        prtManagement();
        prtSystem();
    }



    //  The SPLASH SCREEN for Library Management System
    public static void Splah_Screen()
    {
        println("\n");    //  Print a blank line, to separate the splash screen from the java source code

        WindowTopMenu();     //  Print the top line of the menu

        Blank_Screen_Lines(1);  //  Print a blank filler line

        // Splash Screen Title;
        Splash_Library_Management_System();    //  Print the splash screen from Frosting (on the cake)... :)

        Blank_Screen_Lines(1);  //  Print a blank filler line
    }



    //  DROP MENU
    public static void DROP_MENU(String menu1, String menu2)
    {
        Sp(124, 17, 8);  Sp(17, 136, 2);  prtColor(17, 136, menu1);  Sp(17, 136, 2);  Sp(124, 17, 63); nl(1);
        Sp(124, 17, 8);  Sp(17, 136, 2);  prtColor(17, 136, menu2);  Sp(17, 136, 3);  Sp(124, 17, 63); nl(1);
    }




        //  Double Box Title
    public static void BoxTitle(String title)
    {
        //  The title is centered in a box-length (bl) character box
        int bl = 64;
        int n = title.length();
        int n1 = (bl - n) / 2;
        int n2 = bl - n - n1;

        Sp(124, 17, 8);
        Sp(17, 3, n1);
        prtColor(17, 3, title);
        Sp(17, 3, n2);    Sp(124, 17, 8);    nl(1);
    }





    public static String[] get_Title_Author()
    {
        //   clearScreen();

        WindowTopMenu();

        //  1: Blank Space Line
        Blank_Screen_Lines(2);


        //  2:  Main Box Title
        BoxTitle("ADD A BOOK:");


        //  3:  Top Border Double-Line below Box Title
        Sp(124, 17, 8);
        topBoxDouble(17, 3, 62);    Sp(124, 17, 8);    nl(1);


        //  4:  Inside Fileds: 2 Headers
        Sp(124, 17, 8);    vtLineDouble(17, 3);
        Sp(17, 3, 6);     prtColor(17, 3, "TITLE");
        Sp(17, 3, 26);     prtColor(17, 3, "AUTHOR");
        Sp(17, 3, 19);     vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);


        //  5:  Top Border for the Inside Field Title
        Sp(124, 17, 8);    vtLineDouble(17, 3);
        Sp(17, 3, 4);    topBoxSingle(17, 3, 22);  //  for name
        Sp(17, 3, 6);    topBoxSingle(17, 3, 22);   Sp(17, 3, 4);
        vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);


        //  6:  Field: TITLE
        Sp(124, 17, 8);   vtLineDouble(17, 3);    Sp(17, 3, 4);

        vtLineSingle(17, 3);
        Sp(17, 236, 22);
        vtLineSingle(17, 3);
        Sp(17, 3, 6);


        //  6:  Field: AUTHOR
        vtLineSingle(17, 3);
        Sp(17, 236, 22);
        vtLineSingle(17, 3);
        Sp(17, 3, 4);


        vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);  //  vertical double line at the end of the line


        //  7:  Bottom border for the Inside Fields Title
        Sp(124, 17, 8);    vtLineDouble(17, 3);
        Sp(17, 3, 4);    btmBoxSingle(17, 3, 22);
        Sp(17, 3, 6);    btmBoxSingle(17, 3, 22);   Sp(17, 3, 4);
        vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);


        Sp(124, 17, 8);  vtLineDouble(17, 3);  Sp(17, 3, 62);  vtLineDouble(17, 3); Sp(124, 17, 8);  nl(1);


        //  9:  Bottom Border Double-Line
        Sp(124, 17, 8);    btmBoxDouble(17, 3, 62);    Sp(124, 17, 8);    nl(1);


        //  10: Blank Space Lines
        Blank_Screen_Lines(3);


        //  USER INPUT

        String[] Title_and_Author = new String[2];

        //  Set up to acquire the user selection with a "scn" (scanner) system variable.
        Scanner scn = new Scanner(System.in);

        println("\nPlease enter a Title:");
        Title_and_Author[0] = scn.nextLine();

        println("\nPlease enter an Author:");
        Title_and_Author[1] = scn.nextLine();


        //  FUNCTION RETURNS THE USER INPUT
        return Title_and_Author;
    }






//  ADD A LIST OF BOOKS

    public static String get_Source_List()
    {
        //   clearScreen();

        WindowTopMenu();


        //  1: Blank Space Line
        Blank_Screen_Lines(2);


        //  2:  Main Box Title
        BoxTitle("LIST OF BOOKS");


        //  3:  Top Border Double-Line below Box Title
        Sp(124, 17, 8);
        topBoxDouble(17, 3, 62);    Sp(124, 17, 8);    nl(1);


        //  4:  Inside Fileds: 1 Header
        Sp(124, 17, 8);    vtLineDouble(17, 3);
        Sp(17, 3, 28);     prtColor(17, 3, "SOURCE:");
        Sp(17, 3, 27);     vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);


        //  5:  Top Border for the Inside Field Source
        Sp(124, 17, 8);    vtLineDouble(17, 3);   Sp(17, 3, 10);
        topBoxSingle(17, 3, 44);   Sp(17, 3, 6);
        vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);


        //  6:  Field: TITLE
        Sp(124, 17, 8);   vtLineDouble(17, 3);    Sp(17, 3, 10);
        vtLineSingle(17, 3);   Sp(17, 236, 44);   vtLineSingle(17, 3);   Sp(17, 3, 6);
        vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);  //  vertical double line at the end of the line


        //  7:  Bottom border for the Inside Fields Title
        Sp(124, 17, 8);    vtLineDouble(17, 3);   Sp(17, 3, 10);
        btmBoxSingle(17, 3, 44);   Sp(17, 3, 6);
        vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);


        Sp(124, 17, 8);  vtLineDouble(17, 3);  Sp(17, 3, 62);  vtLineDouble(17, 3); Sp(124, 17, 8);  nl(1);


        //  9:  Bottom Border Double-Line
        Sp(124, 17, 8);    btmBoxDouble(17, 3, 62);    Sp(124, 17, 8);    nl(1);


        //  10: Blank Space Lines
        Blank_Screen_Lines(3);


        //  USER INPUT

        //  Set up to acquire the user's source of books with a "scn" (scanner) system variable
        Scanner scn = new Scanner(System.in);

        println("Please enter a Source List of Books:");

        //  FUNCTION RETURNS THE USER INPUT
        return scn.nextLine();
    }





    //  DELETE A BOOK

    public static int get_ID() {
        //   clearScreen();

        WindowTopMenu();


        //  1: Blank Space Line
        Blank_Screen_Lines(2);


        //  2:  Main Box Title
        BoxTitle("DELETE A BOOK");


        //  3:  Top Border Double-Line below Box Title
        Sp(124, 17, 8);
        topBoxDouble(17, 3, 62);    Sp(124, 17, 8);    nl(1);


        //  Empty Line
        Sp(124, 17, 8);  vtLineDouble(17, 3);  Sp(17, 3, 62);  vtLineDouble(17, 3); Sp(124, 17, 8);  nl(1);

        //  5:  Top Border for the Inside Field ID
        Sp(124, 17, 8);    vtLineDouble(17, 3);   Sp(17, 3, 20);
        topBoxSingle(17, 3, 14);   Sp(17, 3, 26);
        vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);


        //  4:  Inside Filed ID
        Sp(124, 17, 8);    vtLineDouble(17, 3);
        Sp(17, 3, 15);     prtColor(17, 3, "ID:");    Sp(17, 3, 2);
        vtLineSingle(17, 3);   Sp(17, 236, 14);   vtLineSingle(17, 3);   Sp(17, 3, 26);

        vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);




        //  7:  Bottom border for the Inside Fields Title
        Sp(124, 17, 8);    vtLineDouble(17, 3);   Sp(17, 3, 20);
        btmBoxSingle(17, 3, 14);   Sp(17, 3, 26);
        vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);


        Sp(124, 17, 8);  vtLineDouble(17, 3);  Sp(17, 3, 62);  vtLineDouble(17, 3); Sp(124, 17, 8);  nl(1);


        //  9:  Bottom Border Double-Line
        Sp(124, 17, 8);    btmBoxDouble(17, 3, 62);    Sp(124, 17, 8);    nl(1);


        //  10: Blank Space Lines
        Blank_Screen_Lines(3);


        //  USER INPUT

        //  Set up to acquire the ID from the user with a "scn" (scanner).
        Scanner scn = new Scanner(System.in);
        println("Enter the ID of the book to delete:");
        int ID = scn.nextInt();                //  The user input number is saved in the inp var.

        //  FUNCTION RETURNS THE USER INPUT
        return ID;
    }






//  VIEW BOOK LIST

    public static void viewTableTop(){
        //   clearScreen();

        WindowTopMenu();

        //  Step 1: Blank Space Line
        Blank_Screen_Lines(2);


        //  Step 2:  Main Box Title
        BoxTitle("COMPLETE BOOK LIST");


        //  Step 3:  Top Border Double-Line below Box Title
        Sp(124, 17, 8);
        topBoxDouble(17, 3, 62);    Sp(124, 17, 8);    nl(1);


        //  Step 4:  Inside Fileds: 2 Headers
        Sp(124, 17, 8);    vtLineDouble(17, 3);
        Sp(17, 3, 4);     prtColor(17, 3, "ID");        //  ID takes 4 spaces
        Sp(17, 3, 4);     prtColor(17, 3, "TITLE");     //  TITLE takes 28 spaces
        Sp(17, 3, 23);     prtColor(17, 3, "AUTHOR");   //  AUTHOR takes 20 spaces
        Sp(17, 3, 18);     vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);


        //  Step 5:  Top Border for the Table
        Sp(124, 17, 8);    vtLineDouble(17, 3);  //  left window border
        Sp(17, 3, 2);    topBoxSingle(17, 3, 56);   Sp(17, 3, 2);
        vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);

        //  Step 6:  Inside Main.java. Line 290
    }




    public static void viewTableBottom() {


        Sp(124, 17, 8);  vtLineDouble(17, 3);  Sp(17, 3, 62);  vtLineDouble(17, 3); Sp(124, 17, 8);  nl(1);


        //  9:  Bottom Border Double-Line for the window
        Sp(124, 17, 8);    btmBoxDouble(17, 3, 62);    Sp(124, 17, 8);    nl(1);


        //  10: Blank Space Lines
        Blank_Screen_Lines(3);
    }




















    public static void READ_TOP()
    {
        WindowTopMenu();

        //   Blank Space Line
        Blank_Screen_Lines(2);

        //  2: Double Box Title
        BoxTitle("CUSTOMERS");

        //  3:  Double Box Top border
        Sp(124, 17, 8);
        topBoxDouble(17, 3, 62);    Sp(124, 17, 8);    nl(1);

        //  4:  Single Box Title
        Sp(124, 17, 8);   vtLineDouble(17, 3);
        Sp(17, 3, 1);     prtColor(17, 3, "ID");
        Sp(17, 3, 2);     prtColor(17, 3, "NAME");
        Sp(17, 3, 13);    prtColor(17, 3, "ADDRESS");
        Sp(17, 3, 10);    prtColor(17, 3, "PHONE");
        Sp(17, 3, 9);     prtColor(17, 3, "BALANCE");
        Sp(17, 3, 2);     vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);

        //   Blank Space Line
//        Blank_Lines(2);
    }


    public static String BegLine() {
        String begin =  Sp(124, 17, 8) + vtLineDouble(17, 3) + Sp(17, 3, 1);
        return begin;
    }

    public static void PRINT_FIELD(int nChar, String txt) {
        PRT_WORD(nChar, 17, 3, txt);
    }

    public static String EndLine() {
        String end =  vtLineDouble(17, 3) + Sp(124, 17, 8);
        return end;
    }

//    public static String PRINT_LINES(String line) {
//        String LINE =  Sp(124, 17, 8) + vtLineDouble(17, 3) + Sp(17, 3, 4) + prtColor(17, 3, line);
//        return LINE;
//    }

}