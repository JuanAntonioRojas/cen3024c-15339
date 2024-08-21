package cop2805;

import static cop2805.Constants.*;
import static cop2805.Create.*;
import static cop2805.Tools.*;
import static cop2805.Action.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;


public class Table extends JComponent
{
    //  WINDOW  FRAME DEFINITION

    static String title = "  LIST OF ACME'S CUSTOMERS"; //  Title

    //  Position
    static double tpLfX = SCREEN.getWidth()  * 1.5;     //  Top Left corner X value
    static double tpLfY = SCREEN.getHeight() * 0.4;     //  Top Left corner Y value


    //  Dimensions
    static double  wnWd= 1400.0, wnHt = wnWd * GOLDEN;  //  Width & Height. Golden ratio

    //  Positioning the window Frame
    static double[] framePos = { tpLfX, tpLfY, wnWd, wnHt };

    //  Positioning the Title
    public static final int[] posTablTitl = {500, 30, 400, 30, 101};

    //  FONT
    static Font FNTABTITL  = new Font("Arial", Font.BOLD, 36);






    //  Create the main window frame
    WinFrame frame2 = new WinFrame(title, framePos, LTBLUE);


    //  Form Title
    TxtLb lblTitle1 = new TxtLb(frame2, posTablTitl, LTBLUE, FNTABTITL, NAVYBL, "CLIENT INFORMATION");


    //  TABLE  DEFINITION
    static int[] tablePos = { 50, 100, (int) (wnWd*0.9), (int) (wnHt*0.7), 101};

    static String[] colNames = {
    //5,       10,      35,   3,       35,       5,      50,      50,     100,     50,    5,      20,     100,    10,    6,      10,       10,       15,      50,      50,      5,      100
    "ID", "Title", "First", "MI", "LastN", "Photo", "Phon1", "Phon2", "Addrs", "City", "St", "ZipCd", "Email", "Mon", "Day", "Year", "Gender", "Status", "Paswd", "Confr", "Notif", "Notes"};

    static int[] colWidths = { 5, 10, 35, 3, 35, 5, 50, 50, 100, 50, 5, 20, 100, 10, 6, 10, 10, 15, 50, 50, 5, 100 };

    static Color[] rowColors = { LTGRAY, WHITE, LTYELL };



    //  Get the String data from server
    String data = fetchDataFromServer();


    //  Create Table
    CustTable table1 = new CustTable(frame2, tablePos, data, colNames, colWidths, rowColors);









    // Fetch data from server and populate table1
    public static String fetchDataFromServer() {
        StringBuilder dataBuilder = new StringBuilder();


        //  SERVER CLIENT CONNECTION
        try
        {
            client = new Socket(HOST, PORTCLIENT);

            //  Send and receive to and from server. Setting up the Input and Output properly
            BufferedReader brINPUT = new BufferedReader(new InputStreamReader(client.getInputStream()));
            PrintWriter pwOUTPUT = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true);

            //  SEND A 'READ' REQUEST TO THE SERVER, TO GET THE DATA
            pwOUTPUT.println("read");

            //  READ SERVER DATA
            String line;
            while ((line = brINPUT.readLine()) != null) {
                dataBuilder.append(line).append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        logEvent(dataBuilder.toString());

        return dataBuilder.toString();
    }






    //  If user clicks on any row a couple of buttons will pop up: delete and update

    public static void showButtons(WinFrame frame, JTable table, int selectedRow) {

        //  DELETE BUTTON

        int[] aBtnDelete = {450, 730, 150, 50, 200};
        String idStr = (String) table.getValueAt(selectedRow, 0);    // Retrieve the ID (column index 0) as a String
        int ID = Integer.parseInt(idStr);



        Buttn delete = new Buttn(frame, aBtnDelete , LITRED, FNLABEL, DRKRED, "Delete", new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    performDelete(ID);                                          // Call the delete method in Action.java, line 351
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //prtln(String.valueOf(ID));  //  testing

        //  UPDATE BUTTON

        int[] aBtnUpdate = {800, 730, 150, 50, 200};

        Buttn update = new Buttn(frame, aBtnUpdate, MGREEN, FNLABEL, DGREEN, "Update", e -> {
            new Search();
        });
    }

}






























