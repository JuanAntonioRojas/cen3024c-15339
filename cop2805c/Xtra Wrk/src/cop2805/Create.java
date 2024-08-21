package cop2805;

import static cop2805.Constants.*;
import static cop2805.Tools.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionListener;


public class Create extends JFrame
{
    //  WINDOW FRAME

    static class WinFrame extends JFrame {
        //  Window Frame Constuctor
        public WinFrame(String title, double[] dim, Color bgColor)  //  In BASIC, DIM is short for "Dimension," & it was used to declare arrays,
        {
            //  by using super we don't need to create a new JFrame
            super(title);                                   //  This calls the superclass constructor, with a title
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //frame.setLocationRelativeTo(null)             //  This would center the frame on the screen. I want to define that
            setLayout(null);                                //  This allows for absolute positioning

            //  This will display the position and size of the frame
            setBounds((int) dim[0], (int) dim[1], (int) dim[2], (int) dim[3]);
            getContentPane().setBackground(bgColor);        //  This sets a background color

            //Shadow frameShadow = new Shadow(10, SHDCOL, 5, 5);
            //frameShadow.add(this);

            setVisible(true);

            // Load and add a person's Photo (optional?)
            //ImageLabel imageLabel = new ImageLabel(path, photoPoss);
            //add(imageLabel);
        }
    }




    //  REFRESH FRAME

    //     Revalidate and repaint the container to show the new image
    //     to ensure the layout is recalculated and the frame is properly updated
    public static void frmRefresh(Container frame) {
        frame.revalidate();
        frame.repaint();
    }




    //  TAB ORDER

    //  Setting a hash map to define the Tab Order, to avoid moving the mouse around.
    private static final Map<JComponent, Integer> tabOrder = new HashMap<>();










    //  PANEL

    public static class Panl extends JPanel
    {
        public Panl(Container frame, int[] dim, Color bgColor)
        {
            this.setBounds(dim[0], dim[1], dim[2], dim[3]);
            this.setBackground(bgColor);        //  Background color, otherwise it's white
            this.setLayout(null);               // Disable the layout manager

            frame.add(this);                    //  Attach to frame
            frmRefresh(frame);                  //  Refresh the frame
        }
    }






    //  LAYERED PANE

    public static class Pane extends JLayeredPane
    {
        public Pane(Container frame, int[] dim, Color bgColor)
        {
            this.setBounds(dim[0], dim[1], dim[2], dim[3]);
            this.setBackground(bgColor);        //  Background color, otherwise it's white
            this.setLayout(null);               // Disable the layout manager

            frame.add(this);                    //  Attach to frame
            frmRefresh(frame);                  //  Refresh the frame
        }
    }















    //  COMPONENTS




    //  ITEM TEMPLATE

        //    This is what all of the components have in common. Their model.
    public static void Item(JComponent component, Container frame, int[] dim, Color bgColor, Font font, Color fntCol)
    {
        component.setBounds(dim[0], dim[1], dim[2], dim[3]);

        // Assuming tabOrder is a static variable in your utility class or another accessible place
        tabOrder.put(component, dim[4]);        //  Map (tag) the component with a tab number

        component.setBackground(bgColor);           //  This sets a background color
        component.setOpaque(true);                  //  Needed to actually paint the background color
        component.setFont(font);                    //  This sets a font family, bold-face & size
        component.setForeground(fntCol);            //  This sets a font color

        // Add hover effect
        component.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                component.setForeground(NAVYBL);    //  Change the FONT color on HOVER
                component.setBackground(LTYELL);    //  Change the BACKGROUND color on HOVER
            }

            @Override
            public void mouseExited(MouseEvent e) {
                component.setForeground(fntCol);    //  Restore the original FONT color
                component.setBackground(bgColor);   //  Restore the original BACKGROUND color
            }
        });

        frame.add(component);
        frmRefresh(frame);  // Assuming this is a method available in your context
    }








    //  TEXT LABEL

    public static class TxtLb extends JLabel  {
        public TxtLb(Container frame, int[] dim, Color bgColor, Font font, Color fntCol, String text) {
            super(text);
            Item(this, frame, dim, bgColor, font, fntCol);
        }
        public static String getLblTxt(JLabel label) {
            return label.getText();
        }
    }

//  Putting elements inside Panl messes up with the dimensions.
public static class pTxtLb extends JLabel  {
        public pTxtLb(WinFrame panel, int[] dim, Color bgColor, Font font, Color fntCol, String text) {
            super(text);
            Item(this, panel, dim, bgColor, font, fntCol);
        }
    }




    //  TEXT FIELD

    public static class TxtFd extends JTextField {
        public TxtFd(Container frame, int[] dim, Color bgColor, Font font, Color fntCol, String text) {
            super(text);
            Item(this, frame, dim, bgColor, font, fntCol);
        }

        // Custom method to set the text field to blank
        public void clear() {
            setText("");  // Set the text to an empty string
        }
    }





    //  TEXT AREA

    public static class TextA extends JComponent
    {
        private Color originalBackground;

        private JTextArea textArea;                     //  Store a reference to the JTextArea
        public TextA(Container frame, int[] dim, Color bgColor, Font font, Color color, String text)
        {
            textArea = new JTextArea(text);
            textArea.setLineWrap(true);                 //  Enable line wrap
            textArea.setWrapStyleWord(true);            //  Wrap lines at word boundaries

            JScrollPane textAScrollPane = new JScrollPane(textArea);
            textAScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scrollbar
            textAScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Enable vertical scrollbar as needed

            Item(textAScrollPane, frame, dim, bgColor, font, color);

            // Set the background color of the JTextArea
            textArea.setBackground(bgColor);

            frame.add(textAScrollPane);
            frmRefresh(frame);
        }

        //  Method to get the text content of the JTextArea
        public String getText() {
            return textArea.getText();
        }

        //  Method to set the text content of the JTextArea
        public void setText(String text) {
            textArea.setText(text);
        }
        public void clear() {
            setText("");  // Set the text to an empty string
        }
    }








    //   SELECT SPINNER

    public static class SpnTxt extends JSpinner {
        public SpnTxt(Container frame, int[] dim, Color bgColor, Font font, Color color, String[] items, String initialValue)
        {
            super();

            Item(this, frame, dim, bgColor, font, color);

            //  This makes it a text spinner instead of a number spinner
            SpinnerListModel model = new SpinnerListModel(items);
            setModel(model);

            // Set the initial value to the specified item
            setValue(initialValue);

            // Get the editor component
            JComponent editor = getEditor();
            if (editor instanceof JSpinner.DefaultEditor) {
                JTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
                textField.setFont(font);
                textField.setForeground(color);
                textField.setBackground(bgColor);  // Set the background color of the text field
            }
        }

        // Method to get the selected item
        public String getSelectedItem() {
            return (String) getValue();
        }
    }




    //   NUMBER SPINNER

    public static class SpnNbr extends JSpinner {
        public SpnNbr(Container frame, int[] dim, int min, int max, int step, int value) {
            super(new SpinnerNumberModel(value, min, max, step));
            setBounds(dim[0], dim[1], dim[2], dim[3]);

            //  This makes it a number spinner instead of a text spinner
            setModel(new SpinnerNumberModel(value, min, max, step));

            tabOrder.put(this, dim[4]);  //  Map (tag) the button with a tab number
            frame.add(this);
            frmRefresh(frame);
        }
    }






    //  PROGRESS BAR

    public static class PrBar extends JProgressBar {
        public PrBar(Container frame, int[] dim, int min, int max, int value, Color bgColor) {
            JProgressBar progressBar = new JProgressBar(min, max) {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    int tickSpacing = (max - min) / 10;     //  Tick spacing
                    int tickCount = (max - min) / tickSpacing;
                    int tickWidth = getWidth() / tickCount;
                    g.setColor(Color.DARK_GRAY);            //  Tick Color
                    for (int i = 1; i < tickCount; i++) {
                        int x = i * tickWidth;
                        g.drawLine(x, 0, x, getHeight());
                    }
                }
            };
            progressBar.setValue(value);
            progressBar.setBounds(dim[0], dim[1], dim[2], dim[3]);
            progressBar.setBackground(bgColor);  // Set background color if needed
            tabOrder.put(progressBar, dim[4]);  // Map (tag) the progress bar with a tab number
            Border border = BorderFactory.createLineBorder(Color.GRAY, 2);  //  1-pixel grey border
            progressBar.setBorder(border);
            frame.add(progressBar);
            frmRefresh(frame);
        }
    }





    //  SLIDER

    public static class Slidr extends JSlider {
        public Slidr(Container frame, int[] dim, int min, int max, int value, boolean ticks, boolean labels, Color bgColor, JLabel linkedLabel) {
            JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, value);
            slider.setBounds(dim[0], dim[1], dim[2], dim[3]);
            slider.setPaintTicks(ticks);
            slider.setPaintLabels(labels);
            slider.setMajorTickSpacing((max - min) / 10);   //  Major tick spacing
            slider.setMinorTickSpacing((max - min) / 20);   //  Minor tick spacing

            slider.addChangeListener(e -> {                 //  Add Action/Change Listener to update the label
                //int sliderValue = slider.getValue();      //  Moved this functions to Action.java
                //linkedLabel.setText(String.valueOf(sliderValue));
                //Action.onSliderChg(slider,linkedLabel);
            });

            tabOrder.put(slider, dim[4]);               //  Map to a tab number
            slider.setBackground(bgColor);                  //  Background Color
            frame.add(slider);
            frmRefresh(frame);
        }
    }

    public static String getSldVal(JSlider slider) {
        return String.valueOf(slider.getValue());
    }






    //  PASSWORD

    public static class Passwd extends JComponent {
        public Passwd(Container frame, int[] dim, String text)
        {
            JPasswordField password = new JPasswordField(text);
            password.setBounds(dim[0], dim[1], dim[2], dim[3]);
            tabOrder.put(password, dim[4]);
            frame.add(password);
            frmRefresh(frame);
        }
    }










    //  CHECK BOX

    public static class ChkBx extends JCheckBox
    {
        //  This hashmap will handle multiple checkboxes in the frame.
        private static final Map<String, JCheckBox> selectedCheckboxes = new HashMap<>();
        private String groupName;

        public ChkBx(Container frame, ButtonGroup groupName, int[] dim, Font font, Color color, boolean selected, String text)
        {
            //  Modify text to go in 2 lines: Use HTML for multi-line text
            //super("<html>" + text.replace("\n", "<br>") + "</html>");
            super(text);


            // Store the group name for later use
            this.groupName = groupName.toString();

            //  Set position & tab order
            this.setBounds(dim[0], dim[1], dim[2], dim[3]);
            tabOrder.put(this, dim[4]);         // Map (tag) the button with a tab number
            this.setBackground(color);              // Set background color to match the frame's bg color
            this.setSelected(selected);

            // Set custom font and color
            this.setFont(font);                     // Change font size here
            this.setForeground(LTBLUE);            // Change font color here

            // Set custom icons for checkbox
            this.setIcon(customCheckBoxIcon(LTBLUE, LTGRAY));
            this.setSelectedIcon(customCheckBoxIcon(Color.BLUE, Color.BLACK));

            //  Map (tag) the text area box with a tab number
            tabOrder.put(this, dim[4]);

            // Handling their state
            this.addActionListener(e -> handleCheckboxSelection(this, this.groupName));

            // Add checkbox to the frame
            frame.add(this);
            frmRefresh(frame);
        }

        //  This ensures that only one checkbox in the same group (groupName) remains selected.
        private void handleCheckboxSelection(JCheckBox selectedCheckBox, String groupName)
        {
            prtln("Checkbox selected: " + selectedCheckBox.getText());
            selectedCheckboxes.entrySet().removeIf(entry -> {
                boolean toRemove = entry.getKey().equals(groupName) && entry.getValue() != selectedCheckBox;
                if (toRemove) {
                    System.out.println("Removing checkbox: " + entry.getValue().getText() + " from group: " + groupName);
                }
                return toRemove;
            });
            selectedCheckboxes.put(groupName, selectedCheckBox);
            System.out.println("Updated selectedCheckboxes map: " + selectedCheckboxes);
        }


        //  Method to get the selected checkbox text from a group
        public static String getSelectedCheckboxText(String groupName)
        {
            JCheckBox selectedCheckBox = selectedCheckboxes.get(groupName);
            //return selectedCheckBox != null ? selectedCheckBox.getText() : null;

            prtln("Retrieved checkbox for group " + groupName + ": " + (selectedCheckBox != null ? selectedCheckBox.getText() : "null"));
            return selectedCheckBox != null ? selectedCheckBox.getText() : null;
        }
    }








    //  RADIO BUTTON

    public static final Map<String, ButtonGroup> buttonGroup = new HashMap<>();  //  For the selection: Male or Female


    public static class Radio extends JRadioButton
    {
        ButtonGroup groupName;
        public Radio(Container frame, ButtonGroup groupName, int[] dim, Color bgColor, Font font, Color color, boolean selected, String text) {
            super(text);                        // Call the JRadioButton constructor with the text
            this.setBounds(dim[0], dim[1], dim[2], dim[3]);
            this.setSelected(selected);         // Set the selected state
            this.setFont(font);                 // Set the font
            this.setForeground(color);          // Set the font color
            this.setBackground(bgColor);        //  Background color matches the frame's bg color
            //radioButton.setOpaque(false);     //  With this line I don't need the previous line (esp. if it has an alpha channel)

            // Create the Gender ButtonGroup.  Not needed if I define groupName as ButtonGroup to begin with
            //ButtonGroup group = buttonGroup.computeIfAbsent(groupName, k -> new ButtonGroup());
            groupName.add(this);                // Add to ButtonGroup

            tabOrder.put(this, dim[4]);     //  Map (tag) the button with a tab number
            frame.add(this);                    // Add to Frame
            frmRefresh(frame);
        }

        //  Method to clear a button group
        public void clear() {
            groupName.clearSelection();  //  This will deselect all radio buttons in the group
        }
    }


    //  This sets custom icons for radio
    public static class CustomRadio extends Radio {
        public CustomRadio(Container frame, ButtonGroup groupName, int[] dim, Color bgColor, Font font, Color color, boolean selected, String text) {
            super(frame, groupName, dim, bgColor, font, color, selected, text);
            this.setIcon(customRadioIcon(Color.LIGHT_GRAY, Color.BLUE));
            this.setSelectedIcon(customRadioIcon(Color.BLUE, Color.WHITE));
        }
    }


    // Method to get the selected radio button text from a group
    public static String getSelectedRadioButtonText(String groupName) {
        ButtonGroup group = buttonGroup.get(groupName);
        if (group != null) {
            for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements(); ) {
                AbstractButton button = buttons.nextElement();
                if (button.isSelected()) {
                    return button.getText();
                }
            }
        }
        return null;  // No selection found
    }






    // BUTTON

    public static class Buttn extends JButton
    {
        private int shdSize;
        private Color shdColor;
        private Color btnColor;
        private int shdXOffset;
        private int shdYOffset;
        private boolean isHovered;
        private boolean isPressed;
        private Color defaultColor;

        public Buttn(Container frame, int[] dim, Color btnColor, Font font, Color color, String text, ActionListener clickEvent)
        {
            //JButton button = new JButton(text);
            super(text);  //  Call the superclass constructor with the initial text

            Item(this, frame, dim, btnColor, font, color);

            setBorderPainted(false);                    // Remove the border
            setContentAreaFilled(false);                // Remove the content area fill

            // Set shd properties
            this.shdSize = 10;
            this.shdXOffset = 5;
            this.shdYOffset = 5;
            this.isHovered = false;
            this.isPressed = false;
            this.defaultColor = getBackground(); // Save the default color
            this.btnColor = btnColor;

            // Add mouse listener for hover effect
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    isHovered = true;
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    isHovered = false;
                    //setBackground(btnColor); // Revert to default color
                    repaint();
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    isPressed = true;
                    //setBackground(LMAUVE); // Revert to default color
                    repaint();
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    isPressed = false;
                    //setBackground(btnColor); // Revert to default color
                    repaint();
                }
            });


            //  This is what happens when we click this button
            addActionListener(clickEvent);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g1 = (Graphics2D) g.create();        //  Button
            Graphics2D g2 = (Graphics2D) g.create();        //  Shadow

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Calculate shadow offsets
            int shadowXOffset = isPressed ? shdXOffset + 3 : shdXOffset;
            int shadowYOffset = isPressed ? shdYOffset + 3 : shdYOffset;


            // Draw SHADOW based on state
            if (isHovered || isPressed) {
                g2.setColor(SHAD128);
                g2.fillRoundRect(shadowXOffset, shadowYOffset, getWidth() - shadowXOffset, getHeight() - shadowYOffset, 20, 20);
            }

            //  Draw button based on state
            if (isPressed) {
                g1.setColor(LPEACH); // Color when pressed
                g1.fillRoundRect(3, 3, getWidth() - 6, getHeight() - 6, 20, 20); // Adjusted for pressed state
            }
            else if (isHovered) {
                g1.setColor(LGREEN); // Color when hovered
                g1.fillRoundRect(0, 0, getWidth() - shadowXOffset, getHeight() - shadowYOffset, 20, 20);
            }
            else {
                g1.setColor(btnColor); // Default button color
                g1.fillRoundRect(0, 0, getWidth() - shadowXOffset, getHeight() - shadowYOffset, 20, 20);
            }

            //  Draw button text
            drawText(g1, getText(), getFont(), getForeground(), isPressed ? 3 : 0, isPressed ? 3 : 0); // Adjust text position when pressed

            g1.dispose();
            g2.dispose();
        }

        // Draw button text
        private void drawText(Graphics2D g, String text, Font font, Color color, int xOffset, int yOffset)
        {
            g.setFont(FNLABEL);
            g.setColor(NAVYBL);

            FontMetrics fm = g.getFontMetrics();
            Rectangle r = getBounds();
            int x = (r.width - fm.stringWidth(text)) / 2 + xOffset-2;
            int y = (r.height + fm.getAscent()) / 2 - 4 + yOffset;

            g.drawString(text, x, y);
        }
    }
    //  BUTTONS END






    //  IMAGE BUTTON

    public static class ImgButton extends JButton
    {
        public ImgButton(Container frame, String path, int[] dim, Color bgColor)
        {
            //  Loading an image icon with an image on file
            ImageIcon img = new ImageIcon(path);

            //  Attaching the image icon to this JButton
            this.setIcon(img);

            // Set the bounds of the button
            this.setBounds(dim[0], dim[1], dim[2], dim[3]);

            //  this.setBorder(null); // Removes border

            // Set a 1px gray border
            this.setBorder(new LineBorder(SHAD128, 1));

            // Set the background color and fill the content area
            this.setContentAreaFilled(true); // Makes the background transparent
            this.setBackground(bgColor); // Sets the background color

            // Add the button (this) to the frame
            frame.add(this);
            frmRefresh(frame); // Assuming frmRefresh is a method you have defined
        }
    }









    //  IMAGE CONSTRUCTOR

    public static class ImageLabel extends JLabel {
        private String imageFilename; // Instance variable to store the image filename
        private static final String IMAGE_DIRECTORY = "img/"; // Directory where images are stored on the server

        public ImageLabel(Container frame, String imagePath, int[] possPhoto) {
            this.imageFilename = imageFilename;   // Store the image filename

            //  Create an image label
            try {
                //  1. Load the photo
                ImageIcon photo0 = new ImageIcon(imagePath);

                //  2. Convert to Image from ImageIcon in order to scale it
                Image photo1 = photo0.getImage();

                //  3. Resize the image to wd x ht pixels
                Image photo2 = photo1.getScaledInstance(possPhoto[2], possPhoto[3], Image.SCALE_SMOOTH);

                //  4. Convert Image back to ImageIcon
                ImageIcon photo3 = new ImageIcon(photo2);

                //  5. Attach image icon to the label
                this.setIcon(photo3);

                //  6. Horizontal and Vertical Position, and Width and Height of the image label
                this.setBounds(possPhoto[0], possPhoto[1], possPhoto[2], possPhoto[3]);

                //  7. ADD A BORDER to the photo
                Border bdrPhoto = BorderFactory.createLineBorder(Color.GRAY, 1);  //  Gray border, 1 pixel wide

                //  8. Attach the photo border to the label
                this.setBorder(bdrPhoto);

                //  9. Make sure that the frame is updated
                frame.add(this);                   //  Attach to frame
                frmRefresh(frame);                 //  Refresh the frame
            }
            catch (Exception e) { prtln("Failed to load image: " + e.getMessage()); }
        }
    }







    //  TABLE


    public static class CustTable extends JComponent
    {
        private JTable table;
        public CustTable(WinFrame frame, int[] dim, String csvData, String[] columns, int[] colWds, Color[] colors)
        {

            DefaultTableModel tableModel = new DefaultTableModel(columns, 0);
            JTable table = new JTable(tableModel);



            //  ROW SELECTION LISTENER TO THE TABLE
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent event)
                {
                    if (!event.getValueIsAdjusting()) {
                        int selectedRow = table.getSelectedRow();
                        if (selectedRow != -1) {
                            // Show Delete and Update buttons
                            Table.showButtons(frame, table, selectedRow);
                        }
                    }
                }
            });




            //  Parse the CSV DATA
            String[] rows = csvData.split("\n");    //  Separate each row in the CSV DATA, with a new line

            for (String row : rows)
            {
                String[] data = row.split(",");     //  Create a row array based on the comma separation.
                tableModel.addRow(data);                  //  Add rows to the table
            }


            //  Column Widths
            setColumnWidths(table, colWds);

            //  Color Rows
            table.setDefaultRenderer(Object.class, new customCells(colors));


            //  Place table within bounds
            JScrollPane tableScrollPane = new JScrollPane(table);
            tableScrollPane.setBounds(dim[0], dim[1], dim[2], dim[3]);

            //  Place the table in the frame
            frame.add(tableScrollPane, BorderLayout.CENTER);
        }

        //  TABLE COLUMN WIDTH

        private static void setColumnWidths(JTable table, int[] colWds)
        {
            for (int i = 0; i < colWds.length; i++) {
                table.getColumnModel().getColumn(i).setPreferredWidth(colWds[i]);
            }
        }


        //  ALTERNATING COLORS ON ROWS

        public static class customCells extends DefaultTableCellRenderer
        {
            //  Internalize the colors
            private Color[] colors;

            public customCells(Color[] colors) {  this.colors = colors;  }

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                //  Row Colors
                Color bgColor = (row % 2 == 0) ? colors[0] : colors[1];


                // Color for selected row
                if (isSelected) {
                    cell.setBackground(colors[2]);
                } else {
                    cell.setBackground(bgColor);
                }

                return cell;
            }
        }


        public JTable getTable() {
            return table;
        }

        public void getModel() { table.getModel(); }
    }

}


//  Static if I need a utility-like method that doesn't need to maintain state or be associated with an instance:
//      If the method does not depend on creating an instance of the class.
//      The static method can be called directly using the class name, without creating an object.
//      It's useful for utility methods that perform general functions.
//  Non-static: It gives me more flexibility and the ability to extend functionality or override the method in a subclass.