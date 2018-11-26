package model;

import java.util.ArrayList;

public class User {
    private Wallet wallet;
    private ArrayList<Player> players;

    public User() {
        players = new ArrayList<>();
        wallet = new Wallet();
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
            player.setUser(this);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public ArrayList<Player> getForwards() {
        ArrayList<Player> forwards = new ArrayList<>();
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).getName().contains("W") || players.get(i).getName().contains("W")) {
                forwards.add(players.get(i));
            }
        }
        return forwards;
    }

    public Wallet getWallet() {
        return wallet;
    }
}
