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

    public Wallet getWallet() {
        return wallet;
    }
}
