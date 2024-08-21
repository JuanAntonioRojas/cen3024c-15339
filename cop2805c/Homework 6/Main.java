//  Homework 6
//  Connecting to a DataBase
//  Start the Wamp server
//


package cop2805;
import java.sql.*;    //  STEP 1



public class Main
{
    static String Host_Port = "127.0.0.1:3306";
    static String db  = "test";

    static String MySql   = "com.mysql.cj.jdbc.Driver",               MySqlUrl  = "jdbc:mysql://" + Host_Port + "/" + db;
    static String Sqlite  = "org.sqlite.JDBC",                        SqLiteUrl = "jdbc:sqlite:<path-to-db>";
    static String Postgrs = "org.postgresql.Driver",                  PstgrsUrl = "jdbc:postgresql://" + Host_Port + "/" + db;
    static String SqlSvr  = "com.microsoft.sqlserver.jdbc.SQLServerDriver", SqlSvrUrl = "jdbc:sqlserver://" + Host_Port + ";databaseName=" + db;
    static String Access  = "net.ucanaccess.jdbc.UcanaccessDriver",   AccessUrl = "jdbc:ucanaccess://" + Host_Port;
    static String Oracle  = "oracle.jdbc.driver.OracleDriver",        OracleUrl = "jdbc:oracle:thin:@" + Host_Port + ":<SID>";
    static String ibmDB2  = "com.ibm.db2.jcc.DB2Driver",              IBMDB2Url = "jdbc:db2://" + Host_Port + "/" + db;
    static String MariaDB = "org.mariadb.jdbc.Driver",                MariaDBUrl= "jdbc:mariadb://" + Host_Port + "/" + db;
    static String SyBase  = "com.sybase.jdbc4.jdbc.SybDriver",        SyBaseUrl = "jdbc:sybase:Tds://" + Host_Port + "/" + db;

    static String[] Driver = { MySql, SqlSvr, Access, Oracle, Postgrs, Sqlite, ibmDB2, MariaDB, SyBase};
    static String[] UrlConn = { MySqlUrl, SqlSvrUrl, AccessUrl, OracleUrl, PstgrsUrl, SqLiteUrl, IBMDB2Url, MariaDBUrl, SyBaseUrl};


    public static void main(String[] args)
    {
        String SelAllQry = "SELECT * FROM name";
        new Main().startQuery(SelAllQry);
    }

    public void startQuery(String query)
    {
        String url = UrlConn[0];    //  MySqlUrl;
        String usr = "root";
        String pwd = "";
        getData(url, usr, pwd, query);
    }


    public void getData(String url, String usr, String pwd, String qry)
    {
        //  Using a try/catch we ensure that the Connection, Statement, and ResultSet are automatically closed at the end of the statement.
        try {
            Class.forName(Driver[0]);                           //  STEP 2:  Load & register the MySql driver

            try ( Connection conn = DriverManager.getConnection(url, usr, pwd);//  STEP 3: get the connection ready
                  Statement  stat = conn.createStatement();     //  STEP 4:  Create a statement
                  ResultSet  rset = stat.executeQuery(qry) )    //  STEP 5:  Execute the query and get results into a ResultSet
            {
                process(rset);                                  //  STEP 6:  Process the results from the ResultSet. (line 65)
                stat.close();                                   //  STEP 7:  Close the connection and the statement.
                conn.close();
            }
        }  catch (SQLException | ClassNotFoundException e) {  e.printStackTrace();  }
        //  The connection and statement will be properly closed when the block exits, even if an exception is thrown.
    }


    public static void process(ResultSet rset)
    {
        int row = 1;
        try {
            while (rset.next()) {
                System.out.println(row + ": " + rset.getString(3) + ", " + rset.getString(2));
                row++;
            }
        }  catch (SQLException e) {  e.printStackTrace();  }
    }
}