package cop2805;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;




public class Test {
    public static class ChkBx extends JCheckBox {
        // This hashmap will handle multiple checkboxes in the frame.
        private static final Map<String, JCheckBox> selectedCheckboxes = new HashMap<>();
        private String groupName;

        public ChkBx(Container frame, String groupName, int[] display, Font font, Color color, boolean selected, String text) {
            // Modify text to go in 2 lines: Use HTML for multi-line text
            super("<html>" + text.replace("\n", "<br>") + "</html>");

            // Store the group name for later use
            this.groupName = groupName;

            // Set position & tab order
            this.setBounds(display[0], display[1], display[2], display[3]);
            this.setBackground(color); // Set background color to match the frame's bg color
            this.setSelected(selected);

            // Set custom font and color
            this.setFont(font); // Change font size here
            this.setForeground(Color.BLACK); // Change font color here

            // Handling their state
            this.addActionListener(e -> handleCheckboxSelection(this, this.groupName));

            // Add checkbox to the frame
            frame.add(this);
        }

        // This ensures that only one checkbox in the same group (groupName) remains selected.
        private void handleCheckboxSelection(JCheckBox selectedCheckBox, String groupName) {
            System.out.println("Checkbox selected: " + selectedCheckBox.getText());
            selectedCheckboxes.entrySet().removeIf(entry -> entry.getKey().equals(groupName) && entry.getValue() != selectedCheckBox);
            selectedCheckboxes.put(groupName, selectedCheckBox);
        }

        // Method to get the selected checkbox text from a group
        public static String getSelectedCheckboxText(String groupName) {
            JCheckBox selectedCheckBox = selectedCheckboxes.get(groupName);
            System.out.println("Retrieved checkbox for group " + groupName + ": " + (selectedCheckBox != null ? selectedCheckBox.getText() : "null"));
            return selectedCheckBox != null ? selectedCheckBox.getText() : null;
        }
    }

    public static void main(String[] args) {
        JFrame frame1 = new JFrame();
        frame1.setLayout(null);
        frame1.setSize(400, 400);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Font FNLABEL = new Font("Arial", Font.PLAIN, 14);
        Color LTBLUE = Color.LIGHT_GRAY;
        int[] chkNotify = {50, 50, 100, 30, 1};

        ChkBx chkNotif = new ChkBx(frame1, "notify", chkNotify, FNLABEL, LTBLUE, false, "Notifications");

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(50, 100, 100, 30);
        submitButton.addActionListener(e -> {
            String Notif = ChkBx.getSelectedCheckboxText("notify");
            System.out.println("Selected checkbox text to be sent to server: " + Notif);
        });
        frame1.add(submitButton);

        frame1.setVisible(true);
    }
}
