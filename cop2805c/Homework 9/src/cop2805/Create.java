package cop2805;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;

import static java.awt.Color.*;
/*
    Color.BLACK
    Color.BLUE
    Color.CYAN
    Color.DARK_GRAY
    Color.GRAY
    Color.GREEN
    Color.LIGHT_GRAY
    Color.MAGENTA
    Color.ORANGE
    Color.PINK
    Color.RED
    Color.WHITE
    Color.YELLOW
*/





public class Create extends JFrame
{
    //  WINDOW FRAME

    static class Frame extends JFrame {
        //  Window Frame Constuctor
        public Frame(String title, int[] display, Color bgColor) {
            //JFrame frame = new JFrame(title);             //  Create a new JFrame with the given title
            //  by using super we don't need to create a new frame above.
            super(title);                                   //  This call the superclass constructor, with a title
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //frame.setLocationRelativeTo(null)             //  This would center the frame on the screen. I want to define that
            setLayout(null);                                //  This allows for absolute positioning
            //  This will display the position and size of the frame
            setBounds(display[0], display[1], display[2], display[3]);
            getContentPane().setBackground(bgColor);        //  This sets a background color

            setVisible(true);
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
    /*public static void applyFocus(Container frame) {
        frame.setFocusTraversalPolicy(new TabOrder(tabOrder));
    }*/





    //  COMPONENTS



    //  TEXT LABEL

    public static class TxtLb extends JLabel  {
        public TxtLb(Container frame, int[] display, Color bgColor, Font font, Color fntCol, String text) {
            super(text);
            this.setBounds(display[0], display[1], display[2], display[3]);
            tabOrder.put(this, display[4]);  // Map (tag) the label with a tab number
            this.setBackground(bgColor);     // This sets a background color
            this.setOpaque(true);            // Needed to actually paint the background color
            this.setFont(font);
            this.setForeground(fntCol);
            frame.add(this);
            frmRefresh(frame);
        }
    }

    public static String getLblTxt(JLabel label) {
        return label.getText();
    }



    //  TEXT FIELD

    public static class TxtFd extends JTextField {
        public TxtFd(Container frame, int[] display, Font font, Color color, String text) {
            super(text);  // Call the superclass constructor with the initial text
            setBounds(display[0], display[1], display[2], display[3]);
            tabOrder.put(this, display[4]);  //  Map (tag) the text field with a tab number
            setFont(font);                   // Set the font
            setForeground(color);            // Set the font color
            frame.add(this);
            frmRefresh(frame);
        }

        // Custom method to set the text field to blank
        public void clearText() {
            setText("");  // Set the text to an empty string
        }
    }




    //  TEXT AREA

    public static class TextA extends JComponent
    {
        private JTextArea textArea;                     //  Store a reference to the JTextArea
        public TextA(Container frame, int[] display, Font font, Color color, String text)
        {
            textArea = new JTextArea(text);
            textArea.setFont(font);                     //  Set the font on the text area
            textArea.setForeground(color);              //  Set the font color on the text area

            textArea.setLineWrap(true);                 //  Enable line wrap
            textArea.setWrapStyleWord(true);            //  Wrap lines at word boundaries

            JScrollPane textAScrollPane = new JScrollPane(textArea);
            textAScrollPane.setBounds(display[0], display[1], display[2], display[3]);

            textAScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disable horizontal scrollbar
            textAScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Enable vertical scrollbar as needed

            tabOrder.put(textAScrollPane, display[4]);  //  Map (tag) the text area box with a tab number

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
    }





    // BUTTON

    public static class Buttn extends JButton
    {
        public Buttn(Container frame, int[] display, Font font, Color color, String text, ActionListener clickEvent)
        {
            //JButton button = new JButton(text);
            super(text);  //  Call the superclass constructor with the initial text
            setBounds(display[0], display[1], display[2], display[3]);
            tabOrder.put(this, display[4]);
            setFont(font);                              // Set the font
            setForeground(color);                       // Set the font color

            //  This is what happens when we click this button
            addActionListener(clickEvent);

            frame.add(this);
            frmRefresh(frame);
        }
    }



    //  CHECKBOX

    public static class ChkBox extends JCheckBox
    {
        //  This hashmap will handle multiple checkboxes in the frame.
        private static final Map<String, JCheckBox> selectedCheckboxes = new HashMap<>();

        public ChkBox(Container frame, int[] display, Color color, String text, boolean selected, String groupName)
        {
            super(text);
            this.setBounds(display[0], display[1], display[2], display[3]);
            tabOrder.put(this, display[4]);         // Map (tag) the button with a tab number
            this.setBackground(color);              // Set background color to match the frame's bg color
            this.setSelected(selected);

            // Handling their state
            this.addActionListener(e -> handleCheckboxSelection(this, groupName));

            // Add checkbox to the frame
            frame.add(this);
            frmRefresh(frame);
        }

        //  This ensures that only one checkbox in the same group (groupName) remains selected.
        private void handleCheckboxSelection(JCheckBox selectedCheckBox, String groupName)
        {
            selectedCheckboxes.entrySet().removeIf(entry -> entry.getKey().equals(groupName) && entry.getValue() != selectedCheckBox);
            selectedCheckboxes.put(groupName, selectedCheckBox);
        }


        //  Method to get the selected checkbox text from a group
        public static String getSelectedCheckboxText(String groupName)
        {
            JCheckBox selectedCheckBox = selectedCheckboxes.get(groupName);
            return selectedCheckBox != null ? selectedCheckBox.getText() : null;
        }
    }




    //   SELECT SPINNER

    public static class Select extends JSpinner {
        public Select(Container frame, int[] display, Font font, Color color, String[] items, String initialValue)
        {
            super();
            setBounds(display[0], display[1], display[2], display[3]);

            //  This makes it a Select spinner instead of a number spinner
            SpinnerListModel model = new SpinnerListModel(items);
            setModel(model);

            // Set the initial value to the specified item
            setValue(initialValue);

            // Set the font for the spinner and its editor
            setFont(font);
            setForeground(color);

            JComponent editor = getEditor();
            if (editor instanceof JSpinner.DefaultEditor) {
                ((JSpinner.DefaultEditor) editor).getTextField().setFont(font);
                ((JSpinner.DefaultEditor) editor).getTextField().setForeground(color);
            }

            tabOrder.put(this, display[4]);  //  Map (tag) the button with a tab number
            frame.add(this);
            frmRefresh(frame);
        }

        // Method to get the selected item
        public String getSelectedItem() {
            return (String) getValue();
        }
    }




    //   NUMBER SPINNER

    public static class Spinner extends JSpinner {
        public Spinner(Container frame, int[] display, int min, int max, int step, int value) {
            JSpinner spinner = new JSpinner();
            spinner.setBounds(display[0], display[1], display[2], display[3]);
            SpinnerNumberModel model = new SpinnerNumberModel(value, min, max, step);
            spinner.setModel(model);
            tabOrder.put(spinner, display[4]);  //  Map (tag) the button with a tab number
            frame.add(spinner);
            frmRefresh(frame);
        }
    }




    //  RADIO BUTTON

    private static final Map<String, ButtonGroup> buttonGroup = new HashMap<>();  //  For the selection: Male or Female


    public static class Radio extends JRadioButton {
        public Radio(Container frame, int[] display, Color bgColor, String text, Font font, Color color, boolean selected, String groupName) {
            super(text);                        // Call the JRadioButton constructor with the text
            this.setBounds(display[0], display[1], display[2], display[3]);
            this.setSelected(selected);         // Set the selected state
            this.setFont(font);                 // Set the font
            this.setForeground(color);          // Set the font color
            this.setBackground(bgColor);        //  Background color matches the frame's bg color
            //radioButton.setOpaque(false);     //  With this line I don't need the previous line (esp. if it has an alpha channel)

            // Create the Gender ButtonGroup
            ButtonGroup group = buttonGroup.computeIfAbsent(groupName, k -> new ButtonGroup());
            group.add(this);                    // Add to ButtonGroup

            tabOrder.put(this, display[4]);     //  Map (tag) the button with a tab number
            frame.add(this);                    // Add to Frame
            frmRefresh(frame);
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




    //  PROGRESS BAR

    public static class PrBar extends JProgressBar {
        public PrBar(Container frame, int[] display, int min, int max, int value, Color bgColor) {
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
            progressBar.setBounds(display[0], display[1], display[2], display[3]);
            progressBar.setBackground(bgColor);  // Set background color if needed
            tabOrder.put(progressBar, display[4]);  // Map (tag) the progress bar with a tab number
            Border border = BorderFactory.createLineBorder(Color.GRAY, 2);  //  1-pixel grey border
            progressBar.setBorder(border);
            frame.add(progressBar);
            frmRefresh(frame);
        }
    }





    //  SLIDER

    public static class Slidr extends JSlider {
        public Slidr(Container frame, int[] display, int min, int max, int value, boolean ticks, boolean labels, Color bgColor, JLabel linkedLabel) {
            JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, value);
            slider.setBounds(display[0], display[1], display[2], display[3]);
            slider.setPaintTicks(ticks);
            slider.setPaintLabels(labels);
            slider.setMajorTickSpacing((max - min) / 10);   //  Major tick spacing
            slider.setMinorTickSpacing((max - min) / 20);   //  Minor tick spacing

            slider.addChangeListener(e -> {                 //  Add Action/Change Listener to update the label
                //int sliderValue = slider.getValue();      //  Moved this functions to Action.java
                //linkedLabel.setText(String.valueOf(sliderValue));
                //Action.onSliderChg(slider,linkedLabel);
            });

            tabOrder.put(slider, display[4]);               //  Map to a tab number
            slider.setBackground(bgColor);                  //  Background Color
            frame.add(slider);
            frmRefresh(frame);
        }
    }

    public static String getSldVal(JSlider slider) {
        return String.valueOf(slider.getValue());
    }




    //  JPANEL

    public static class Panel extends JPanel
    {
        public Panel(Container frame, int[] display, Color ltBlue, String text)
        {
            JPanel panel = new JPanel();
            panel.setBounds(display[0], display[1], display[2], display[3]);
            panel.setBackground(ltBlue);        //  Background color, otherwise it's white
            frame.add(panel);                   //  Attach to frame
            frmRefresh(frame);                   //  Refresh the frame
        }
    }



    //  PASSWORD

    public static class Passwd extends JComponent {
        public Passwd(Container frame, int[] display, String text)
        {
            JPasswordField password = new JPasswordField(text);
            password.setBounds(display[0], display[1], display[2], display[3]);
            tabOrder.put(password, display[4]);
            frame.add(password);
            frmRefresh(frame);
        }
    }




    //  IMAGE CONSTRUCTOR

    //  Static if I need a utility-like method that doesn't need to maintain state or be associated with an instance:
    //      If the method does not depend on creating an instance of the class.
    //      The static method can be called directly using the class name, without creating an object.
    //      It's useful for utility methods that perform general functions.
    //  Non-static: It gives me more flexibility and the ability to extend functionality or override the method in a subclass.
    public static class CreateImageLabel extends JLabel {
        public CreateImageLabel(String imagePath, int[] photoPoss) {
            //  Create an image label
            try {
                //  Load the photo
                ImageIcon photo0 = new ImageIcon(imagePath);
                //  Convert to Image from ImageIcon in order to scale it
                Image photo1 = photo0.getImage();
                //  Resize the image to wd x ht pixels
                Image photo2 = photo1.getScaledInstance(photoPoss[2], photoPoss[3], Image.SCALE_SMOOTH);
                //  Convert Image back to ImageIcon
                ImageIcon photo3 = new ImageIcon(photo2);
                //  Attach image icon to the label
                this.setIcon(photo3);
                //  Horizontal and Vertical Position, and Width and Height of the image label
                this.setBounds(photoPoss[0], photoPoss[1], photoPoss[2], photoPoss[3]);
                //  ADD A BORDER to the photo
                Border bdrPhoto = BorderFactory.createLineBorder(GRAY, 1);  //  Gray border, 1 pixel wide
                //  Attach the photo border to the label
                this.setBorder(bdrPhoto);
                //  Make sure that the frame is updated
                frmRefresh(this);
            } catch (Exception e) {
                System.out.println("Failed to load image: " + e.getMessage());
            }
        }
    }



}
