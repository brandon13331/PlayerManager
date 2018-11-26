package ui;

import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerManager extends JFrame implements ActionListener {
    private User user;
    private DefaultListModel<String> teamOne;
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
        user = new User();

        setTitle("Player Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        JPanel panel = new JPanel();
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        setLayout(new FlowLayout());

        JLabel label = new JLabel("Welcome To Player Manager!");
        label.setFont(new Font("Arial", Font.BOLD, 32));
        panel.add(label);

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        JRadioButton team1 = new JRadioButton("Team 1");
        team1.setMnemonic(KeyEvent.VK_B);
        team1.setActionCommand("team1");
        team1.setSelected(true);

        JRadioButton team2 = new JRadioButton("Team 2");
        team2.setMnemonic(KeyEvent.VK_C);
        team2.setActionCommand("team2");

        add(team1);
        add(team2);

        ButtonGroup group = new ButtonGroup();
        group.add(team1);
        group.add(team2);

        team1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getActionCommand().equals("team1")) {
                    teamOne = new DefaultListModel<>();
                    List<String> lines = null;
                    try {
                        lines = Files.readAllLines(Paths.get("team1.txt"));
                    } catch (IOException e) {
                    }
                    for (String line : lines) {
                        ArrayList<String> partsOfLine = splitOnSpace(line);
                        String name = partsOfLine.get(0);
                        teamOne.addElement(name);
                    }
                    option();
                }
            }
        });
        team2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if (evt.getActionCommand().equals("team2")) {

                    DefaultListModel<String> list = new DefaultListModel<>();
                    list.addElement("team2");
                    JList<String> jList = new JList<>(list);
                    add(jList);

                    // Font
                    jList.setFont(new Font("Arial", Font.BOLD, 32));

                    // Scrollpane
                    add(new JScrollPane(jList));

                    setVisible(true);
                }
            }
        });
    }

    public void option() {
        JFrame frame = new JFrame();
        frame.setTitle("Player Manager");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        ((JPanel) getContentPane()).setBorder(new EmptyBorder(13, 13, 13, 13));
        frame.setLayout(new FlowLayout());

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

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(field);
        frame.add(enterButton);
        frame.pack();
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand().equals("enter") && field.getText().equals("1")) {
            JFrame frame = new JFrame();
            frame.setTitle("Player Manager");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel panel1 = new JPanel();
            frame.add(panel1);

            JList<String> jList = new JList<>(teamOne);
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
