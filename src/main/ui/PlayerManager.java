package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerManager extends JFrame implements ActionListener {
    private JTextField field;
    private JButton enterButton;
    private JButton button1;
    private JButton button2;

    public static void main(String[] args) {
        new PlayerManager();
    }

    public PlayerManager() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        JLabel label1 = new JLabel("Enter:");
        label1.setFont(new Font("Arial", Font.BOLD, 16));
        panel1.add(label1);

        JLabel label2 = new JLabel("1. to see your players");
        label2.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(label2);

        JLabel label3 = new JLabel("2. to buy or sell players");
        label3.setFont(new Font("Arial", Font.PLAIN, 16));
        panel3.add(label3);

        enterButton = new JButton("Enter");
        enterButton.setActionCommand("enter");
        enterButton.addActionListener(this);

        field = new JTextField(5);
        add(panel1);
        add(panel2);
        add(panel3);
        add(field);
        add(enterButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void Temp() {
        JFrame frame = new JFrame();
        frame.setTitle("Player Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        JPanel panel1 = new JPanel();
        frame.add(panel1);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        // Button 1
        button1 = new JButton("1");
        button1.setActionCommand("button1");
        panel1.add(button1);
        button1.addActionListener(this);

        // Button 2
        button2 = new JButton("2");
        button2.setActionCommand("button2");
        panel1.add(button2);
        button2.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("enter")) {
            JFrame frame = new JFrame();
            frame.setTitle("Player Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel panel1 = new JPanel();
            frame.add(panel1);

            DefaultListModel<String> list = new DefaultListModel<>();
            list.addElement("a");
            JList<String> jList = new JList<>(list);
            frame.add(jList);

            // Font
            jList.setFont(new Font("Arial", Font.BOLD, 32));

            // Scrollpane
            frame.add(new JScrollPane(jList));

            frame.setVisible(true);
        }

        if (e.getActionCommand().equals("button1")) {
            DefaultListModel<String> list = new DefaultListModel<>();
            list.addElement("a");
            JList<String> jList = new JList<>(list);
            add(jList);

            // Font
            jList.setFont(new Font("Arial", Font.BOLD, 32));

            // Scrollpane
            add(new JScrollPane(jList));

            setVisible(true);
        } else if (e.getActionCommand().equals("button2")) {
            DefaultListModel<String> list = new DefaultListModel<>();
            list.addElement("b");
            JList<String> jList = new JList<>(list);
            add(jList);

            // Font
            jList.setFont(new Font("Arial", Font.BOLD, 32));

            // Scrollpane
            add(new JScrollPane(jList));

            setVisible(true);
        }
    }
}
