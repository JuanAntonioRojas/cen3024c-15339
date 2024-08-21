package cop2805;

import static cop2805.Constants.*;
import static cop2805.Services.*;
import static cop2805.Tools.logEvent;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import java.sql.*;    //  STEP 1


public class Server
{
    public static BufferedReader brINPUT;
    public static PrintWriter pwOUTPUT;


    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORTCLIENT))
        {
            logEvent(" The Server is now up, and listening on port " + PORTCLIENT);


            // CONNECT TO DB, and log it.

            DBConn.connect2DB(true);        //  from Services.java, line 19



            //  LISTEN FOR CLIENT CONNECTION

            while (true) {
                Socket socket = serverSocket.accept();

                //  EXTRA LITTLE NOTHING'S

                //  Get the port on the client's side of the connection
                int clientPort = socket.getPort();

                //  Get the port on the server side (the port the server is listening on)
                int serverPort = socket.getLocalPort();
                logEvent(" A client just connected on our port: " + serverPort + ", from their port: " + clientPort);

                new ClientHandler(socket).start();              //  go to line 96
            }
/*
The following piece of code will start Xampp (on windows) or Mamp (on a Mac), as well as Apache and MySQL. I decided not to use it
because closing xampp after we exit the program has to be done manually. Windows keeps Xampp running as a Windows Service.
The same as the old TSR (Terminate and Stay Resident), which were a problem (memory hogs).

            //  Detecting the OS
            String os = System.getProperty("os.name").toLowerCase();

            // Start the Services class
            StartServices(os);  //  had too many problems closing all the instances it opens every time I invoke it.
*/
        }
        catch (IOException ex) { ex.printStackTrace(); }
        catch (SQLException e) { throw new RuntimeException("Failed to initialize database connection" + e.getMessage()); }

    }





    //  CODE TO RECEIVE THE PHOTO FROM CLIENT

    //  separation of concerns (and ports) between text data handling and image uploads
    public static void photoUpload()
    {
        try (ServerSocket serverSocket = new ServerSocket(PORTIMAGES)) {
            System.out.println("Server is listening on port " + PORTIMAGES);

            // Ensure the directory exists
            File imgDir = new File("img/");
            if (!imgDir.exists()) {
                imgDir.mkdirs(); // Create the directory if it doesn't exist
            }

            while (true)
            {
                try (Socket socket = serverSocket.accept();
                     InputStream in = socket.getInputStream();
                     DataInputStream dis = new DataInputStream(in)) {

                    // Read the file name and size
                    String fileName = dis.readUTF();
                    long fileSize = dis.readLong();
                    File file = new File("img/" + fileName);

                    // Read the file contents and save to disk
                    try (FileOutputStream fos = new FileOutputStream(file)) {
                        byte[] buffer = new byte[4096];
                        int bytesRead;
                        long totalBytesRead = 0;
                        while (totalBytesRead < fileSize && (bytesRead = dis.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                            totalBytesRead += bytesRead;
                        }
                        fos.flush();
                        System.out.println("File uploaded successfully: " + fileName);
                    } catch (IOException e) {
                        System.err.println("Error saving file: " + e.getMessage());
                    }
                } catch (IOException e) {
                    System.err.println("Error handling client connection: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error starting server: " + e.getMessage());
        }
    }








    //  CODE TO RECEIVE THE DATA STREAM FROM THE CLIENT

    static class ClientHandler extends Thread    //  from line 46
    {
        public static Socket client;
        private BufferedReader brINPUT;
        private PrintWriter pwOUTPUT;
        public ClientHandler(Socket socket) { this.client = socket; }


        //  Now the Server handles the Client code

        @Override
        public void run() {
            try (BufferedReader brINPUT = new BufferedReader(new InputStreamReader(client.getInputStream()));
                 PrintWriter pwOUTPUT = new PrintWriter(new OutputStreamWriter(client.getOutputStream()), true))
            {
                this.brINPUT = brINPUT;
                this.pwOUTPUT = pwOUTPUT;


                //  Get the data from the client
                String ReceivedFormClient = brINPUT.readLine();


                processClientRequest(ReceivedFormClient);

            } catch (IOException | SQLException e) {
                logEvent("Error handling client: " + e.getMessage());
            } finally {
                closeClientConnection();
            }
        }

        private void closeClientConnection() {
            try {
                client.close();
                logEvent("A client has disconnected from port " + client.getPort());
            } catch (IOException e) {
                logEvent("Error closing client connection: " + e.getMessage());
            }
        }



        private void processClientRequest(String ReceivedFormClient) throws SQLException {

            //  INSERT DATA IN THE DATABASE

            if (ReceivedFormClient.startsWith("null")) {
                //  print what the client sent:
                logEvent("The client has sent:\n" + ReceivedFormClient + "\n");

                //  Create SQL INSERT statement
                String insertData = "INSERT INTO `users` VALUES (" + ReceivedFormClient + ")";

                //  INSERT DATA.  (Connect to DB & Execute)
                InsertData(insertData);
            }




            //  READ ALL THE DATA FROM THE DATABASE

            else if (ReceivedFormClient.startsWith("read")) {
                //  print what the client sent:
                logEvent("The client has sent a " + ReceivedFormClient + " request.\n");

                //  Create SQL INSERT statement
                String readAllData = "SELECT * FROM `users`";

                //  PRESENT THE DATA.  (Connect to DB & Execute)
                SendAllRecordsToClient(readAllData, pwOUTPUT);
            }




            //  SEARCH FOR DATA

            else if (ReceivedFormClient.startsWith("WHERE")) {
                //  print what the client sent:
                logEvent("The client has sent a search request for: " + ReceivedFormClient + ".\n");

                //  Create SQL INSERT statement
                String data2BSearched = "SELECT * FROM `users` " + ReceivedFormClient;

                //  Show server what we're looking for, before we look for them
                logEvent("We're going to " + data2BSearched);

                //  Go look for them. Line 285
                SendFoundRecordsToClient(data2BSearched);
            }
        }
    }






    //  INSERT

    public static void InsertData(String insertion)
    {
        //  Class.forName(CLASSFORNAME);                    //  STEP 2: Load & register the MySql driver.
        //  No longer needed. The driver manager will automatically load the driver when DriverManager.getConnection() is called.

        try (Connection conn = DBConn.getConn();            //  STEP 3: Initialize the connection
             Statement statm = conn.createStatement() )     //  STEP 4: Create a working statement
        {
            statm.executeUpdate(insertion);                  //  STEP 5: Execute the insertion

            //  Result from insert;
            logEvent("This new data has been added to DB.");    //  STEP 6: Process the results from the ResultSet. (line 65)
            statm.close();                                   //  STEP 7: Close the connection and the statement.
            conn.close();


            //  SEND BACK TO THE CLIENT FOR POPUP.

            //  Extract First and Last names from the received String
            String[] values = insertion.split(",");

            String Title = values[1].trim().replace("'", "");
            String First = values[2].trim().replace("'", "");
            String Middl = values[3].trim().replace("'", "");
            String LastN = values[4].trim().replace("'", "");

            // Send back to the client the First and Last names
            pwOUTPUT.println(Title + " " + First + " " + Middl + " " + LastN);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







    //  SENT TO A TABLE

    public static void SendAllRecordsToClient(String readAllData, PrintWriter output) throws SQLException
    {
        try (Connection conn = DBConn.getConn();            //  STEP 3: Initialize the connection
             Statement statm = conn.createStatement() )     //  STEP 4: Create a working statement
        {
            //  GO FETCH THE DATA

            ResultSet tableData = statm.executeQuery(readAllData);

            //  Create a long string with all 18 columns and as many rows as users
            StringBuilder dataBuilder = new StringBuilder();
            int columnCount = tableData.getMetaData().getColumnCount();

            // Iterate through the result set
            while (tableData.next())
            {
                for (int i = 1; i <= columnCount; i++)              // For each row, iterate through the columns
                {
                    String columnValue = tableData.getString(i);
                    dataBuilder.append(columnValue).append(", ");   // Append each column's value
                }
                dataBuilder.append("\n");                           // Newline for each row
            }

            logEvent(dataBuilder.toString());                       //  Print the complete matrix (rows and cols) in the console
            output.println(dataBuilder.toString());                 //  Send the complete matrix (rows and cols) to the client
        }
        catch (SQLException e) { e.printStackTrace(); }
    }







    //  SEARCH

    public static void SendFoundRecordsToClient(String data2BSearched) throws SQLException
    {
        try (Connection conn = DBConn.getConn();            //  STEP 3: Initialize the connection
             Statement statm = conn.createStatement() )     //  STEP 4: Create a working statement
        {
            //  GO FETCH THE DATA

            ResultSet resultSet = statm.executeQuery(data2BSearched);

            //  What the server found:
            StringBuilder found = new StringBuilder();
            int columnCount = resultSet.getMetaData().getColumnCount();

            // Iterate through the result set
            while (resultSet.next())
            {
                for (int i = 1; i <= columnCount; i++)  // For each row, iterate through the columns
                {
                    String columnValue = resultSet.getString(i);
                    found.append(columnValue).append(", ");  // Append each column's value
                }
                found.append("\n");  // Newline for each row
            }
            logEvent(found.toString());



            //  SEND RESULT ROW(S) TO THE CLIENT:

            //  Create a long string with all 18 columns and as many rows as users
            StringBuilder dataBuilder = new StringBuilder();

            while ( resultSet.next() )
            {
                dataBuilder.append(resultSet.getString("First")).append(",");
                dataBuilder.append(resultSet.getString("Middl")).append(",");
                dataBuilder.append(resultSet.getString("LastN")).append(",");
                dataBuilder.append(resultSet.getString("Photo")).append(",");
                dataBuilder.append(resultSet.getString("Phon1")).append(",");
                dataBuilder.append(resultSet.getString("Phon2")).append(",");
                dataBuilder.append(resultSet.getString("Addrs")).append(",");
                dataBuilder.append(resultSet.getString("City" )).append(",");
                dataBuilder.append(resultSet.getString("State")).append(",");
                dataBuilder.append(resultSet.getString("ZipCd")).append(",");
                dataBuilder.append(resultSet.getString("Email")).append(",");
                dataBuilder.append(resultSet.getString("SpMon")).append(",");
                dataBuilder.append(resultSet.getString("SpDay")).append(",");
                dataBuilder.append(resultSet.getString("YearB")).append(",");
                dataBuilder.append(resultSet.getString("Gendr")).append(",");
                dataBuilder.append(resultSet.getString("Marit")).append(",");
                dataBuilder.append(resultSet.getString("Paswd")).append(",");
                dataBuilder.append(resultSet.getString("Confr")).append(",");
                dataBuilder.append(resultSet.getString("Notif")).append(",");
                dataBuilder.append(resultSet.getString("Notes")).append(",");

                //  Create as many rows (new lines) as there are users
                dataBuilder.append("\n");
            }
            pwOUTPUT.println(dataBuilder.toString());  //  Send the complete matrix (rows and cols) to the client

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


























