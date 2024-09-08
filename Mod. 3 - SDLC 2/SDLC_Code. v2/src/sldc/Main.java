package sldc;

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
 *      e.g.: 1, To Kill a Mockingbird, Harper Lee
 *            2, 1984, George Orwell
 *            3, The Great Gatsby, F. Scott Fitzgerald
 *
 *  GOAL DESCRIPTION:
 *      1: We need to interact with the system using a CLI menu, that will allow the user to choose between these 3 options: add, remove, view.
 *      2: To use an external file to add books, we need to create a class that reads a csv file ("books.txt"),
 *        with the appropriate Path to it (next to the src folder). This code will insert all the books into the “Books” table of
 *        the “BooksDB” database, line by line (row by row), given the “ID, Name, Author” as columns headers.
 *      3: To be able to remove a book from the collection, we will change the type of SQL query that we’re using.
 *        We can do this by using its ID number, in the WHERE clause (we need to use a "delete" query using the book ID as identifier).
 *      4: To be able to see a list of all books currently in the collection we can simply change the SQL query again to do a “select *” from the table.
 *
 *
 *  VERSION 2:
 *      1: In this version we interact with the system using a Window Menu (but in reality it's a CLI menu) that will allow
 *          the user to choose 1 of 4 options: add a book, add a list of books, remove a book, view all books, or exit
 *
 *      2: If the user chooses to add a book, the program will prompt the user for the book's Title, and Author, in a GUI window (circa 1980s).
 *         2.1: The program will then insert the book into the “Books” table of the “BooksDB” database, using the book's Title,
 *            and Author as column headers. The ID will be generated automatically.
 *
 *      3: If the user chooses to add a list of books, the program will prompt the user for the file path, and then insert the books
 *            from that file into the “Books” table of the “BooksDB” database, using the book's Title, and Author as column headers.
 *
 *      4: If the user chooses to remove a book, the program will prompt the user for the book's ID, and then delete the book from the database.
 *
 *      5: If the user chooses to view the list of all books currently in the collection, the program will display a list of all books currently in the collection.
 *
 *      6: If the user chooses to exit, the program will exit.
 *
 *  IMPORTANT:
 *      The cursor and user input can only happen in the console. The cursor and user input cannot happen directly in the input fields.
 *      Java does not have built-in support for terminal cursor manipulation like Python’s curses or C’s ncurses.
 *      However, we could achieve something similar using libraries like Jansi or Lanterna, which provide control over the terminal cursor.
 *      (import com.googlecode.lanterna.*), but for now we will use the CLI menu at the bottom of the screen.
 *
 *
 *  GOAL 2:
 *      Create a login userName and Pwd, with a table that contains the "hashed" pwds.
 *
 *  BUG REPORT:
 *      while reading the title, the field box border is not sized properly.
 *      For the popup windows, I fixed it by using a fixed width (box-length=64): Win.java line 78
 *      For the table resultSet, I fixed the width issue by using a substring (line 378)
 *      For the viewing of only 10 books, I fixed it by using a SECOND "offset" query. (line 378)
 *      Not sure if the DB connection was closed properly.
 *
 *  TO DO:
 *      The little 'T' in the middle of the top border for the table, separating the columns.
 *      String align="right" on the ID column.
*/

/*
OMG was this hard!
I did one like this for you last year, but it was a lot easier
(It was on Generics... And I reused some of that code)
I didn't have to deal with the pagination (what a nightmare) and the sql queries.
And the windows don't even work, unless I import the Jansi or lanterna libraries. May be later...
Then, why go through all that trouble?
I guess the reason I'm doing it is like a prep for the final project, when we have Spring Boot, SQL, and Java.

What?
I created the jar file, and all I get is garbage. But...Why?
It works fine on the console. Why not on the cmd prompt?
That's it. I'm outta here. I'll never try to recreate a DOS windows again!
Phew!
*/



import static java.lang.String.valueOf;
import static sldc.Tools.*;
import static sldc.Win.*;


import java.util.Scanner;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;                              //  STEP 1: Import the necessary packages to work with the database.







public class Main {



    public static void main(String[] args) throws SQLException, ClassNotFoundException
    {
        clearScreen();

        Splah_Screen();

        //  Test connection
        // Database connection parameters
        String url = "jdbc:mysql://localhost:3306/booksdb";
        String usr = "root";
        String pwd = "";

        testConnection(url, usr, pwd);

        menu(url, usr, pwd);
    }

    public static void menu(String url, String usr, String pwd) throws SQLException, ClassNotFoundException
    {



        println("\nPlease select a letter from the above window menu:");

        //  Menu options:
        //  This time we converted the special letter to bold, italic and underlined.  Tools, line 32
        println(boldItalUnder(5, 3, "A: Add a book\t\t\t\tadds a single book to the database from a popup window"));
        println(boldItalUnder(5, 9, "L: Add a List of books\t\tadds a list of books to the database from a CSV file"));
        println(boldItalUnder(5, 3, "D: Delete a book\t\t\tdeletes a book, giving its ID number in a popup window"));
        println(boldItalUnder(5, 3, "V: View all books\t\t\tdisplays all books. Press N for the next 10, or P for the previous 10"));
        println(boldItalUnder(5, 3, "X: Exit Program\t\t\tcloses the open connections and exits"));
        prt(" -> ");

        //  Set up to acquire the user selection with a "scn" (scanner) system variable.
        Scanner scn = new Scanner(System.in);       //  FIXME: need to improve this method, to make it more secure. DONE: Line 135.
        String selection = scn.next();              //  This way, the string input is saved in the "inp" var.



        //  now we need to "sanitize" and sort the input
        switch (valueOf(selection.toUpperCase().charAt(0))) {  //  take only the first character of the input
            case "A": addABook(url, usr, pwd);      break;
            case "L": addList(url, usr, pwd);       break;
            case "D": deleteBook(url, usr, pwd);    break;
            case "V": viewBookList(url, usr, pwd);  break;
            case "X": exit();                       break;
            default: println("Invalid input. Please enter only a menu option.");
        }
    }





    //  DATABASE CONNECTION

    private static void testConnection(String url, String usr, String pwd) {
        try {
            Connection conn = DriverManager.getConnection(url, usr, pwd);
            if (conn != null) {
                println("Connected to the database!");
            } else {
                println("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            println("Error connecting to the database: " + e.getMessage());
        }
    }



    //  DATABASE TRANSACTIONS

    private static ResultSet transactWithDB(String url, String usr, String pwd, String qry) throws SQLException, ClassNotFoundException
    {
        ResultSet resultSet = null;

        Class.forName("com.mysql.cj.jdbc.Driver");                //  STEP 2: Load the JDBC driver & register the MySql driver

        //  With this method (try-with), we don't need to manually close the connection. It's automatically closed.
        try {
            Connection conn = DriverManager.getConnection(url, usr, pwd);   //  STEP 3: get the connection ready
            PreparedStatement prepStatmt = conn.prepareStatement(qry);      //  STEP 4: Create a prepared statement

            if (qry.trim().startsWith("SELECT")) {                          //  STEP 5.1: Check if the query is a SELECT query
                resultSet = prepStatmt.executeQuery();                      //  Execute the select query and later return it.
            } else {
                prepStatmt.executeUpdate();                                 //  STEP 5.2: Execute the other queries (INSERT, UPDATE, DELETE)
            }
        } catch (SQLException e) {
            println("Failed to connect to the database.");
            e.printStackTrace();
        }

        return resultSet; // Return the ResultSet or null if it's not a SELECT query
    }








//  ADD A BOOK

    // First, we need to define the path of the "books.txt" file, relative to our src folder

    // Once the File method locates the "books.txt" file, we assign it to the "file" variable.

    //  Now, we need to create a new BufferedReader object to read the file.
    //  But to avoid a sudden crash (and a confusing 'printStackTrace' output), we need to check if the file exists first,
    //     if not, we print an error message: It could be a "FileNotFoundException" or a "IOException", so we need to catch both.

    private static void addABook(String url, String usr, String pwd) throws SQLException, ClassNotFoundException
    {

        //  Now we go to the GUI (win.java file), and get the title and author from the user
        String[] Title_and_Author = get_Title_Author();

        // Next, we define the title and author from the user input
        String title = Title_and_Author[0];
        String author = Title_and_Author[1];

        //  The SQL query to insert the title and author into the database
        String qry = "INSERT INTO `books` (Title, Author) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usr, pwd);
             //  A prepared statement helps prevent SQL injection attacks (by treating user input as data, not as SQL code)
             PreparedStatement prepStatmt = conn.prepareStatement(qry)) {

            //  Next we set the parameters for the title and author
            prepStatmt.setString(1, title);
            prepStatmt.setString(2, author);

            //  Finally, we execute the insert query
            int rowsInserted = prepStatmt.executeUpdate();
            if (rowsInserted > 0) {
                println("A new book was inserted successfully!");
            }
        } catch (SQLException e) {
            println("Error inserting data: " + e.getMessage());
        }

        //  We display the menu after insertion, in case the user wants to add another book or books.
        menu(url, usr, pwd);
    }








//  ADD A LIST OF BOOKS

    private static void addList(String url,String usr,String pwd) throws SQLException, ClassNotFoundException
    {
        String source = get_Source_List();      //  Line 188 in Win.java

        // First, we need to define the path of the "books.txt" file, relative to our src folder
        String filePath = source;

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
                    String qry = "INSERT INTO `books` (Title, Author) VALUES ('" + Title + "', '" + Author + "')";
                    transactWithDB(url, usr, pwd, qry);
                    println("Book list added successfully!");
                }
            } catch (IOException | SQLException | ClassNotFoundException e) {
                println("Error reading file: " + e.getMessage());
            }
        } else {
            println("File not found: " + file.getAbsolutePath());
        }

        //  We display the menu after insertion, in case the user wants to add another book or books.
        menu(url, usr, pwd);
    }







//  DELETE BOOK

    private static void deleteBook(String url, String usr, String pwd) throws SQLException, ClassNotFoundException {

        //  Now we go to the GUI, and get the book ID from the user
        int ID = get_ID();

        //  First, get the name of the book that is going to be deleted, from the database.
        String selQry = "SELECT * FROM `Books` WHERE ID = " + ID + ";";
        try(ResultSet resultSet = transactWithDB(url, usr, pwd, selQry)) {
            if (resultSet != null && resultSet.next()) {                    // Check if there is a result
                //  Then, we extract the title of the book from the result set
                String Title = resultSet.getString("Title");  //  getString(2); // 2 is the index of the Title column in the result set

                //  Prepare and call the "transactWithDB" method again, to delete this book from the database.
                String delQry = "DELETE FROM `Books` WHERE ID =" + ID + ";";
                transactWithDB(url, usr, pwd, delQry);
                println("Book number " + ID + "(\"" + Title + "\") has been deleted!");
            }
        }

        //closeConnection(conn, prepStatmt)

        //  We display the menu after insertion, in case the user wants to add another book or books.
        menu(url, usr, pwd);
    }







//  VIEW BOOK LIST

    private static void viewBookList(String url, String usr, String pwd) throws SQLException, ClassNotFoundException {


        //  PAGINATION

        //  1. Number of books per page
        int pageSize = 10;

        //  2. Determine the page number you want to start the fetching from
        int pageNumber = 1;


        //  3. We don't know how many pages of 10 books we need.
        //  So, to get the total number of books (that are in the db) we do a "count" query.

        String numBooksQry = "SELECT COUNT(*) AS total FROM `books`";
        ResultSet resultSet1 = transactWithDB(url, usr, pwd, numBooksQry);

        int totNumBooks = 0;
        if (resultSet1.next()) {
            totNumBooks = resultSet1.getInt("total");
        }


        //  3. Then, we need to get the total number of pages.
        int totNumPages = (int) Math.ceil((double) totNumBooks / pageSize);



        //  3. PRINT THE TABLE:

        printTable(url, usr, pwd, 1, totNumPages);





        //  We display the menu after insertion, in case the user wants to add another book or books.
        menu(url, usr, pwd);
    }









    //  DISPLAY THE TABLE

    //  We pass the page number as a parameter to the method.
    private static void printTable(String url, String usr, String pwd, int pageNumber, int totNumPages) throws SQLException, ClassNotFoundException
    {
        //  Number of books per page
        int pageSize = 10;

        //  5. we calculate offset (from book zero) for each fetching
        int offsetValue = (pageNumber - 1) * pageSize;

        //  6. Now we Query for a specific number of pages, at a specific offset (starting point)
        String selQry = String.format("SELECT * FROM `books` LIMIT %d OFFSET %d;", pageSize, offsetValue);

        //  Now we go to the GUI, and prepare the book List in a 3 column table: ID, Title, Author.
        ResultSet resultSet2 = transactWithDB(url, usr, pwd, selQry);

        if (resultSet2 != null) {

            //  We start with the Wintow top, and then prepare the book List in a 3 column table: ID, Title, Author.
            viewTableTop();


            // Print rows of data
            while (resultSet2.next())
            {


                String ID = String.valueOf(resultSet2.getInt("ID"));
                String Title = resultSet2.getString("Title");
                String Author = resultSet2.getString("Author");


                //  Step 6: Rows for the Table Data.
                //  The rest of the steps are in Win.java in lines 330-390

                Sp(124, 17, 8);    vtLineDouble(17, 3);
                Sp(17, 3, 2);      vtLineSingle(17, 3);    Sp(17, 3, 1);


                //  ID

                prtColor(17, 3, ID);  //  space before the ID

                //  ID takes 4 spaces
                int IDLen = ID.length();
                if (IDLen < 2) { Sp(17, 3, 2); }  //  reminder spaces
                else { Sp(17, 3, 1); }



                //  TITLE, with a max length of 27

                vtLineSingle(17, 3);    Sp(17, 3, 0);  //  space before the Title

                //  Title takes 27 spaces
                int TitLen = Title.length();
                String newTitle;                //  newTitle is the Title with a max length of 27
                if (TitLen > 27) { newTitle = Title.substring(0, 27); }
                else { newTitle = Title; }

                prtColor(17, 3, newTitle);

                // Ensure that remindTitle is never negative
                int remindTitle = 27 - Math.min(TitLen, 27);

                Sp(17, 3, remindTitle);    vtLineSingle(17, 3);  //  reminder spaces



                //  AUTHOR

                Sp(17, 3, 1);  //  space before the Author

                //  Author takes 20 spaces
                int AuthLen = Author.length();
                String newAuthor;                //  newAuthor is the Author with a max length of 20
                if (AuthLen > 22) { newAuthor = Author.substring(0, 22); }
                else { newAuthor = Author; }

                prtColor(17, 3, newAuthor);

                //  reminder spaces
                int remindAuth = 22 - Math.min(AuthLen, 22);    // Ensure that remindAuth is never negative

                Sp(17, 3, remindAuth);    vtLineSingle(17, 3);   Sp(17, 3, 2);


                vtLineDouble(17, 3);   Sp(124, 17, 8);   nl(1);

            }


            //  Step 7:  TABLE'S BOTTOM BORDER

            Sp(124, 17, 8);    vtLineDouble(17, 3);
            Sp(17, 3, 2);    btmBoxSingle(17, 3, 56);   Sp(17, 3, 2);
            vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);





            //  8:  BUTTONS:  PREVIOUS & NEXT

            Sp(124, 17, 8);    vtLineDouble(17, 3);    Sp(17, 3, 7);    //  LEFT BORDER

            prtColor(17, 40, " <-");    prtColor(16, 34, "P");  endCol();   prtColor(17, 40, "REV. ");
            Sp(16, 3, 30);
            prtColor(16, 40, "  ");    prtColor(16, 34, "N");  endCol();    prtColor(16, 40, "EXT-> ");

            Sp(17, 3, 7);     vtLineDouble(17, 3);   Sp(124, 17, 8);    nl(1);//  RIGHT BORDER

            viewTableBottom();



            //  Set up to acquire the user selection: forward (next 10 books) and backward (previous 10).
            println("Previous or Next or Exit? (P/N/X): ");

            Scanner scn = new Scanner(System.in);
            String P_or_N = String.valueOf(scn.next().toUpperCase().charAt(0));  // Take only the first character of the user input.


            //  Wd adjust page number based on user input ("P" for previous, "N" for next)
            if (P_or_N.equals("P") && pageNumber > 1) {  pageNumber--;  }                //  Move to the previous page
            else if (P_or_N.equals("N") && pageNumber < totNumPages) {  pageNumber++;  } //  Move to the next page
            else if (P_or_N.equals("N") && pageNumber == totNumPages) {  menu(url, usr, pwd);  } //  Move to the next page
            else if (P_or_N.equals("X")) {  menu(url, usr, pwd);  } //  Move to the next page


            //  Now we print the table with the updated page number
            printTable(url, usr, pwd, pageNumber, totNumPages);

        }

    }












/*
//  LEGACY:  Display the list of books from the database.
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
*/









    //  STEP 7: Close the connection and the statement.
    private static void closeConnection(Connection conn, Statement stat) throws SQLException {
        stat.close();
        conn.close();
    }

    private static void exit() {
        println("Exiting the program.");
        // To exit the program
        System.exit(0);   //   "0" means a successful exit of the program.
    }
}




























