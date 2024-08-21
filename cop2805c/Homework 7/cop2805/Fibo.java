package cop2805;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class Fibo extends JFrame
{
    private JLabel lblEnter;
    private JPanel Thread_Calc;
    private JTextField textName;
    private JButton btnClick;

    public Fibo()
    {
        // Initialize the components
        Thread_Calc = new JPanel();
        lblEnter = new JLabel();
        textName = new JTextField(15);
        btnClick = new JButton(" Click Me ");

        // Add components to the panel
        Thread_Calc.add(lblEnter);
        Thread_Calc.add(textName);
        Thread_Calc.add(btnClick);

        btnClick.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                JOptionPane.showMessageDialog(btnClick, textName.getText());
            }
        });

        // Add the panel to the frame
        add(Thread_Calc);
    }



    public static void main(String[] args)
    {
        Fibo win = new Fibo();
        win.setContentPane(win.Thread_Calc);
        win.setTitle("Fibonacci");
        win.setBounds(1400, 1400, 500, 300);
        win.setVisible(true);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
