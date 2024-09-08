// ==============================================
//  CEN3024C Assignment 2
//  Author: Tony Rojas
//  Version 1: 9/4/24. (Just the min required )
// ==============================================

/**
*  GOAL: Write the code to implement the Library Management System, according to what we wrote in our SDLC document.
*  As per this assignment, the required user features that we must implement are:
*      1.	Users must be able to add new books to the collection from a “text file.”
*      2.	Users must be able to remove a book from the collection using its ID number.
*      3.	Users must be able to see a list of all books currently in the collection.
*      4.	Users must interact with the system using an on-screen menu.
*
*  The "books.txt" file will contain the data for the books, as a comma separated values (CSV) file.
*  Its format will be: ID, Title, Author
*  e.g.: 1, To Kill a Mockingbird, Harper Lee
*        2, 1984, George Orwell
*        3, The Great Gatsby, F. Scott Fitzgerald
*
*  GOAL DESCRIPTION:
*  1: We need to interact with the system using a CLI menu, that will allow the user to choose between these 3 options: add, remove, view.
*  2: To use an external file to add books, we need to create a class that reads a csv file ("books.txt"),
*    with the appropriate Path to it (next to the src folder). This code will insert all the books into the “Books” table of
*    the “BooksDB” database, line by line (row by row), given the “ID, Name, Author” as columns headers.
*  3: To be able to remove a book from the collection, we will change the type of SQL query that we’re using.
*    We can do this by using its ID number, in the WHERE clause (we need to use a "delete" query using the book ID as identifier).
*  4: To be able to see a list of all books currently in the collection we can simply change the SQL query again to do a “select *” from the table.
*
*/



import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;    //  STEP 1: Import the necessary packages to work with the database.



public class Main {

    public static void println(String txt) {
        System.out.println(txt);
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        println("WELCOME TO OUR LIBRARY MANAGEMENT SYSTEM (LMS)");
        println("Please select one of the following options:");

        //  Menu options:
        println("- A: Add books\t\t\tadds books to the database from a CSV file");
        println("- D: Delete a book\t\t\tdeletes a book, given its ID number");
        println("- V: View all books\t\t\tdisplays all books in the database");
        println("- X: Exit\t\t\t\t\texits the program, closing all the open connections");


        //  Set up to acquire the user selection with a "scn" (scanner) system variable.
        Scanner scn = new Scanner(System.in);
        String inp = scn.next();                //  This way, the string input is saved in the "inp" var.

        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/booksdb";
        String usr = "root";
        String pwd = "";

        //  now we need to "sanitize" and sort the input
        switch (inp.toUpperCase()) {
            case "A": addBooks(url, usr, pwd);   break;
            case "D": deleteBook(url, usr, pwd); break;
            case "V": viewBooks(url, usr, pwd);  break;
            case "X": exit();                    break;
            default: println("Invalid input. Please enter only alphabetic characters."); // FIXME: This is not working as expected.
        }
    }






    private static void addBooks(String url,String usr,String pwd) {
        // First, we need to define the path of the "books.txt" file, relative to our src folder
        String filePath = "books.txt";    //  TODO: Have the interface prompt the user for the file path.

        // Once the File method locates the "books.txt" file, we assign it to the "file" variable.
        File file = new File(filePath);

        //  Now, we need to create a new BufferedReader object to read the file.
        //  But to avoid a sudden crash (and a confusing 'printStackTrace' output), we need to check if the file exists first,
        //     if not, we print an error message: It could be a "FileNotFoundException" or a "IOException", so we need to catch both.

        if (file.exists()) {                                     //  If the file exists, we can proceed to read it; if not, we print an error message.
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;                                     //  line doesn't need to be an outside static, it's an encapsulated local variable.
                while ((line = br.readLine()) != null) {         //  Now, we can use this line variable to access the contents of "books.txt".

                    // But first, we need to split the line (or "destructure" it) into a string array, taking the comma as the delimiter (CSV).
                    String[] column = line.split(",");

                    int ID = Integer.parseInt(column[0]);       //  We need to convert the ID to an integer, because it came as a string.
                    String Title = column[1];
                    String Author = column[2];

                    //  Prepare and call the "transactWithDB" method, to insert this data into the database.
                    String qry = "INSERT INTO `books` VALUES (" + ID + ", '" + Title + "', '" + Author + "')";
                    transactWithDB(url, usr, pwd, qry);
                }
            } catch (IOException | SQLException | ClassNotFoundException e) {
                println("Error reading file: " + e.getMessage());
            }
        } else {
            println("File not found: " + file.getAbsolutePath());
        }
    }








    //  Database connection
    private static void transactWithDB(String url, String usr, String pwd, String qry) throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.cj.jdbc.Driver");                   //  STEP 2: Load the JDBC driver & register the MySql driver

        //  With this method (try-with), we don't need to manually close the connection. It's automatically closed.
        try (Connection conn = DriverManager.getConnection(url, usr, pwd);     //  STEP 3: get the connection ready
             PreparedStatement prepStatmt = conn.prepareStatement(qry)) {      //  STEP 4: Create a prepared statement

            if (qry.trim().toUpperCase().startsWith("SELECT")) {               //  STEP 5.1: Check if the query is a SELECT query
                try (ResultSet resultSet = prepStatmt.executeQuery(qry)) {     //  Execute the select query and...
                    displayList(resultSet);                                    //  Display the results
                }
            } else {
                int rowsAffected = prepStatmt.executeUpdate();                 //  STEP 5.2: Execute the other queries (INSERT, UPDATE, DELETE)
                println("Rows affected: " + rowsAffected);
            }
        } catch (SQLException e) {
            println("Failed to connect to the database.");
            e.printStackTrace();
        }

    }




    private static void deleteBook(String url, String usr, String pwd) throws SQLException, ClassNotFoundException {

        //  Set up to acquire the ID from the user with a "scn" (scanner).
        Scanner scn = new Scanner(System.in);
        println("Enter the ID of the book to delete:");
        int ID = scn.nextInt();                //  The user input number is saved in the inp var.

        //  Prepare and call the "transactWithDB" method, to delete this book from the database.
        String qry = "DELETE FROM `Books` WHERE ID =" + ID + ";";
        transactWithDB(url, usr, pwd, qry);
    }





    private static void viewBooks(String url, String usr, String pwd) throws SQLException, ClassNotFoundException {

        //  Prepare and call the "transactWithDB" method, to delete this book from the database.
        String qry = "SELECT * FROM `Books`;";
        transactWithDB(url, usr, pwd, qry);
    }



    private static void displayList(ResultSet resultSet) throws SQLException
    {
        while (resultSet.next())
        {
            int id = resultSet.getInt("ID");
            String title = resultSet.getString("Title");
            String author = resultSet.getString("Author");

            System.out.printf("ID: %d, Title: %s, Author: %s%n", id, title, author);
        }
    }




    private static void exit() {
        // To exit the program
        System.exit(0);   //   "0" means a successful exit of the program.
    }
}




























