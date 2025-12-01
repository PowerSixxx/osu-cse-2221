import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//GUI - Graphical User Interface
public class GUI implements ActionListener{
    
    private int count = 0;
    private JLabel label;
    private JFrame frame;
    private JPanel panel;
    /*
     * Constructor, when you call main method, it will come here. Basically,
     * this is a method Set up frame, the layout, and everything in here
     */
    public GUI() {
        // Create frame (window), we want to put some stuff in window
        frame = new JFrame();
        panel = new JPanel();
        JButton button = new JButton("Click Me");
        button.addActionListener(this);
        label = new JLabel("Number of clicks: 0");

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 10));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Our GUI");
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Number of clicks: " + count);
    }

}
