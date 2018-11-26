package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerManager extends JFrame implements ActionListener {
    private JButton button1;
    private JButton button2;

    public static void main(String[] args) {
        new PlayerManager();
    }

    public PlayerManager() {
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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 1000);

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
