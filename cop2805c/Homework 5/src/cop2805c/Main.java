package cop2805c;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


//  COP2805C Homework 5
//  Author: Tony Rojas
//  Version 1:   6/15/24   Just the required assignment
//
//  Goal: to Build a GUI that has the following
//      1.	A JTextField where the user can enter a name
//      2.	A JLabel instructing the user to enter a name
//      3.	A JList that starts out empty
//      4.	A JButton that adds a name to the list
//      5.	A JButton that removes the selected name from the list
//      6.	A JButton that clears the contents of the list
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



public class Main
{
    public static void prt(String txt) { System.out.print(txt); }
    public static void prtl(String txt) { System.out.println(txt); }


    //  Create the Window Frame:
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






    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            //  Create the Window Frame, the Label, and Text input field
            JFrame frame1 = myFrame("Homework 5", 850, 100, 700, 500);
            JLabel lblName = makeLabel(frame1.getContentPane(), "Enter Name", 20, 50, 70, 30);
            JTextField txtName = mkTextFld(frame1.getContentPane(), "Name to insert", 100, 50, 200, 30);


            //  Create the Text output Area... No bueno: text is not selectable.
            //  JTextArea boxList= mkTxtArea(frame1.getContentPane(), "Hello, World!", 320, 50, 300, 300);

            //  ArrayList (collection) to store the list data:
            JList<String> lstNames = makeList(frame1.getContentPane(), 320, 50, 300, 300);


            //  Create the action buttons
            JButton btnAddName = makeButton(frame1.getContentPane(), "Add Name",   100, 100, 120, 30);
            JButton btnRemName = makeButton(frame1.getContentPane(), "Remove Name",100, 150, 120, 30);
            JButton btnClrList = makeButton(frame1.getContentPane(), "Clear List", 100, 200, 120, 30);


            // Action listener to the "Add Name" button
            btnAddName.addActionListener(
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

            // Action listener to the Remove Name button
            btnRemName.addActionListener(e -> {
                int selectedIndex = lstNames.getSelectedIndex();
                if (selectedIndex != -1) {
                    arListNames.remove(selectedIndex);
                }
            });


            /* //  This would remove more than one line. Not working yet.
            btnRemName.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int[] selectedIndices = lstNames.getSelectedIndices();
                        for (int i = selectedIndices.length - 1; i >= 0; i--) {
                            lstNames.remove(selectedIndices[i]);
                        }
                    }
                }
            );
            */


            // Add action listener to the button
            btnClrList.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        arListNames.clear();    // Clear all elements & Update the list display
                    }
                }
            );
        });
    }
}









