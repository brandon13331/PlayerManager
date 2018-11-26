package ui;

import model.*;

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
    private User user;
    private JRadioButton team1;
    private JRadioButton team2;
    private DefaultListModel<String> teamOne;
    private DefaultListModel<String> teamTwo;
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
        setTitle("Player Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        JPanel panel = new JPanel();
        setLayout(new FlowLayout());

        // Welcome to player manager
        JLabel label1 = new JLabel("Welcome To Player Manager!");
        label1.setFont(new Font("Arial", Font.BOLD, 32));
        panel.add(label1);

        // Select your team
        JLabel label2 = new JLabel("Select your team");
        label2.setFont(new Font("Arial", Font.PLAIN, 27));
        panel.add(label2);

        // Team 1
        team1 = new JRadioButton("Team 1");
        team1.setFont(new Font("Arial", Font.PLAIN, 27));
        team1.setSelected(true);

        // Team 2
        team2 = new JRadioButton("Team 2");
        team2.setFont(new Font("Arial", Font.PLAIN, 27));

        // Select button
        JButton button = new JButton("Select");
        button.setActionCommand("select");
        button.setFont(new Font("Arial", Font.PLAIN, 27));

        add(panel);
        add(team1);
        add(team2);
        add(button);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        ButtonGroup group = new ButtonGroup();
        group.add(team1);
        group.add(team2);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Initialize user
                user = new User();

                // Team 1 selected
                if (team1.isSelected() && evt.getActionCommand().equals("select")) {
                    teamOne = new DefaultListModel<>();
                    List<String> lines = null;
                    try {
                        lines = Files.readAllLines(Paths.get("team1.txt"));
                    } catch (IOException e) {
                    }
                    for (String line : lines) {
                        ArrayList<String> partsOfLine = splitOnSpace(line);
                        addPlayer(partsOfLine);
                    }
                    option();
                }
                // Team 2 selected
                else if (team2.isSelected() && evt.getActionCommand().equals("select")) {
                    teamTwo = new DefaultListModel<>();
                    List<String> lines = null;
                    try {
                        lines = Files.readAllLines(Paths.get("team2.txt"));
                    } catch (IOException e) {
                    }
                    for (String line : lines) {
                        ArrayList<String> partsOfLine = splitOnSpace(line);
                        addPlayer(partsOfLine);
                    }
                    option();
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
        // 1 entered
        if (evt.getActionCommand().equals("enter") && field.getText().equals("1")) {
            JFrame frame = new JFrame();
            frame.setTitle("Player Manager");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel panel1 = new JPanel();
            frame.add(panel1);

            JList<String> jList = new JList<>();
            // Team 1 was selected
            if (team1.isSelected()) {
                for (int i = 0; i < user.getPlayers().size(); i++) {
                    if (!teamOne.contains(user.getPlayers().get(i).getName())) {
                        teamOne.addElement(user.getPlayers().get(i).getName());
                    }
                }
                jList = new JList<>(teamOne);
            }
            // Team 2 was selected
            else if (team2.isSelected()) {
                for (int i = 0; i < user.getPlayers().size(); i++) {
                    if (!teamTwo.contains(user.getPlayers().get(i).getName())) {
                        teamTwo.addElement(user.getPlayers().get(i).getName());
                    }
                }
                jList = new JList<>(teamTwo);
            }
            frame.add(jList);

            // Font
            jList.setFont(new Font("Arial", Font.BOLD, 32));

            // Scrollpane
            frame.add(new JScrollPane(jList));

            frame.setVisible(true);
        }
        // 2 entered
        else if (evt.getActionCommand().equals("enter") && field.getText().equals("2")) {
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

    public void addPlayer(ArrayList<String> list) {
        if (list.get(1).contains("W") || list.get(1).contains("ST")) {
            user.getPlayers().add(new Forward(list.get(0)));
        } else if (list.get(1).contains("M")) {
            user.getPlayers().add(new Midfielder(list.get(0)));
        } else if (list.get(1).contains("B")) {
            user.getPlayers().add(new Defender(list.get(0)));
        } else if (list.get(1).contains("GK")) {
            user.getPlayers().add(new Goalkeeper(list.get(0)));
        }
    }

    // splitting line up by spaces
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
