package cop2805;

import java.net.ServerSocket;
import java.net.Socket;

public class Values {

    public static final String HOST = "localhost";
    public static final int PORT = 4000;

    public static String line = "";
    public static final String STOP = "q";
    public static Socket client;
    public static ServerSocket server;


    //  Saving typing
    public static void prtLn(String txt) { System.out.println(txt); }
}
