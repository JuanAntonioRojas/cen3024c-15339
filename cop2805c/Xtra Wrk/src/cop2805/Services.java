package cop2805;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static cop2805.Constants.*;
import static cop2805.Tools.*;
import static cop2805.Tools.logEvent;

import java.io.*;


public class Services
{
    //  CONNECT THE SERVER TO THE DATABASE

    static class DBConn {
        private static Connection conn;
        public static void connect2DB(boolean log) throws SQLException
        {
            //  wait 2 seconds for the Server to start, and for MySQL to start
            try { Thread.sleep(2000);  }
            catch (InterruptedException e) { throw new RuntimeException(e); }

            //  CONNECT TO DB:
            conn = DriverManager.getConnection(MYSQLURL, USER, PSWD);   // Initialize connection

            //  Get connecting port. Usually 3306
            String databaseUrl = conn.getMetaData().getURL();
            int port = extractPortFromUrl(databaseUrl);
            if (log) logDBConn(port);
        }


        //  Access the connection if needed
        public static Connection getConn() { return conn; }



        //  Inform the user which port is being used. Part 1
        private static int extractPortFromUrl(String databaseUrl) {
            String[] parts = databaseUrl.split(":");

            // Assuming the standard JDBC URL format: jdbc:mysql://hostname:port/dbname
            if (parts.length >= 3) {
                try {
                    return Integer.parseInt(parts[3].split("/")[0]);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            return -1;  // Return -1 if port cannot be determined
        }


        //  Inform the user which port is being used. Part 2
        private static void logDBConn(int port) throws SQLException {
            Statement stat = conn.createStatement();                              // Initialize statement
            logEvent(" Server connected to the database on port " + port);
        }
    }









//  Not going to use the following code... TSR error with Xampp

    //  1: DETECT THE OS OF THE USER
    String os = System.getProperty("os.name").toLowerCase();



    //  2: START THE LOCAL SERVER.  PART 1

    public static void StartServices(String os)
    {
        if (os.contains("win"))
        {
            if     (isXamppInstalled()) { StartWinXAMPP(); }
            else if (isWampInstalled()) { StartWinWAMP(); }
            else { System.out.println("No known server software detected on Windows."); }
        }
        else if (os.contains("nix") || os.contains("nux") || os.contains("mac")) {
            if (isMampInstalled()) { StartMAMP(); }
            else { System.out.println("No known server software detected on macOS."); }
        }
        else { prtln("Unknown operating system."); }
    }



    //  3: DETECT WHICH LOCALHOST SERVER IS INSTALLED, FOR PART 1

    public static boolean isXamppInstalled() {
        return new File("C:\\xampp\\xampp-control.exe").exists();
    }

    // Detect WAMP on Windows
    public static boolean isWampInstalled() {
        return new File("C:\\wamp64\\wampmanager.exe").exists();
    }

    // Detect MAMP on macOS
    public static boolean isMampInstalled() {
        return new File("/Applications/MAMP/MAMP.app").exists();
    }



    //  4: START THE SERVER SERVICES.  PART 2

    // START XAMPP Control Panel and Apache and MySQL on Windows
    public static void StartWinXAMPP() {
        // Path to the XAMPP Control Panel executable
        ProcessBuilder builder = new ProcessBuilder("C:\\xampp\\xampp-control.exe");
        builder.redirectErrorStream(true); // Redirect error stream to output stream
        builder.redirectOutput(ProcessBuilder.Redirect.to(new File("xampp_control.log"))); // Redirect output to a log file

        //  Start XAMPP
        try {
            Process process = builder.start();
            prtln("XAMPP Control Panel started.");

            /*/  MINIMIZE THE XAMPP CONTROL PANEL using PowerShell
            String command = "powershell -Command \"(Get-Process xampp-control).MainWindowHandle | ForEach-Object " +
                    "{Add-Type '[DllImport(\"user32.dll\")] public static extern bool " +
                    "ShowWindow(int hWnd, int nCmdShow);' -Name WinAPI -Namespace Win32;" +
                    "[WinAPI]::ShowWindow($_, 6)}\"";  //  6 is the parameter for minimizing the window in PowerShell.
            ProcessBuilder minimizePB = new ProcessBuilder("cmd.exe", "/c", command);
            minimizePB.start();  //  Didn't work... Why?
            */

            //  START APACHE
            StartWinApacheMySQL();
            prtln("The Apache Server is up.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    //  START WAMP on Windows
    public static void StartWinWAMP() {
        try {
            ProcessBuilder builder = new ProcessBuilder("C:\\wamp64\\wampmanager.exe");
            builder.redirectErrorStream(true);
            Process process = builder.start();
            System.out.println("WAMP started.");

            //  Start MySQL
            StartWinApacheMySQL();
        } catch (IOException e) {
            throw new RuntimeException("Failed to start WAMP.", e);
        }
    }


    // START MAMP on a Mac
    public static void StartMAMP() {
        try {
            // Start MAMP application on macOS
            ProcessBuilder mampBuilder = new ProcessBuilder("open", "/Applications/MAMP/MAMP.app");
            mampBuilder.redirectErrorStream(true);
            Process mampProcess = mampBuilder.start();

            System.out.println("MAMP started on macOS.");

            // You can wait for MAMP to start, or give it some time before starting services
            Thread.sleep(5000); // Sleep for 5 seconds (adjust if necessary)

            // Start Apache using MAMP's scripts (optional, as MAMP might handle this automatically)
            ProcessBuilder apacheBuilder = new ProcessBuilder("sudo", "/Applications/MAMP/Library/bin/apachectl", "start");
            apacheBuilder.redirectErrorStream(true);
            Process apacheProcess = apacheBuilder.start();

            // Start MySQL using MAMP's scripts (optional, as MAMP might handle this automatically)
            ProcessBuilder mysqlBuilder = new ProcessBuilder("sudo", "/Applications/MAMP/Library/bin/mysqld_safe", "--datadir=/Applications/MAMP/db/mysql");
            mysqlBuilder.redirectErrorStream(true);
            Process mysqlProcess = mysqlBuilder.start();

            //StartMacLinApacheMySQL();  //  not sure if line above will start it or not.

            System.out.println("Apache and MySQL started via MAMP on macOS.");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start MAMP or its services on macOS.", e);
        }
    }



    //  5: START APACHE AND MYSQL ON WINDOWS

    public static void StartWinApacheMySQL()
    {
        // Create a ProcessBuilder for starting XAMPP
        ProcessBuilder builder = new ProcessBuilder("C:\\xampp\\xampp_start.exe");
        builder.redirectErrorStream(true); // Redirect error stream to output stream
        builder.redirectOutput(ProcessBuilder.Redirect.to(new File("xampp_start.log"))); // Redirect output to a log file

        // Start the process
        try {
            Process process = builder.start();
            prtln("Apache and MySQL started via XAMPP (minimized and output discarded).");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    // Method to START Apache and MySQL on Mac/Linux (untested yet)
    public static void StartMacLinApacheMySQL() {
        try {
            // Start Apache on Mac/Linux using ProcessBuilder
            ProcessBuilder apacheBuilder = new ProcessBuilder("sudo", "launchctl", "load", "/Library/LaunchDaemons/com.mamp.apache.plist");
            apacheBuilder.redirectErrorStream(true);
            Process apacheProcess = apacheBuilder.start();

            // Start MySQL on Mac/Linux using ProcessBuilder (example command)
            ProcessBuilder mysqlBuilder = new ProcessBuilder("sudo", "launchctl", "load", "/Library/LaunchDaemons/com.mamp.mysql.plist");
            mysqlBuilder.redirectErrorStream(true);
            Process mysqlProcess = mysqlBuilder.start();

            System.out.println("Apache and MySQL started via MAMP on Mac/Linux.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start Apache or MySQL on Mac/Linux.", e);
        }
    }







    //  STOP THE XAMPP SERVICES (Apache and MySQL)
    public static void stopWinApacheMySQL(String os) {
        try {
            // Command to stop XAMPP services
            ProcessBuilder builder = new ProcessBuilder("C:\\xampp\\xampp_stop.exe");
            Process stopProcess = builder.start();
            stopProcess.waitFor(); // Optional: wait for the process to complete

            prtln("XAMPP services stopped.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Failed to stop XAMPP services: " + e.getMessage());
        }
    }


    //  STOP THE MAMP SERVICES (Apache and MySQL) on a Mac/Linux box (untested yet)
    public static void StopMacLinApacheMySQL() {
        try {
            // Stop Apache on Mac/Linux using ProcessBuilder
            ProcessBuilder apacheBuilder = new ProcessBuilder("sudo", "launchctl", "unload", "/Library/LaunchDaemons/com.mamp.apache.plist");
            apacheBuilder.redirectErrorStream(true);
            Process apacheProcess = apacheBuilder.start();

            // Stop MySQL on Mac/Linux using ProcessBuilder
            ProcessBuilder mysqlBuilder = new ProcessBuilder("sudo", "launchctl", "unload", "/Library/LaunchDaemons/com.mamp.mysql.plist");
            mysqlBuilder.redirectErrorStream(true);
            Process mysqlProcess = mysqlBuilder.start();

            System.out.println("Apache and MySQL stopped via MAMP on Mac/Linux.");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to stop Apache or MySQL on Mac/Linux.", e);
        }
    }


}



























