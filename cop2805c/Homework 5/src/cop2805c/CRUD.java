
//  COP2805C Homework 5
//  Author: Tony Rojas
//  Version 1:   6/15/24   Just the required assignment
//  Version 2:   6/16/24   Added a Menu to the Frame
//  Version 3:   6/16/24   Created a real life Registration Form

//
//  Goal 1 (original): to Build a GUI that has the following
//      1.	A JTextField where the user can enter a name
//      2.	A JLabel instructing the user to enter a name
//      3.	A JList that starts out empty
//      4.	A JButton that adds a name to the list
//      5.	A JButton that removes the selected name from the list
//      6.	A JButton that clears the contents of the list\
//
//  Goal 2 (updated): this window will:
//       a. Add to list
//       b. Remove from list
//       c. Clear list
//       d. Sort List (Asc, Desc)
//       e. Search in the List
//       f. All of this is done through a MENU BAR.
//
//  Goal 3: Present a registration form with a list of registered individuals.
//
//  Description:
//  The user inputs the data as a String field box ("txtName" created by the mkTextFld function).
//  By clicking on the Add Name button, the user calls for the btnAddName method to execute, though an action listener.
//  This "btnAddName.addActionListener" transfers the name to a Text Box called "arListNames" (ArrayList of names).
//      Initially I had chosen a simple TextArea but its content cannot be manipulated as an object, line by line.
//  After the name is transferred, it gets erased from the txtName box.
//  The Remove Name button (btnRemName) will take the selected text from the List of Names box and delete it.
//  The Clear content button will clear the content of the List of Names text box.
//
//  In version 2 the user can select an action (a -> e) from a menu located on top
//  In version 3 the user will input the personal data into a registration form
//
//  Issues: have to fix the tabbing.





package cop2805c;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;





public class CRUD {


    //  Create the Window Frame:
    public static JFrame myFrame(String title, int tpLfX, int tpLfY, int szWd, int szHt)
    {
        JFrame frame = new JFrame(title);                //  Create a new JFrame with the given title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);                          // Use null layout for absolute positioning
        frame.setBounds(tpLfX, tpLfY, szWd, szHt);      // Set the position and size of the frame
        frame.setVisible(true);
        return frame;
    }

    //  Create labels:
    public static JLabel makeLabel(Container container, String lblName, int x, int y, int wd, int ht){
        JLabel label = new JLabel(lblName);  //  Set the label text
        label.setBounds(x, y, wd, ht);       //  Set the bounds of the JLabel
        container.add(label);                //  Add the label to the container
        return label;                        // Return the label
    }

    //  Create Text Boxes for input:
    public static JTextField mkTextFld(Container container, String txtName, int x, int y, int wd, int ht){
        JTextField textField = new JTextField(txtName);
        textField.setBounds(x, y, wd, ht);       //  Set the bounds of the textField
        container.add(textField);                //  Add the textField to the container
        return textField;                        // Return the textField
    }


    //  Create a Text area (rows & columns) to hold the names:
    //this doesn't work here bc textArea is not selectable (line by line) to be deleted later.
    public static JTextArea mkTxtArea(Container container, String boxName, int x, int y, int wd, int ht){
        JTextArea textArea = new JTextArea(boxName);
        textArea.setBounds(x, y, wd, ht);       //  Set the bounds of the textArea
        container.add(textArea);                //  Add the textArea to the container
        return textArea;                        //  Return the textArea
    }


    //  List Box that will hold the ArrayList collection of names.
    private static final DefaultListModel<String> arListNames = new DefaultListModel<>();

    //  Create JList Text Area:
    public static JList<String> makeList(Container container, int x, int y, int wd, int ht) {
        JList<String> lstNames = new JList<>(arListNames);
        lstNames.setBounds(x, y, wd, ht);
        container.add(lstNames);
        return lstNames;                        //  Return the list of Names
    }



    public static JButton makeButton(Container container, String boxName, int x, int y, int wd, int ht){
        JButton bAddName = new JButton(boxName);
        bAddName.setBounds(x, y, wd, ht);       //  Set the bounds of the bAddName button
        container.add(bAddName);                //  Add the bAddName to the container
        return bAddName;                        //  Return the textField
    }



    // Create a password field
    public static JPasswordField makePasswd(Container container, int x, int y, int wd, int ht){
        JPasswordField pswdField = new JPasswordField();
        pswdField.setBounds(x, y, wd, ht);       //  Set the bounds of the box
        container.add(pswdField);                //  Add this box to the container
        return pswdField;                        //  Return the pswdField
    }



    // Create a radio button
    public static JRadioButton makeRadio(Container container, String name, int x, int y, int wd, int ht, boolean select){
        JRadioButton btnRadio = new JRadioButton(name);
        btnRadio.setBounds(x, y, wd, ht);       //  Set the bounds of the box
        container.add(btnRadio);                //  Add this box to the container
        btnRadio.setSelected(select);
        return btnRadio;                        //  Return the Radio Button
    }



    // Create a Check Box
    public static JCheckBox makeChkBox(Container container, int x, int y, int wd, int ht){
        JCheckBox makeChkBox = new JCheckBox();
        makeChkBox.setBounds(x, y, wd, ht);       //  Set the bounds of the box
        container.add(makeChkBox);                //  Add this box to the container
        return makeChkBox;                        //  Return the Check Box
    }





/*
    // Create a "combobox":
        JComboBox<String> comboBox = new JComboBox<>(text.split(","));
        comboBox.setBounds(x, y, width, height);
        container.add(comboBox);


    // Create a  "imagebutton":
        JButton imageButton = new JButton(new ImageIcon(text));
        imageButton.setBounds(x, y, width, height);
        container.add(imageButton);


    // Create a  "image":
        JLabel imageLabel = new JLabel(new ImageIcon(text));
        imageLabel.setBounds(x, y, width, height);
        container.add(imageLabel);
*/




//  Dimensions

    //  Window Title
    static String title = "   MEMBER REGISTRATION";

    //  Position
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int tpLfX = (int) (screenSize.getWidth() * 1.02);
    static int tpLfY = (int) (screenSize.getHeight() * 0.25);

    //  Dimensions
    static int szWd = 1000;
    static int szHt = 530;



    //  Menu Creator
    private static JMenu createMenu(JMenuBar mnBar, String name, String fntFam, int fntSize)
    {
        // Create the menu element
        JMenu mnElement = new JMenu(name);

        // Change the font and size of the menu
        Font font = new Font(fntFam, Font.PLAIN, fntSize);
        mnElement.setFont(font);

        // Add this menu item to the menu bar
        mnBar.add(mnElement);

        //  return an element of type menu that gets plugged in the Menu Bar.
        return mnElement;
    }


    //  Sub-Menu Creator: Creates submenus for each menu item such as File, Edit, etc.
    private static JMenuItem addMnItem (JMenu menu, String name, String fntFam, int fntSize)
    {
        JMenuItem subMn1  = new JMenuItem(name);

        // Change the font and size of the menu
        Font font = new Font(fntFam, Font.PLAIN, fntSize);
        subMn1.setFont(font);

        return subMn1;
    }




    public static void main(String[] args)
    {
        //  MENU BAR:

        //  Menu Font Fam & Sz
        String mnFntFam = "Verdana";
        int mnFntSiz = 14;

        JMenuBar topMenu = new JMenuBar();

        //  Create the File Menu
        JMenu mnFile = createMenu(topMenu, " File ", "Verdana", 16);

        JMenuItem subMnNew = addMnItem(mnFile, "New Registration",  mnFntFam, mnFntSiz);  // clear the form
        JMenuItem subMnSav = addMnItem(mnFile, "Save Info",  mnFntFam, mnFntSiz);
        JMenuItem subMnPrt = addMnItem(mnFile, "Print", mnFntFam, mnFntSiz);
        JMenuItem subMnExit = addMnItem(mnFile,"Exit",  mnFntFam, mnFntSiz);

        mnFile.add(subMnNew);
        mnFile.add(subMnSav);
        mnFile.add(subMnPrt);
        mnFile.addSeparator();
        mnFile.add(subMnExit);


        //  Create the Search Menu
        JMenu mnEdit = createMenu(topMenu, " Edit ", "Verdana", 16);

        JMenuItem subMnCut = addMnItem(mnEdit, "Cut   ",  mnFntFam, mnFntSiz);
        JMenuItem subMnCpy = addMnItem(mnEdit, "Copy  ",  mnFntFam, mnFntSiz);
        JMenuItem subMnPst = addMnItem(mnEdit, "Paste ",  mnFntFam, mnFntSiz);

        mnEdit.add(subMnCut);
        mnEdit.add(subMnCpy);
        mnEdit.add(subMnPst);


        //  Create the Account Menu
        JMenu mnAcct = createMenu(topMenu, "Account", "Verdana", 16);

        JMenuItem subMnLgIn = addMnItem(mnAcct, "Login ",  mnFntFam, mnFntSiz);
        JMenuItem sbMnLgOut = addMnItem(mnAcct, "Logout",  mnFntFam, mnFntSiz);
        JMenuItem subMnProf = addMnItem(mnAcct, "Profile", mnFntFam, mnFntSiz);
        JMenuItem subMnSrch = addMnItem(mnAcct, "Search",  mnFntFam, mnFntSiz);
        JMenuItem subMnRemv = addMnItem(mnAcct, "Delete",  mnFntFam, mnFntSiz);

        mnAcct.add(subMnLgIn);
        mnAcct.add(sbMnLgOut);
        mnAcct.add(subMnProf);
        mnAcct.add(subMnSrch);
        mnAcct.add(subMnRemv);


        //  Create the Sort Menu
        JMenu mnReview = createMenu(topMenu, "Review", "Verdana", 16);

        JMenuItem subMnAsc = addMnItem(mnReview, "Sort Asc.",  mnFntFam, mnFntSiz);
        JMenuItem subMnDsc = addMnItem(mnReview, "Sort Desc.", mnFntFam, mnFntSiz);

        mnReview.add(subMnAsc);
        mnReview.add(subMnDsc);

        // END MENU BAR.






        //  Mouse Actions:


        SwingUtilities.invokeLater(() ->
        {
            //  Create the Window Frame, the Label, and Text input field
            JFrame frame1 = myFrame(title, tpLfX, tpLfY, szWd, szHt);


            frame1.setJMenuBar(topMenu);


            //  Label and TextBox for First Name
            JLabel lblFName = makeLabel(frame1.getContentPane(), "First Name", 20, 30, 70, 30);
            JTextField txtFName = mkTextFld(frame1.getContentPane(), "", 90, 30, 100, 30);

            //  Label and TextBox for Last Name
            JLabel lblLName = makeLabel(frame1.getContentPane(), "Last Name", 210, 30, 70, 30);
            JTextField txtLName = mkTextFld(frame1.getContentPane(), "", 280, 30, 100, 30);

            //  Label and TextBox for ADDRESS
            JLabel lblAddress = makeLabel(frame1.getContentPane(), "Address:", 20, 80, 70, 30);
            JTextField txtAddrs = mkTextFld(frame1.getContentPane(), "", 90, 80, 290, 30);



            //  Display the info to TextArea and add it to the JList (DB)
            JButton btnAddName = makeButton(frame1.getContentPane(), "Submit",   420, 30, 80, 30);

            //  Create the action buttons
            JButton btnNewName = makeButton(frame1.getContentPane(), "Reset",420, 80, 80, 30);



            //  Need a Text Area box to put all the member info:
            JTextArea txtAreaMemberInf = mkTxtArea(frame1.getContentPane(), "", 550, 25, 385, 80);



            //  Label and TextBox for Phone
            JLabel lblPhone = makeLabel(frame1.getContentPane(), "Phone:", 20, 130, 50, 30);
            JTextField txtPhone = mkTextFld(frame1.getContentPane(), "", 90, 130, 100, 30);

            //  Label and TextBox for eMail
            JLabel lblEmail = makeLabel(frame1.getContentPane(), "Email:", 200, 130, 70, 30);
            JTextField txtEmail = mkTextFld(frame1.getContentPane(), "", 240, 130, 180, 30);

            //  Label and TextBox for Password
            JLabel lblPasswd = makeLabel(frame1.getContentPane(), "Password:", 440, 130, 70, 30);
            JTextField txtPaswd = makePasswd(frame1.getContentPane(), 505, 130, 80, 30);

            //  Label and TextBox for Confirm Password
            JLabel lblConfrm = makeLabel(frame1.getContentPane(), "Confirm:", 600, 130, 60, 30);
            JTextField txtConfm = makePasswd(frame1.getContentPane(), 655, 130, 80, 30);

            //  Label and TextBox for Phone
            JLabel lblGender = makeLabel(frame1.getContentPane(), "Gender:", 750, 130, 50, 30);
            JRadioButton radMale = makeRadio(frame1.getContentPane(), "Male", 795, 130, 60, 30, false);
            JRadioButton radFemale = makeRadio(frame1.getContentPane(), "Female", 860, 130, 70, 30, false);

            ButtonGroup groupGender = new ButtonGroup();
            groupGender.add(radMale);
            groupGender.add(radFemale);




            //  Create the Text output Area: ArrayList (collection) to store the list data:
            JLabel lblList = makeLabel(frame1.getContentPane(), "Database:", 20, 200, 70, 30);
            JList<String> lstNames = makeList(frame1.getContentPane(), 90, 200, 850, 200);







            // Create action listeners

            //  1. "Add Name" to list button
            ActionListener actLisAdd = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String FName = txtFName.getText();
                    String LName = txtLName.getText();
                    String Addrs = txtAddrs.getText();
                    String Phone = txtPhone.getText();
                    String Email = txtEmail.getText();

                    //  Checking to make sure the passwords match
                    String Paswd = txtPaswd.getText();
                    String Confm = txtConfm.getText();
                    if (!Paswd.equals(Confm)) {
                        JOptionPane.showMessageDialog(frame1, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                    // radio btn selection:
                    String Gender = "";
                    if(radMale.isSelected()) { Gender = "Male"; }
                    else if (radFemale.isSelected()){ Gender = "Female"; }

                    //  Creating the input data
                    if (!FName.isEmpty() || !LName.isEmpty()) {
                        //  Pick up the input text, format it, and add it into the JList
                        String newMemberData = "Name: " + FName + " " + LName + ";   Address: " + Addrs +
                                ";    Phone: " + Phone + ";    Email: " + Email + ";    Pwd: " + Paswd + ";    Gender: " + Gender;
                        arListNames.addElement(newMemberData);
                        //  Problem:  JList does not support multiline text.
                        //  Need to convert this line to TextArea to see the "\n" formatting.
                        String newMemberText = "  Name: " + FName + " " + LName + "\n  Address: " + Addrs +
                                "\n  Phone: " + Phone + "\n  Email: " + Email + "\n  Pwd: " + Paswd + "\t    Gender: " + Gender;
                        txtAreaMemberInf.append(newMemberText);

                        //  Clear the name textFields after appending
                        txtFName.setText("");
                        txtLName.setText("");
                        txtAddrs.setText("");
                        txtPhone.setText("");
                        txtEmail.setText("");
                        txtPaswd.setText("");
                        txtConfm.setText("");
                    } else {
                        txtFName.setText("Enter a First name here.");
                        txtLName.setText("Enter a Last name here.");
                    }
                }
            };


            //  2. Clear the TextArea box
            ActionListener actClrTxtArea = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtAreaMemberInf.setText("");
                    groupGender.clearSelection();
                }
            };


            //  3. Clear the List (DB) box
            ActionListener actLisRem = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int selectedIndex = lstNames.getSelectedIndex();
                    if (selectedIndex != -1) {
                        arListNames.remove(selectedIndex);
                    }
                }
            };



            //  4. Clear List button
            ActionListener actLisClr = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtFName.setText("");    // Clear all the input boxes and display
                    txtLName.setText("");
                    txtAddrs.setText("");
                    txtAreaMemberInf.setText("");
                }
            };



            //  5.1. Sort ascending button.
            //  Note: a simple Collection.sort method doesn't work. the JList has to be converted to an ArrayList.
            subMnAsc.addActionListener(e -> {
                java.util.List<String> arrListAsc = new ArrayList<>();
                for (int i=0; i<arListNames.size(); i++) {
                    arrListAsc.add(arListNames.getElementAt(i));
                }
                Collections.sort(arrListAsc);
                arListNames.clear();
                for (String s : arrListAsc) {
                    arListNames.addElement(s);
                }
            });



            //  5.2. Sort descending button action
            subMnDsc.addActionListener(e -> {
                List<String> arrListDsc = new ArrayList<>();
                for (int i=0; i<arListNames.size(); i++) {
                    arrListDsc.add(arListNames.getElementAt(i));
                }
                Collections.sort(arrListDsc, Collections.reverseOrder());
                arListNames.clear();
                for (String s : arrListDsc) {
                    arListNames.addElement(s);
                }
            });




            //  6. Search button action
            subMnSrch.addActionListener(e -> {
                //System.out.println("Search button clicked"); // Debugging line
                lblFName.setText("By First:");
                lblLName.setText("By Last:");
                String searchedFName = txtFName.getText();
                String searchedLName = txtLName.getText();

                if (!searchedFName.isEmpty() || !searchedLName.isEmpty()) {
                    for (int i = 0; i < arListNames.getSize(); i++) {
                        if (arListNames.getElementAt(i).contains(searchedFName)){
                            lstNames.setSelectedIndex(i);
                            lstNames.ensureIndexIsVisible(i);
                            break;
                        } /*else (arListNames.getElementAt(i).contains(searchedLName)){
                            lstNames.setSelectedIndex(i);
                            lstNames.ensureIndexIsVisible(i);
                            break;
                        }*/
                    }
                }
            });



            //  7. Clear List button
            subMnExit.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0); // Exit the program with status code 0 (normal termination)
                        }
                    }
            );




            //  Attach the predefined Action Listeners to buttons or menus.

            //  1. Add
            btnAddName.addActionListener(actLisAdd);
            subMnSav.addActionListener(actLisAdd);

            //  2. Clear Form
            subMnNew.addActionListener(actLisClr);
            btnNewName.addActionListener(actClrTxtArea);

            //  3. Remove
            subMnRemv.addActionListener(actLisRem);
        });
    }
}










