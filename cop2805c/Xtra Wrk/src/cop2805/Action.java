package cop2805;

import cop2805.Create.*;

import static cop2805.Server.ClientHandler.client;
import static cop2805.Tools.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Action
{
    //  PHOTO
    public static void addPhoto(Frame frame, JButton btnPhoto)
    {
        //  Have to browse for the image path first:
        JFileChooser fileChooser = new JFileChooser();

        //  Show Browse popup modal window, to find/select image
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION)
        {
            //  Once we click on an img, we grab it (selectedFile)
            File selectedFile = fileChooser.getSelectedFile();

            //  Load the image into the program
            ImageIcon photo0 = new ImageIcon(selectedFile.getPath());

            // Extract the Image from ImageIcon, so we can scale it down.
            Image photo1 = photo0.getImage();

            //  Resize the image to 80x80 pixels
            Image photo2 = photo1.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

            //  Convert Image back to ImageIcon, to place it in the form (label)
            ImageIcon photo3 = new ImageIcon(photo2);

            //  Set up image within the containing label
            JLabel lblImgBox = new JLabel(photo3);

            // Set the size and position of the image Label
            lblImgBox.setBounds(750, 37, 80, 80);

            //  Put the photo within the containing label
            lblImgBox.setIcon(photo3);

            // Create a border for the containing label
            Border bdrPhoto = BorderFactory.createLineBorder(Color.GRAY, 1); // Black border, 2 pixels wide
            lblImgBox.setBorder(bdrPhoto);

            //  Add the image label to the frame
            frame.add(lblImgBox);

            //  REFRESH: Revalidate and repaint the frame to show the new image
            frame.revalidate();
            frame.repaint();

            //  Make the Add button invisible, after placing the image
            btnPhoto.setVisible(false);
        }
    }


    //  TABLE

    //  Read/Import the data from an external csv file to the table
    public static void loadCSV(JTable table, String file)
    {
        try (BufferedReader csvReader = new BufferedReader(new FileReader(file)))
        {
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();  // recast the JTable

            tableModel.setRowCount(0);                           // Clear existing data

            String row;
            //  The first row in the CSV file contains column headers, so we will skip it.
            boolean isFirstRow = true;

            //  Read new lines:
            while ((row = csvReader.readLine()) != null)
            {
                //  Create a data array to capture content that is comma delimited     // make it ", " later
                String[] data = row.split(",");

                //  Add the subsequent rows to the table, except for the first row:
                if (isFirstRow) {
                    isFirstRow = false;       //  headers
                }
                else {
                    tableModel.addRow(data);  //  continue reading and adding rows
                }
            }
        } catch (IOException e) {  e.printStackTrace();  }
    }





/*  I don't need this anymore since I recast the JTable above.

    public static void addData(DefaultTableModel model) {
        Object[] rowData = new Object[model.getColumnCount()];
        // Fill rowData with the values from the form components
        model.addRow(rowData);
    }
*/








    //  ADD NEW CLIENT

    public static void Add(JFrame frame1, JComponent[] components, JTable theTable)
    {
        DefaultTableModel model = (DefaultTableModel) theTable.getModel();


        //  This is the Input components array:
        //  First, MI, Last, Phone1, Phone2, Address, City, State, Zip, Gender, Email,  D.of B.,    Mar. Status,       Passwd, Notes
        //  0      1   2     3       4       6        7     8      9    10,11   12      13,14,15   16,17,18,19,20,21   22      5
        //  Output:
        //  0      1   2     3       4       5        6     7      8    9       10      11         12                  13      14


        //  Map each component to the correct position in the output data array.
        String[] output = new String[15];

        //  Line 1: Name, phones and notes
        output[0] = ((JTextField) components[0]).getText();   //  txtFirst_0
        output[1] = ((JTextField) components[1]).getText();   //  txtMiddl_1
        output[2] = ((JTextField) components[2]).getText();   //  txtLastN_2
        output[3] = ((JTextField) components[3]).getText();   //  txtPhon1_3
        output[4] = ((JTextField) components[4]).getText();   //  txtPhon2_4
        //  get the notes:
        output[14] = ((JTextArea)  components[5]).getText();  //  txtNotes_5


        //  Line 2: Address and Gender
        output[5] = ((JTextField) components[6]).getText();   //  txtAddrs_6
        output[6] = ((JTextField) components[7]).getText();   //  textCity_7
        output[7] = ((JTextField) components[8]).getText();   //  txtState_8
        output[8] = ((JTextField) components[9]).getText();   //  txtZipCd_9
        //  Radio btn for gender selection:
        String Gender = "";
        if ( ( (JRadioButton) components[10] ).isSelected() ) { Gender = "Male"; }    //  radioMal_10
        else if ( ( (JRadioButton) components[11]).isSelected() ) { Gender = "Female"; }  //  radFemal_11
        output[9] = Gender;


        //  Line 3: Email, DoB, Marital Status and Password
        output[10] = ((JTextField) components[12]).getText();   // txtEmail_12

        //  Date of birth: the Day and Month spinners get concatenated with year and give this to output[]
        String Day  = ((JSpinner)   components[13]).getValue().toString();   //  spinrDay_13
        String Month= ((JSpinner)   components[14]).getValue().toString();   //  spinrMon_14
        String Year = ((JTextField) components[15]).getText();               //  textYear_15
        String DoB  = Day + "/" + Month + "/" + Year;
        output[11]  = DoB;

        // Collect Marital Status states (checkboxes)
        StringBuilder maritalStatus = new StringBuilder();
        if (((JCheckBox) components[16]).isSelected()) { maritalStatus.append("Single "); }     //  chSingle_16
        if (((JCheckBox) components[17]).isSelected()) { maritalStatus.append("Married "); }    //  chMaried_17
        if (((JCheckBox) components[18]).isSelected()) { maritalStatus.append("Divorced "); }   //  chDivord_18
        if (((JCheckBox) components[19]).isSelected()) { maritalStatus.append("Widowed "); }    //  chWidowd_19
        if (((JCheckBox) components[20]).isSelected()) { maritalStatus.append("Common-Law "); } //  chCmonLw_20
        if (((JCheckBox) components[21]).isSelected()) { maritalStatus.append("Separated "); }  //  chSepard_21
        output[12] = maritalStatus.toString().trim(); // Remove any trailing space


        //  Then we collect the passwords and make sure they match
        String pwd = new String(((JPasswordField) components[22]).getPassword());  //  pswPaswd_22
        String cnf = new String(((JPasswordField) components[23]).getPassword());  //  pswConfr_23
        if (!pwd.equals(cnf)) {
            //JOptionPane.showMessageDialog(frame1, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE); // w this it hangs
            SwingUtilities.invokeLater(() -> {  //  by invoking this the Swing components are handled on the Event Dispatch Thread (EDT)
                JOptionPane.showMessageDialog(frame1, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
            });
        }
        else {
            output[13] = pwd;

            // Finally, we add the output array to the table's model, if the passwords match
            //theTable.addRow(output);//  Old: DefaultTableModel
            model.addRow(output);
        }

        //  Index
        output[14] +=1;
        //  this is necessary for identifying each person, in case of updates to the table. I should have put it in the beginning.


    }







    //  SEARCH FORM

    public static void Search(JTable table, JComponent[] components) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();

        // Perform the search logic as before...

        // Clear table before showing search results
        model.setRowCount(0);

        // Add the matching row to the table model
        //String[] rowData = {first, midIn, lastN, phon1, phon2, addrs, city, state, zipCd, gnder, email, dobir, mstat, paswd, null};
        //model.addRow(rowData);
    }









    //  TABLE

    public static class CustomListSelectionListener implements ListSelectionListener {
        private JTable table;
        private JComponent[] components;

        public CustomListSelectionListener(JTable table, JComponent[] components) {
            this.table = table;
            this.components = components;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                Object[] rowData = new Object[table.getColumnCount()];
                for (int i = 0; i < table.getColumnCount(); i++) {
                    rowData[i] = table.getValueAt(selectedRow, i);
                }
                populateForm(rowData, components);
            }
        }

        private void populateForm(Object[] rowData, JComponent[] components) {
            // Populate form fields with the data from rowData array as before...
        }
    }

    public static void save2CSV(JTable table, String file) {
        try (FileWriter csvWriter = new FileWriter(file)) {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            int columnCount = model.getColumnCount();

            // Write column headers
            for (int i = 0; i < columnCount; i++) {
                csvWriter.append(model.getColumnName(i));
                if (i < columnCount - 1) csvWriter.append(",");
            }
            csvWriter.append("\n");

            // Write rows
            for (int i = 0; i < model.getRowCount(); i++) {
                for (int j = 0; j < columnCount; j++) {
                    csvWriter.append(model.getValueAt(i, j).toString());
                    if (j < columnCount - 1) csvWriter.append(",");
                }
                csvWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



/*
    private static class SubmitData implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                //  Get the Text Fields content.
                String inpName  = txtName.getText();
                String inpEmail = txtEmail.getText();

                //  Get the CheckBox selection
                String promo = ChkBox.getSelectedCheckboxText("RecEmail");

                //  Get the Dropdown List selection
                String ageGrp = selAgeGrp.getSelectedItem();

                //  Get the Radio Buttons selection
                int rating = Integer.parseInt(getSelectedRadioButtonText("Rating"));

                //  Get the Text Area content.
                String feedback = TAFeedBk.getText();

                //  Send to server
                SendFormData(inpName, inpEmail, promo, ageGrp, rating, feedback);
            }
            catch (IOException ex) { ex.printStackTrace(); }
        }
    }*/




    //  BUTTON  RESET

    public static void performReset(
            JTextField txtFirst, JTextField txtMiddl, JTextField txtLastN, JTextField txtPhon1, JTextField txtPhon2,
            JTextField txtAddrs, JTextField textCity, JTextField txtState, JTextField txtZipCd, JTextField txtEmail,
            JSpinner spinrMon, JSpinner spinrDay, JTextField textYear, ButtonGroup grpGender, ButtonGroup marStatus,
            TxtFd pswPaswd, TxtFd pswConfr, ButtonGroup grpNotify, TextA txtNotes)
    {
        // Clear the fields
        txtFirst.setText("");
        txtMiddl.setText("");
        txtLastN.setText("");
        txtPhon1.setText("");
        txtPhon2.setText("");
        txtAddrs.setText("");
        textCity.setText("");
        txtState.setText("");
        txtZipCd.setText("");
        txtEmail.setText("");
        spinrMon.setValue("");
        spinrDay.setValue("");
        textYear.setText("");
        grpGender.clearSelection();
        marStatus.clearSelection();
        pswPaswd.setText("");
        pswConfr.setText("");
        grpNotify.clearSelection();
        txtNotes.setText("");
    }



    public static void performSearch(
            JFrame frame, JSpinner spnTitle, JTextField txtFirst, JTextField txtMiddl, JTextField txtLastN, JTextField txtPhon1,
            JTextField txtPhon2, JTextField txtAddrs, JTextField textCity, JTextField txtState, JTextField txtZipCd,
            JTextField txtEmail, JSpinner spinrMon, JSpinner spinrDay, JTextField textYear, JRadioButton radiMale,
            JRadioButton radFemal, JRadioButton rdSingle, JRadioButton rdMaried, JRadioButton rdDivord, JRadioButton rdWidowd,
            JRadioButton rdSepart, JRadioButton rdCmonLw, TxtFd pswPaswd, TxtFd pswConfr, JRadioButton rdNotif)
    {
        // Read the server's response
        try
        {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String response = reader.readLine();

            // Parse the response and populate form fields
            String[] fields = response.split(",");
            if (fields.length >= 20) {
                spnTitle.setValue(fields[0]);
                txtFirst.setText(fields[1]);
                txtMiddl.setText(fields[2]);
                txtLastN.setText(fields[3]);
                txtPhon1.setText(fields[4]);
                txtPhon2.setText(fields[5]);
                txtAddrs.setText(fields[6]);
                textCity.setText(fields[7]);
                txtState.setText(fields[8]);
                txtZipCd.setText(fields[9]);
                txtEmail.setText(fields[10]);
                spinrMon.setValue(fields[11]);
                spinrDay.setValue(fields[12]);
                textYear.setText(fields[13]);
                // Set Gender radio button
                if (fields[14].equalsIgnoreCase("Male")) {
                    radiMale.setSelected(true);
                } else if (fields[14].equalsIgnoreCase("Female")) {
                    radFemal.setSelected(true);
                }
                // Set Marital Status radio button
                if (fields[15].equalsIgnoreCase("Single")) {
                    rdSingle.setSelected(true);
                } else if (fields[15].equalsIgnoreCase("Married")) {
                    rdMaried.setSelected(true);
                } else if (fields[15].equalsIgnoreCase("Divorced")) {
                    rdDivord.setSelected(true);
                } else if (fields[15].equalsIgnoreCase("Widowed")) {
                    rdWidowd.setSelected(true);
                } else if (fields[15].equalsIgnoreCase("Separated")) {
                    rdSepart.setSelected(true);
                } else if (fields[15].equalsIgnoreCase("Com.Law Marr.")) {
                    rdCmonLw.setSelected(true);
                }
                // Set marital status radio button
                // ... (similar logic for marital status)
                pswPaswd.setText(fields[16]);
                pswConfr.setText(fields[17]);
                // Set notification checkbox
                rdNotif.setSelected(fields[18].equalsIgnoreCase("Yes"));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    //  BUTTON  DELETE

    // Method to delete the record from the database
    public static void performDelete(int ID) throws IOException {
        prtln(String.valueOf(ID));  //  testing

        String sql = "DELETE FROM `users` WHERE `ID` = ?";
        //client = new Socket(HOST, PORTCLIENT);

        try (Connection conn = Services.DBConn.getConn();                   //  STEP 3: Initialize the connection
             PreparedStatement statm = conn.prepareStatement(sql) )         //  STEP 4: Create a working statement
        //  Note: The PreparedStatement is used to safely execute the SQL DELETE statement, preventing SQL injection attacks.
        {
            statm.setInt(1, ID);                               // Set the ID value in the prepared statement
            int rowsAffected = statm.executeUpdate();                       // Execute the delete statement

            if (rowsAffected > 0) {
                System.out.println("Record with ID " + ID + " was deleted successfully.");
            } else {
                System.out.println("No record found with ID " + ID + ".");
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Handle any SQL exceptions
        }
    }
}



























