package cop2805;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Fibona extends JFrame
{
    private JPanel pnlFibona;
    private JTextField textFiboSeq;
    private JSlider sliderSleep;
    private JProgressBar pgrCalcFibo;
    private JButton btnCalculate;
    private JLabel lblResult;


    public Fibona()
    {
        //  Initialize the components
        textFiboSeq = new JTextField(10);
        sliderSleep = new JSlider();
        pgrCalcFibo = new JProgressBar();
        btnCalculate = new JButton("Calculate");
        lblResult = new JLabel("Result: ");


        // Initialize the panel and set layout
        pnlFibona = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);  // Add padding


        // Add label and text field to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        pnlFibona.add(new JLabel("Enter Fibonacci sequence:"), gbc);


        gbc.gridx = 1;
        textFiboSeq.setPreferredSize(new Dimension(100, 30));
        pnlFibona.add(textFiboSeq, gbc);




        // Initialize the panel and set layout
        pnlFibona = new JPanel();
        pnlFibona.setLayout(new BoxLayout(pnlFibona, BoxLayout.Y_AXIS));


        //  Add the components to the panel
        pnlFibona.add(new JLabel("Enter Fibonacci Sequence:"));
        pnlFibona.add(textFiboSeq);
        pnlFibona.add(sliderSleep);
        pnlFibona.add(btnCalculate);
        pnlFibona.add(pgrCalcFibo);


        // Add the panel to the frame
        add(pnlFibona);

        // Add ActionListener to the button
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateFibonacci();
            }
        });
    }



    private void calculateFibonacci() {
        try {
            // Get the value from the text field
            int n = Integer.parseInt(textFiboSeq.getText());

            // Create a new recursive thread and start it
            Recursive recursiveThread = new Recursive(n);
            recursiveThread.start();

            // Wait for the thread to finish (join)
            recursiveThread.join();

            // Get the results from the thread
            int fiboNumber = Recursive.getFiboNumber();
            long timeDiff = Recursive.getTimeDiff();

            // Display the results
            JOptionPane.showMessageDialog(this, "Fibonacci number: " + fiboNumber + "\nTime taken: " + timeDiff + " ms");
        }
        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
        catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(this, "Calculation was interrupted.");
        }
    }


    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run()
            {
                Fibona win = new Fibona();
                win.setContentPane(win.pnlFibona);
                win.setTitle("Fibonacci");
                win.setBounds(1400, 1400, 500, 300);
                win.setVisible(true);
                win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }



    public static class Recursive extends Thread {
        private static int n, fiboNumber;
        private static long timeDiff;

        public Recursive(int n) {
            this.n = n;
        }

        public static int getFiboNumber() {
            return fiboNumber;
        }

        public static long getTimeDiff() {
            return timeDiff;
        }

        @Override
        public void run() {
            long startTimer = System.currentTimeMillis();   //  Start the clock
            fiboNumber = fibonacci(n);                      //  Call the recursive method
            long stopTimer = System.currentTimeMillis();    //  Stop the clock
            timeDiff = stopTimer - startTimer;              //  Calculate the difference in time
        }

        private int fibonacci(int n) {
            if (n == 0) return 0;
            else if (n == 1) return 1;
            else {
                return fibonacci(n - 1) + fibonacci(n - 2);
            }
        }
    }
}
