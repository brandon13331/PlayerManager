package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerManager extends JFrame implements ActionListener {
    private JTextField field;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
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

        label1 = new JLabel("Enter:");
        label1.setFont(new Font("Arial", Font.BOLD, 16));
        panel1.add(label1);

        label2 = new JLabel("1. to see your players");
        label2.setFont(new Font("Arial", Font.PLAIN, 16));
        panel2.add(label2);

        label3 = new JLabel("2. to buy or sell players");
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

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("enter") && field.getText().equals("1")) {
            JFrame frame = new JFrame();
            frame.setTitle("Player Manager");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel panel1 = new JPanel();
            frame.add(panel1);

            DefaultListModel<String> list = new DefaultListModel<>();
            List<String> lines = null;
            try {
                lines = Files.readAllLines(Paths.get("inputfile.txt"));
            } catch (IOException e) {
            }
            for (String line : lines) {
                ArrayList<String> partsOfLine = splitOnSpace(line);
                String name = partsOfLine.get(0);
                list.addElement(name);
            }
            JList<String> jList = new JList<>(list);
            frame.add(jList);

            // Font
            jList.setFont(new Font("Arial", Font.BOLD, 32));

            // Scrollpane
            frame.add(new JScrollPane(jList));

            frame.setVisible(true);
        } else if (evt.getActionCommand().equals("enter") && field.getText().equals("2")) {
            JFrame frame = new JFrame();
            frame.setTitle("Player Manager");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel panel1 = new JPanel();
            frame.add(panel1);

            DefaultListModel<String> list = new DefaultListModel<>();
            list.addElement("b");
            JList<String> jList = new JList<>(list);
            frame.add(jList);

            // Font
            jList.setFont(new Font("Arial", Font.BOLD, 32));

            // Scrollpane
            frame.add(new JScrollPane(jList));

            frame.setVisible(true);
        }
        if (evt.getActionCommand().equals("button2")) {
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

    // splitting line up by spaces
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
