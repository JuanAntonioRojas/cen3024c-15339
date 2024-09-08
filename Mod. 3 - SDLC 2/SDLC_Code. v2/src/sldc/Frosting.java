package sldc;

import static sldc.Tools.*;


public class Frosting
{
//  this was done with the help of the following website:
//  http://www.patorjk.com/software/taag/#p=display&f=Graffiti&t=Type%20Something%20



    //  Splash Image Fillers
    public static String S(int n) { return prtColor(45, 17, Rp(" ", n));  }
    public static String p(int n) { return prtColor(45, 17, Rp("_", n));  }
    public static String l(int n) { return prtColor(45, 17, Rp("|", n));  }
    public static String a(int n) { return prtColor(45, 17, Rp("\\",n));  }
    public static String s(int n) { return prtColor(45, 17, Rp("/", n));  }
    public static String h(int n) { return prtColor(45, 17, Rp("(", n));  }
    public static String I(int n) { return prtColor(45, 17, Rp(")", n));  }
    public static String m(int n) { return prtColor(45, 17, Rp("'", n));  }
    public static String g(int n) { return prtColor(45, 17, Rp("`", n));  }
    public static String d(int n) { return prtColor(45, 17, Rp(".", n));  }
    public static String c(int n) { return prtColor(45, 17, Rp(",", n));  }



/*
Here is the pattern in ASCII Art
All I have to do is count and replace... (very tedious, but cute)
  _        _   _
 | |      (_) | |
 | |       _  | |__    _ __    __ _   _ __   _   _
 | |      | | | '_ \  | '__|  / _` | | '__| | | | |
 | |____  | | | |_) | | |    | (_| | | |    | |_| |
 |______| |_| |_.__/  |_|     \__,_| |_|     \__, |
                                              __/ |
  __  __                       _             |___/
 |  \/  |                     | |
 | \  / |   __ _   _ __ ___   | |_
 | |\/| |  / _` | | '_ ` _ \  | __|
 | |  | | | (_| | | | | | | | | |_   _
 |_|  |_|  \__, | |_| |_| |_|  \__| (_)
            __/ |
   _____   |___/         _
  / ____|               | |
 | (___    _   _   ___  | |_    ___   _ __ ___
  \___ \  | | | | / __| | __|  / _ \ | '_ ` _ \
  ____) | | |_| | \__ \ | |_  |  __/ | | | | | |
 |_____/   \__, | |___/  \__|  \___| |_| |_| |_|
            __/ |
           |___/
 */




    // Function to print the word "Library" using the methods in Tools
    public static void prtLibrary() {
        // Line 1/8 of Library
        S(16); p(1); S(8); p(1); S(3); p(1); S(50); nl(1);

        // Line 2 of Library
        S(15); l(1); S(1); l(1); S(6); h(1); p(1); I(1); S(1); l(1); S(1); l(1); S(49); nl(1);

        // Line 3 of L                              i                                   b                           r                                 a                                 r                                           y
        S(15); l(1); S(1); l(1); S(7); p(1); S(2); l(1); S(1); l(1); p(2); S(4); p(1); S(1); p(2); S(4); p(2); S(1); p(1); S(3);  p(1); S(1); p(2); S(3); p(1); S(3); p(1); S(16); nl(1);

        // Line 4 of L                              i                                   b                                                           r                                                   a                                                           r                                                                       y
        S(15); l(1); S(1); l(1); S(6); l(1); S(1); l(1); S(1); l(1); S(1); m(1); p(1); S(1); a(1); S(2); l(1); S(1); m(1); p(2); l(1); S(2); s(1); S(1); p(1); g(1); S(1); l(1); S(1); l(1); S(1); m(1); p(2); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); S(14); nl(1);

        // Line 5 of L                                          i                                  b                                                r                                                   a                                                   r                                                   y
        S(15); l(1); S(1); l(1); p(4); S(2); l(1); S(1); l(1); S(1); l(1); S(1); l(1); p(1); I(1); S(1); l(1); S(1); l(1); S(1); l(1); S(4); l(1); S(1); h(1); p(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(4); l(1); S(1); l(1); p(1); l(1); S(1); l(1); S(15); nl(1);

        // Line 6 of L                                  i                               b                                                   r                                   a                                                   r                               y
        S(15); l(1); p(6); l(1); S(1); l(1); p(1); l(1); S(1); l(1); p(1); d(1); p(2); s(1); S(2); l(1); p(1); l(1); S(5); a(1); p(2); c(1); p(1); l(1); S(1); l(1); p(1); l(1); S(5); a(1); p(2); c(1); S(1); l(1); S(15); nl(1);

        // Line 7 of Library
        S(60); p(2); s(1); S(1); l(1); S(15); nl(1);

        // Line 8 of Librar                                     y
        S(16); p(2); S(2); p(2); S(23); p(1); S(13);   l(1); p(3); s(1); S(16); nl(1);
    }

    // Function to print the word "Management"
    public static void prtManagement() {

        // Line 1/8 of Mgm                                                      t.
        S(15); l(1); S(2); a(1); s(1); S(2); l(1); S(21); l(1); S(1); l(1); S(33); nl(1);

        // Line 2 of M                                                                  g                                  m                                                    t.
        S(15); l(1); S(1); a(1); S(2); s(1); S(1); l(1); S(3); p(2); S(1); p(1); S(3); p(1); S(1); p(2); S(1); p(3); S(3); l(1); S(1); l(1); p(1); S(32); nl(1);

        // Line 3 of M                                                                          g                                                   m                                                                                                       t.
        S(15); l(1); S(1); l(1); a(1); s(1); l(1); S(1); l(1); S(2); s(1); S(1); p(1); g(1); S(1); l(1); S(1); l(1); S(1); m(1); p(1); S(1); g(1); S(1); p(1); S(1); a(1); S(2); l(1); S(1); p(2); l(1); S(31); nl(1);

        // Line 4 of M                                                                    g                                                   m                                                                               t.
        S(15); l(1); S(1); l(1); S(2); l(1); S(1); l(1); S(1); l(1); S(1); h(1); p(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); p(1); S(3); p(1); S(28); nl(1);

        // Line 5 of M                                                                  g                                                             m                                                                                                     t                                   .
        S(15); l(1); p(1); l(1); S(2); l(1); p(1); l(1); S(2); a(1); p(1); p(1); c(1); S(1); l(1); S(1); l(1); p(1); l(1); S(1); l(1); p(1); l(1); S(1); l(1); p(1); l(1); S(2); a(1); p(2); l(1); S(1); h(1); p(1); I(1); S(27); nl(1);

        // Line 6 of M
        S(26); p(1); p(1); s(1); S(1); l(1); S(49); nl(1);

        // Line 7 of M               g
        S(17); p(5); S(3); l(1); p(3); s(1); S(9); p(1); S(40); nl(1);
    }

    // Function to print the word "System"
    public static void prtSystem() {
        // Line 1 of System
        S(16); s(1); S(1); p(4); l(1); S(15); l(1); S(1); l(1); S(39); nl(1);

        // Line 2 of Sys                                                                                            tem
        S(15); l(1); S(1); h(1); p(3); S(4); p(1); S(3); p(1); S(3); p(3); S(2); l(1); S(1); l(1); p(1); S(4); p(3); S(3); p(1); S(1); p(2); S(1); p(3); S(20); nl(1);

        // Line 3 of S                                          y                                                                   s                                           t                                       e                                                   m
        S(16); a(1); p(3); S(1); a(1); S(2); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); s(1); S(1); p(2); l(1); S(1); l(1); S(1); p(2); l(1); S(2); s(1); S(1); p(1); S(1); a(1); S(1); l(1); S(1); m(1); p(1); S(1); g(1);S(1); p(1); S(1); a(1); S(19); nl(1);

        // Line 4 of S                                          y                                                                   s                                           tem
        S(16); p(4); I(1); S(1); l(1); S(1); l(1); S(1); l(1); p(1); l(1); S(1); l(1); S(1); a(1); p(2); S(1); a(1); S(1); l(1); S(1); l(1); p(1); S(2); l(1); S(2); p(2); s(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(1); l(1); S(18); nl(1);

        // Line 5 of S                              y                                                   s                               t                                       e                                   m
        S(15); l(1); p(5); s(1); S(3); a(1); p(2); c(1); S(1); l(1); S(1); l(1); p(3); s(1); S(2); a(1); p(2); l(1); S(2); a(1); p(3); l(1); S(1); l(1); p(1); l(1); S(1); l(1); p(1); l(1); S(1); l(1); p(1); l(1); S(18); nl(1);

        // Line 6 of System
        S(26); p(2); s(1); S(1); l(1); S(49); nl(1);

        // Line  of System
        S(25); l(1); p(3); s(1); S(50); nl(1);
    }
}









































