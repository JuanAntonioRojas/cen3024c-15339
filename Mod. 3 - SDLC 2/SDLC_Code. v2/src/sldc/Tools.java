package sldc;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.io.PrintWriter;


public class Tools {

    static String bk2Normal = "\033[0m";     //  Default = not-colored, not bold, not italic, not underlined.

    //  Print function:
    public static String prt(String txt) { System.out.print(txt);  return txt; }

    public static void println(String txt) {
        System.out.println(txt);
    }

    // Print Bold text
    public static void bold(String txt) { println("\033[1m" + txt + bk2Normal); }

    // Print Italicized text (not universally supported)
    public static void italic(String txt) { println("\033[3m" + txt + bk2Normal); }

    // Print Underlined text
    public static void underln(String txt) { println("\033[4m" + txt + bk2Normal); }



    // Print combo of bold, underlined and italic
    public static String boldItalUnder(int pre, int pos, String txt) {

        String space = " ".repeat(pre);

        //  To be able to bold and underline any letter of a word, we need to split the word into 3 parts
        String beforL = txt.substring(0, pos);                      // Before the letter to underline
        String letter = String.valueOf(txt.charAt(pos));            // The letter to underline
        String afterL = txt.substring(pos + 1);           // After the letter to underline

        //  To color with the oldColor function we pass 6 parameters:
        String specLtr = "\033[1m\033[3m\033[4m" + letter + bk2Normal;

        return space.concat(beforL).concat(specLtr).concat(afterL);
    }







    //  CLEAR SCREEN
    public static void cls() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }






    //  The SINGLE LINE characters for the INNER BOXES  (in ascii they're 191-197, in unicode: 2500-254F)

    //  Assign Unicode to English Variable
    public static String horizontal_S = "\u2500", vertical_S = "\u2502";
    public static String topLfCorner_S = "\u250C", topMiddle_S = "\u253C", topRightCorner_S = "\u2510";
    public static String middleLeft_S = "\u251C", middleRight_S = "\u2524";
    public static String btmLeftCorner_S = "\u2514", bottomMiddle_S = "\u2534", bottomRightCorner_S = "\u2518";
    //  Assign English Variables to Abbreviated Variables
    public static String Shrz = horizontal_S, Svrt = vertical_S;
    public static String Stlc = topLfCorner_S, Stmd = topMiddle_S, Strc = topRightCorner_S;
    public static String Smdl = middleLeft_S, Smdr = middleRight_S;
    public static String Sblc = btmLeftCorner_S, Sbmd = bottomMiddle_S, Sbrc = bottomRightCorner_S;



    //  The DOUBLE LINE characters for the BOX (in ascii they're 200-206, in unicode: 2550-256C)

    //  Assign Unicode to English Variables
    public static String horizontal_D = "\u2550", vertical_D = "\u2551";
    public static String topLfCorner_D = "\u2554", topMiddle_D = "\u2566", topRightCorner_D = "\u2557";
    public static String middleLeft_D = "\u2560", middleRight_D = "\u2563";
    public static String btmLeftCorner_D = "\u255A", bottomMiddle_D = "\u2569", bottomRightCorner_D = "\u255D";

    //  Assign English Variables to Abbreviated Variables
    public static String Dhrz = horizontal_D, Dvrt = vertical_D;
    public static String Dtlc = topLfCorner_D, Dtmd = topMiddle_D, Dtrc = topRightCorner_D;
    public static String Dmdl = middleLeft_D, Dmdr = middleRight_D;
    public static String Dblc = btmLeftCorner_D, Dbmd = bottomMiddle_D, Dbrc = bottomRightCorner_D;





    //  COLORS:    Foreground   Background          Shades
    static int fBlk = 30,       bBlk = 40;          public static String Light = "\u2591";
    static int fRed = 31,       bRed = 41;          public static String Medium = "\u2592";
    static int fGrn = 32,       bGrn = 42;          public static String Dark = "\u2593";
    static int fYlw = 33,       bYlw = 43;
    static int fBlu = 34,       bBlu = 44;
    static int fPpl = 35,       bPpl = 45;
    static int fCyn = 36,       bCyn = 46;
    static int fWht = 37,       bWht = 47;


    // Repeater Function
    public static String Rp(String strg, int x) {
        return strg.repeat(x);
    }

    //  New Line Function
    public static void nl(int n) {
        System.out.print(Rp("\n", n));
    }


    //  IN ORDER TO PRINT ESCAPE CHARACTERS WE NEED A SPECIAL PRINT FUNCTION
    static PrintWriter printWriter = new PrintWriter(System.out, true);


//  THE OLD COLORING FUNCTION
    //  it only uses 4 bit colors: 16 colors max.
    //  but it can give characters in bold, underlined and italics
    public static String oldColor(int bld, int und, int ita, int fgr, int bgr, String text) {
        return "\033[" + bld + ";" + und + ";" + ita + ";" + fgr + ";" + bgr + "m" + text;
    }

    public static void endCol() { System.out.print(bk2Normal); }






//  TOP WINDOW MENU BAR (DOS VERSION)

    public static String mnWord(int pre, int pos, int specFgr, int fgr, int bgr, String menu)
    {

        String space = " ".repeat(pre);

        // To be able to bold and underline any letter of a word, we need to split the word into parts
        String before = menu.substring(0, pos);          // Before the letter to underline
        String letter = String.valueOf(menu.charAt(pos)); // The letter to underline
        String after = menu.substring(pos + 1);           // After the letter to underline

        //  To color with the oldColor function we pass 6 parameters:
        String befr = oldColor(1, 0, 0, fgr, bgr, before);
        String spec = oldColor(1, 4, 3, specFgr, bgr, letter);
        String aftr = oldColor(1, 0, 0, fgr, bgr, after);

        return space.concat(befr).concat(spec).concat(aftr);
    }





    //  Date and time:
    public static String DiaHora() {
        Date curDate = new Date();
        SimpleDateFormat formDate = new SimpleDateFormat("MM/dd/yy");
        String fecha = formDate.format(curDate);
        SimpleDateFormat formatDate = new SimpleDateFormat("hh:mm");
        String hora = formatDate.format(curDate);
        return fecha + "-" + hora;
    }





//  THE PRIMARY NEW COLOR DEFINING CLASS:

    //  The color numbers come from an 8-bit chart of colors (wikipedia).
    //  It takes 3 arguments: Foreground & Background colors, and the Text content.
    //  Terminating (\033[0m) the coloring function (going back to normal):

    public static String prtColor(int Fgr, int Bgr, String TXT) {
        prt("\u001b[38:5:" + Fgr + "m\u001b[48:5:" + Bgr + "m" + TXT + bk2Normal);
        return TXT;
    }











//  BOX CREATOR. FOUNDATION

    //  top lines of a box:  Indent  +  Top Left Corner  +  Lines  +  Horizontal Right Corner
    public static String hzBorder(int fg, int bg, String lfCorner, String centerLine, int width, String rtCorner) {
        prtColor(fg, bg, lfCorner + centerLine.repeat(width) + rtCorner);
        return lfCorner;
    }

    //  middle vertical lines of a box
    public static String vtLine(int fg, int bg, String vertLine) { prtColor(fg, bg, vertLine);
        return vertLine;
    }


//  BOX CREATOR. DETAILED

    //  Pre-defined double boxes
    public static String topBoxDouble(int fg, int bg, int wd) { return hzBorder(fg, bg, Dtlc, Dhrz, wd, Dtrc); }

    public static String vtLineDouble(int fg, int bg) { return vtLine(fg, bg, Dvrt);  }

    public static String btmBoxDouble(int fg, int bg, int wd) { return hzBorder(fg, bg, Dblc, Dhrz, wd, Dbrc);  }


    //  Pre-defined single boxes
    public static String topBoxSingle(int fg, int bg, int wd) { return hzBorder(fg, bg, Stlc, Shrz, wd, Strc); }

    public static String vtLineSingle(int fg, int bg) { return vtLine(fg, bg, Svrt); }

    public static String btmBoxSingle(int fg, int bg, int wd) {  return hzBorder(fg, bg, Sblc, Shrz, wd, Sbrc); }


/*
TO DO: the little 'T' in the middle of the top border for the table.
    //  For the table columns
    public static String topColSingle(int fg, int bg, int wdCol1, int wdCol2, int wdCol3) {
        return hzBorder(fg, bg, Stlc, Shrz, wd, Strc);
        prtColor(fg, bg, lfCorner + centerLine.repeat(width) + rtCorner);
    }
*/



    //  Blank Space Filler
    public static String Sp(int fg, int bg, int sp) { return prtColor(fg, bg, Rp(" ", sp));  }




    //  Bottom Box Double Border
    public static void BtmBoxDblBorder() {
        Sp(124, 17, 8);
        btmBoxDouble(17, 3, 62);
        Sp(124, 17, 8);
        nl(1);
    }


    //  Print each WORD w Fgr & Bgr, up to a "fixed" number of characters (nChar)
     public static String PRT_WORD(int nChar, int fg, int bg, String word)
    {
        String content;
        int wdSize = word.length();
        int wdComp = nChar - wdSize;  //  complementary to size, minus init space

        if (wdSize < nChar) {
            //  left padding: 1 blank space, right padding: the complementary to the word size
            content = Sp(fg, bg, 1) + prtColor(fg, bg, word) + Sp(fg, bg, wdComp);
        }
        else {
            word = word.substring(0, nChar);   //  Chop the word to a max of nbChar.
            content = Sp(fg, bg, 1) + prtColor(fg, bg, word);  //  w only left padding of 1 blank space
        }
        return content;
    }



//  This part is optional.
    //  I would like to make it read each customer's form instead of a list
    //  I tried to get Prev and Next to be like top menu, but for now this will do:
    public static String Button(int fgr, int bgr, String btn)
    {
        String first = String.valueOf(btn.charAt(2));
        String rest = String.valueOf(btn.substring(3));

        String fst = oldColor(1, 4, 3, fgr, bgr, first);
        String rst = oldColor(1, 0, 0, fgr, bgr, rest);

        return fst.concat(rst);
    }
}


