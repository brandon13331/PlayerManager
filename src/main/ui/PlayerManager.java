package ui;

import exceptions.IncorrectPosition;
import model.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
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
    private TransferMarket market;
    private JRadioButton rButton1;
    private JRadioButton rButton2;
    private JTextField field;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    public static void main(String[] args) {
        new PlayerManager();
    }

    public PlayerManager() {
        setTitle("Player Manager");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 500));
        setLayout(new FlowLayout());

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        // Welcome to player manager
        JLabel label1 = new JLabel("Welcome To Player Manager!");
        label1.setFont(new Font("Arial", Font.BOLD, 32));
        panel1.add(label1);
        panel1.setLayout(new GridLayout(1, 1));

        // Select team
        JLabel label2 = new JLabel("Select team");
        label2.setFont(new Font("Arial", Font.PLAIN, 27));
        panel2.add(label2);
        panel2.setLayout(new GridLayout(1, 1));

        // Team
        rButton1 = new JRadioButton("Team A");
        rButton1.setFont(new Font("Arial", Font.PLAIN, 27));

        rButton2 = new JRadioButton("Team B");
        rButton2.setFont(new Font("Arial", Font.PLAIN, 27));

        // Select default
        rButton1.setSelected(true);

        // Select button
        JButton selectButton = new JButton("Select");
        selectButton.setActionCommand("select");
        selectButton.setFont(new Font("Arial", Font.PLAIN, 27));

        add(panel1);
        add(panel2);
        add(rButton1);
        add(rButton2);
        add(selectButton);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);

        // Only one radio button selection allowed
        ButtonGroup group = new ButtonGroup();
        group.add(rButton1);
        group.add(rButton2);

        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Initialize user
                user = new User();
                market = new TransferMarket();

                // Team A selected
                if (rButton1.isSelected() && evt.getActionCommand().equals("select")) {
                    List<String> lines = null;
                    try {
                        lines = Files.readAllLines(Paths.get("user.txt"));
                    } catch (IOException e) {
                    }
                    for (String line : lines) {
                        ArrayList<String> partsOfLine = splitOnSpace(line);
                        addPlayerToUser(partsOfLine);
                    }
                    List<String> lines2 = null;
                    try {
                        lines2 = Files.readAllLines(Paths.get("market.txt"));
                    } catch (IOException e) {
                    }
                    for (String line : lines2) {
                        ArrayList<String> partsOfLine = splitOnSpace(line);
                        addPlayerToMarket(partsOfLine);
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

        label1 = new JLabel("Enter");
        label1.setFont(new Font("Arial", Font.BOLD, 32));
        panel1.add(label1);

        label2 = new JLabel("1. Club Management");
        label2.setFont(new Font("Arial", Font.PLAIN, 32));
        panel2.add(label2);

        label3 = new JLabel("2. Transfer Market");
        label3.setFont(new Font("Arial", Font.PLAIN, 32));
        panel3.add(label3);

        JButton enterButton = new JButton("Enter");
        enterButton.setFont(new Font("Arial", Font.PLAIN, 32));
        enterButton.setActionCommand("enter");
        enterButton.addActionListener(this);

        field = new JTextField();
        field.setPreferredSize(new Dimension(80, 30));
        field.setFont(new Font("Arial", Font.PLAIN, 32));

        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(field);
        frame.add(enterButton);
        frame.pack();
    }

    public void actionPerformed(ActionEvent evt) {
        // JTable
        DefaultTableModel model = new DefaultTableModel();

        // Option 1
        if (evt.getActionCommand().equals("enter") && field.getText().equals("1")) {
            JFrame frame = new JFrame();
            frame.setTitle("Player Manager");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setLayout(new FlowLayout());

            // Team A
            if (rButton1.isSelected()) {
                // Column
                String[] Column = {"Position", "Name"};
                model.setColumnIdentifiers(Column);
                // Row
                for (int i = 0; i < user.getPlayers().size(); i++) {
                    String position = "";
                    if (user.getPlayers().get(i).getPosition().contains("W") || user.getPlayers().get(i).getPosition().contains("ST")) {
                        position = "Forward";
                    } else if (user.getPlayers().get(i).getPosition().contains("M")) {
                        position = "Midfielder";
                    } else if (user.getPlayers().get(i).getPosition().contains("B")) {
                        position = "Defender";
                    } else {
                        position = "Goalkeeper";
                    }
                    String name = user.getPlayers().get(i).getName();

                    Object[] data = {position, name};
                    model.addRow(data);
                }
                JTable table = new JTable(model);
                table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
                table.setPreferredScrollableViewportSize(table.getPreferredSize());
                table.setFillsViewportHeight(true);

                // Table header
                frame.add(new JScrollPane(table));

                // See details button
                JButton detail1Button = new JButton("Show details");
                detail1Button.setActionCommand("detail1");
                frame.add(detail1Button);

                detail1Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getActionCommand().equals("detail1")) {
                            JFrame frame = new JFrame();
                            frame.setTitle("Player Manager");
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setSize(500, 400);
                            frame.setLocationRelativeTo(null);
                            frame.setResizable(false);
                            frame.setLayout(new FlowLayout());

                            JPanel panel1 = new JPanel();
                            JPanel panel2 = new JPanel();
                            JPanel panel3 = new JPanel();
                            JPanel panel4 = new JPanel();

                            JLabel labelname1 = new JLabel("Name:");
                            labelname1.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel labelname2 = new JLabel(user.getPlayers().get(table.getSelectedRow()).getName());
                            labelname2.setFont(new Font("Arial", Font.PLAIN, 32));

                            panel1.add(labelname1);
                            panel1.add(labelname2);

                            JLabel labelposition1 = new JLabel("Position:");
                            labelposition1.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel labelposition2 = new JLabel(user.getPlayers().get(table.getSelectedRow()).getPosition());
                            labelposition2.setFont(new Font("Arial", Font.PLAIN, 32));

                            panel2.add(labelposition1);
                            panel2.add(labelposition2);

                            JLabel labelratings1 = new JLabel("Ratings:");
                            labelratings1.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel labelratings2 = new JLabel(String.valueOf(user.getPlayers().get(table.getSelectedRow()).getRatings()));
                            labelratings2.setFont(new Font("Arial", Font.PLAIN, 32));

                            panel3.add(labelratings1);
                            panel3.add(labelratings2);

                            JLabel labelprice1 = new JLabel("Price:");
                            labelprice1.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel labelprice2 = new JLabel(String.valueOf(user.getPlayers().get(table.getSelectedRow()).getPrice()));
                            labelprice2.setFont(new Font("Arial", Font.PLAIN, 32));

                            panel4.add(labelprice1);
                            panel4.add(labelprice2);

                            frame.add(panel1);
                            frame.add(panel2);
                            frame.add(panel3);
                            frame.add(panel4);

                            frame.setVisible(true);
                        }
                    }
                });
                JButton sellButton = new JButton("Sell");
                sellButton.setActionCommand("sell");
                frame.add(sellButton);

                sellButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getActionCommand().equals("sell")) {
                            JFrame frame = new JFrame();
                            frame.setTitle("Player Manager");
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setSize(500, 400);
                            frame.setLocationRelativeTo(null);
                            frame.setResizable(false);
                            frame.setLayout(new FlowLayout());

                            JPanel panel1 = new JPanel();
                            JPanel panel2 = new JPanel();
                            JPanel panel3 = new JPanel();

                            JLabel label1a = new JLabel("Confirm player:");
                            label1a.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel label1b = new JLabel(user.getPlayers().get(table.getSelectedRow()).getName());
                            label1b.setFont(new Font("Arial", Font.PLAIN, 32));
                            panel1.add(label1a);
                            panel1.add(label1b);

                            JLabel label2a = new JLabel("Your balance:");
                            label2a.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel label2b = new JLabel(String.valueOf(user.getWallet().getBalance()));
                            label2b.setFont(new Font("Arial", Font.PLAIN, 32));
                            panel2.add(label2a);
                            panel2.add(label2b);

                            JLabel label3a = new JLabel("After purchase balance:");
                            label3a.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel label3b = new JLabel(String.valueOf((user.getWallet().getBalance() + user.getPlayers().get(table.getSelectedRow()).getPrice())));
                            label3b.setFont(new Font("Arial", Font.PLAIN, 32));
                            panel3.add(label3a);
                            panel3.add(label3b);

                            frame.add(panel1);
                            frame.add(panel2);
                            frame.add(panel3);

                            frame.setVisible(true);

                            JButton confirmButton = new JButton("Confirm");
                            confirmButton.setActionCommand("confirm2");
                            confirmButton.setFont(new Font("Arial", Font.PLAIN, 32));
                            frame.add(confirmButton);

                            confirmButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    int i = table.getSelectedRow();
                                    model.removeRow(i);
                                    try {
                                        market.addPlayer(user.getPlayers().get(i));
                                    } catch (IncorrectPosition incorrectPosition) {
                                        incorrectPosition.printStackTrace();
                                    }
                                    user.getWallet().addBalance(user.getPlayers().get(i).getPrice());
                                    user.getPlayers().remove(i);
                                    frame.dispose();
                                }
                            });
                        }
                    }
                });
                frame.setVisible(true);

                JButton backButton = new JButton("Back");
                backButton.setFont(new Font("Arial", Font.PLAIN, 20));
                frame.add(backButton);

                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
            }
        }
        // Option 2
        else if (evt.getActionCommand().equals("enter") && field.getText().equals("2")) {
            JFrame frame = new JFrame();
            frame.setTitle("Player Manager");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setLayout(new FlowLayout());

            // Team A
            if (rButton1.isSelected()) {
                // Column
                String[] Column = {"Type", "Name"};
                model.setColumnIdentifiers(Column);
                // Row
                for (int i = 0; i < market.getPlayers().size(); i++) {
                    String position = "";
                    if (market.getPlayers().get(i).getPosition().contains("W") || market.getPlayers().get(i).getPosition().contains("ST")) {
                        position = "Forward";
                    } else if (market.getPlayers().get(i).getPosition().contains("M")) {
                        position = "Midfielder";
                    } else if (market.getPlayers().get(i).getPosition().contains("B")) {
                        position = "Defender";
                    } else {
                        position = "Goalkeeper";
                    }
                    String name = market.getPlayers().get(i).getName();

                    Object[] data = {position, name};
                    model.addRow(data);
                }
                JTable table = new JTable(model);
                table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
                table.setPreferredScrollableViewportSize(table.getPreferredSize());
                table.setFillsViewportHeight(true);

                // Table header
                frame.add(new JScrollPane(table));

                // See details button
                JButton detail2Button = new JButton("Show details");
                detail2Button.setActionCommand("detail2");
                frame.add(detail2Button);

                detail2Button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getActionCommand().equals("detail2")) {
                            JFrame frame = new JFrame();
                            frame.setTitle("Player Manager");
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setSize(500, 400);
                            frame.setLocationRelativeTo(null);
                            frame.setResizable(false);
                            frame.setLayout(new FlowLayout());

                            JPanel panel1 = new JPanel();
                            JPanel panel2 = new JPanel();
                            JPanel panel3 = new JPanel();
                            JPanel panel4 = new JPanel();
                            JPanel panel5 = new JPanel();

                            JLabel labelname1 = new JLabel("Name:");
                            labelname1.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel labelname2 = new JLabel(market.getPlayers().get(table.getSelectedRow()).getName());
                            labelname2.setFont(new Font("Arial", Font.PLAIN, 32));

                            panel1.add(labelname1);
                            panel1.add(labelname2);

                            JLabel labelposition1 = new JLabel("Position:");
                            labelposition1.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel labelposition2 = new JLabel(market.getPlayers().get(table.getSelectedRow()).getPosition());
                            labelposition2.setFont(new Font("Arial", Font.PLAIN, 32));

                            panel2.add(labelposition1);
                            panel2.add(labelposition2);

                            JLabel labelratings1 = new JLabel("Ratings:");
                            labelratings1.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel labelratings2 = new JLabel(String.valueOf(market.getPlayers().get(table.getSelectedRow()).getRatings()));
                            labelratings2.setFont(new Font("Arial", Font.PLAIN, 32));

                            panel3.add(labelratings1);
                            panel3.add(labelratings2);

                            JLabel labelprice1 = new JLabel("Price:");
                            labelprice1.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel labelprice2 = new JLabel(String.valueOf(market.getPlayers().get(table.getSelectedRow()).getPrice()));
                            labelprice2.setFont(new Font("Arial", Font.PLAIN, 32));

                            panel4.add(labelprice1);
                            panel4.add(labelprice2);

                            JButton closeButton = new JButton("Close");
                            closeButton.setFont(new Font("Arial", Font.PLAIN, 32));
                            panel5.add(closeButton);

                            frame.add(panel1);
                            frame.add(panel2);
                            frame.add(panel3);
                            frame.add(panel4);
                            frame.add(panel5);

                            closeButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    frame.dispose();
                                }
                            });
                            frame.setVisible(true);
                        }
                    }
                });
                JButton purchaseButton = new JButton("Purchase");
                purchaseButton.setActionCommand("purchase");
                frame.add(purchaseButton);

                purchaseButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getActionCommand().equals("purchase")) {
                            JFrame frame = new JFrame();
                            frame.setTitle("Player Manager");
                            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            frame.setSize(500, 400);
                            frame.setLocationRelativeTo(null);
                            frame.setResizable(false);
                            frame.setLayout(new FlowLayout());

                            JPanel panel1 = new JPanel();
                            JPanel panel2 = new JPanel();
                            JPanel panel3 = new JPanel();

                            JLabel label1a = new JLabel("Confirm player:");
                            label1a.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel label1b = new JLabel(market.getPlayers().get(table.getSelectedRow()).getName());
                            label1b.setFont(new Font("Arial", Font.PLAIN, 32));
                            panel1.add(label1a);
                            panel1.add(label1b);

                            JLabel label2a = new JLabel("Your balance:");
                            label2a.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel label2b = new JLabel(String.valueOf(user.getWallet().getBalance()));
                            label2b.setFont(new Font("Arial", Font.PLAIN, 32));
                            panel2.add(label2a);
                            panel2.add(label2b);

                            JLabel label3a = new JLabel("After purchase balance:");
                            label3a.setFont(new Font("Arial", Font.BOLD, 32));
                            JLabel label3b = new JLabel(String.valueOf((user.getWallet().getBalance() - market.getPlayers().get(table.getSelectedRow()).getPrice())));
                            label3b.setFont(new Font("Arial", Font.PLAIN, 32));
                            panel3.add(label3a);
                            panel3.add(label3b);

                            frame.add(panel1);
                            frame.add(panel2);
                            frame.add(panel3);

                            frame.setVisible(true);

                            JButton confirmButton = new JButton("Confirm");
                            confirmButton.setActionCommand("confirm");
                            frame.add(confirmButton);

                            confirmButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    int x = table.getSelectedRow();
                                    model.removeRow(x);
                                    user.addPlayer(market.getPlayers().get(x));
                                    user.getWallet().removeBalance(market.getPlayers().get(x).getPrice());
                                    market.removePlayer(market.getPlayers().get(x));

                                    frame.dispose();
                                }
                            });
                        }
                    }
                });
                frame.setVisible(true);

                JButton backButton = new JButton("Back");
                backButton.setFont(new Font("Arial", Font.PLAIN, 20));
                frame.add(backButton);

                backButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                    }
                });
            }
        }
    }

    public void addPlayerToUser(ArrayList<String> list) {
        if (list.get(1).contains("W") || list.get(1).contains("ST")) {
            Forward forward = new Forward(list.get(0), list.get(1));
            forward.setRatings(Integer.parseInt(list.get(2)));
            forward.setPrice();
            user.getPlayers().add(forward);
        } else if (list.get(1).contains("M")) {
            Midfielder midfielder = new Midfielder(list.get(0), list.get(1));
            midfielder.setRatings(Integer.parseInt(list.get(2)));
            midfielder.setPrice();
            user.getPlayers().add(midfielder);
        } else if (list.get(1).contains("B")) {
            Defender defender = new Defender(list.get(0), list.get(1));
            defender.setRatings(Integer.parseInt(list.get(2)));
            defender.setPrice();
            user.getPlayers().add(defender);
        } else if (list.get(1).contains("GK")) {
            Goalkeeper goalkeeper = new Goalkeeper(list.get(0), list.get(1));
            goalkeeper.setRatings(Integer.parseInt(list.get(2)));
            goalkeeper.setPrice();
            user.getPlayers().add(goalkeeper);
        }
    }

    public void addPlayerToMarket(ArrayList<String> list) {
        if (list.get(1).contains("W") || list.get(1).contains("ST")) {
            Forward forward = new Forward(list.get(0), list.get(1));
            forward.setRatings(Integer.parseInt(list.get(2)));
            forward.setPrice();
            market.getPlayers().add(forward);
        } else if (list.get(1).contains("M")) {
            Midfielder midfielder = new Midfielder(list.get(0), list.get(1));
            midfielder.setRatings(Integer.parseInt(list.get(2)));
            midfielder.setPrice();
            market.getPlayers().add(midfielder);
        } else if (list.get(1).contains("B")) {
            Defender defender = new Defender(list.get(0), list.get(1));
            defender.setRatings(Integer.parseInt(list.get(2)));
            defender.setPrice();
            market.getPlayers().add(defender);
        } else if (list.get(1).contains("GK")) {
            Goalkeeper goalkeeper = new Goalkeeper(list.get(0), list.get(1));
            goalkeeper.setRatings(Integer.parseInt(list.get(2)));
            goalkeeper.setPrice();
            market.getPlayers().add(goalkeeper);
        }
    }

    // Splitting line up by spaces
    public static ArrayList<String> splitOnSpace(String line) {
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }
}
