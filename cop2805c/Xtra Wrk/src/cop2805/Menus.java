package cop2805c;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


//  COP2805C Homework 5
//  Author: Tony Rojas
//  Version 1:   6/15/24   Just the required assignment
//  Version 2:   6/16/24   Added a Menu to the Frame

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
//       e. Search_Old in the List
//       f. All of this is done through a MENU BAR.
//
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
//  In new this version the user can select an action (a -> e) from a menu located on top
//



public class Menus
{    //  Create the Window Frame:
    public static JFrame myFrame(String title, int tpLfX, int tpLfY, int szWd, int szHt)
    {
        JFrame frame = new JFrame(title);                //  Create a new JFrame with the given title
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);                          // Use null layout for absolute positioning
        frame.setBounds(tpLfX, tpLfY, szWd, szHt);      // Set the position and size of the frame
        frame.setVisible(true);
        //init();
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
        bAddName.setBounds(x, y, wd, ht);       //  Set the bounds of the bAddName
        container.add(bAddName);                //  Add the bAddName to the container
        return bAddName;                        //  Return the textField
    }





    //  Window Title
    static String title = "Components with menu bar";

    //  Position
    static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int tpLfX = (int) (screenSize.getWidth() * 0.5);
    static int tpLfY = (int) (screenSize.getHeight() * .4);

    //  Dimensions
    static int szWd = 700;
    static int szHt = 500;


    //  Menu Font Fam & Sz
    static String mnFntFam = "Verdana";
    static int mnFntSiz = 14;



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


    //  Create submenus for each menu item such as File, Edit, etc.
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
        //  Create the menu bar
        JMenuBar topMenu = new JMenuBar();

        //  Create the File Menu
        JMenu mnFile = createMenu(topMenu, " File ", "Verdana", 16);

        JMenuItem subMnNew = addMnItem(mnFile, "Add Name",  mnFntFam, mnFntSiz);
        JMenuItem subMnRem = addMnItem(mnFile, "Remove",   mnFntFam, mnFntSiz);
        JMenuItem subMnClr = addMnItem(mnFile, "Clear List", mnFntFam, mnFntSiz);
        JMenuItem subMnExit = addMnItem(mnFile,"Exit", mnFntFam, mnFntSiz);

        mnFile.add(subMnNew);
        mnFile.add(subMnRem);
        mnFile.add(subMnClr);
        mnFile.addSeparator();
        mnFile.add(subMnExit);


        //  Create the Sort Menu
        JMenu mnSort = createMenu(topMenu, "Sort", "Verdana", 16);

        JMenuItem subMnAsc = addMnItem(mnSort, "Ascending",  mnFntFam, mnFntSiz);
        JMenuItem subMnDsc = addMnItem(mnSort, "Descending", mnFntFam, mnFntSiz);

        mnSort.add(subMnAsc);
        mnSort.add(subMnDsc);


        //  Create the Search_Old Menu
        JMenu mnSearch = createMenu(topMenu, " Search_Old", "Verdana", 16);



        //  Mouse AddNew:
        /* copied this from textbook. It's good because of threading problems. But I prefer the Lambda version.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                constructGUI();
            }
        });*/

        SwingUtilities.invokeLater(() ->
        {
            //  Create the Window Frame, the Label, and Text input field
            JFrame frame1 = myFrame(title, tpLfX, tpLfY, szWd, szHt);

            //  Label for text box
            JLabel lblName = makeLabel(frame1.getContentPane(), "Enter Name", 20, 50, 70, 30);
            JTextField txtName = mkTextFld(frame1.getContentPane(), "Name to insert", 100, 50, 200, 30);


            frame1.setJMenuBar(topMenu);


            //  Create the Text output Area
            //  JTextArea boxList= mkTxtArea(frame1.getContentPane(), "Hello, World!", 320, 50, 300, 300);
            //  This didn't work: it's not selectable.

            //  ArrayList (collection) to store the list data:
            JList<String> lstNames = makeList(frame1.getContentPane(), 320, 50, 300, 300);


            /*/  Create the action buttons  -  Old code!
            JButton btnAddName = makeButton(frame1.getContentPane(), "Add Name",   100, 100, 120, 30);
            JButton btnRemName = makeButton(frame1.getContentPane(), "Remove Name",100, 150, 120, 30);
            JButton btnClrList = makeButton(frame1.getContentPane(), "Clear List", 100, 200, 120, 30);
            */



            //  Action listeners to the:

            //  1. "Add Name" button
            subMnNew.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String text = txtName.getText();
                            if (!text.isEmpty()) {
                                arListNames.addElement(text);       //  Picks up the input text into the list
                                txtName.setText("");                //  Clear the textField after appending
                            } else {
                                txtName.setText("Enter a name here.");
                            }
                        }
                    }
            );


            //  2. Remove Name from list
            subMnRem.addActionListener(e -> {
                int selectedIndex = lstNames.getSelectedIndex();
                if (selectedIndex != -1) {
                    arListNames.remove(selectedIndex);
                }
            });



            //  3. Clear List button
            subMnClr.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            arListNames.clear();    // Clear all elements & Update the list display
                        }
                    }
            );



            //  4. Sort ascending button.
            //  Note: a simple Collection.sort method doesn't work. the JList has to be converted to an ArrayList.
            subMnAsc.addActionListener(e -> {
                List<String> arrListAsc = new ArrayList<>();
                for (int i=0; i<arListNames.size(); i++) {
                    arrListAsc.add(arListNames.getElementAt(i));
                }
                Collections.sort(arrListAsc);
                arListNames.clear();
                for (String s : arrListAsc) {
                    arListNames.addElement(s);
                }
            });



            //  5. Sort descending button action
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




            //  6. Search_Old button action
            mnSearch.addActionListener(e -> {
                System.out.println("Search_Old button clicked"); // Debugging line
                lblName.setText("Search_Old for:");
                String searchedText = txtName.getText();

                if (!searchedText.isEmpty()) {
                    for (int i = 0; i < arListNames.getSize(); i++) {
                        if (arListNames.getElementAt(i).contains(searchedText)) {
                            lstNames.setSelectedIndex(i);
                            lstNames.ensureIndexIsVisible(i);
                            break;
                        }
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




        });
    }
}









