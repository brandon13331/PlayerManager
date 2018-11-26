package model;

import java.util.ArrayList;

public class User {
    private String id;
    private Wallet wallet;
    private ArrayList<Player> players;

    public User(String id) {
        this.id = id;
        wallet = new Wallet();
        players = new ArrayList<>();
    }

    public String getID() {
        return id;
    }

    public Wallet getWallet() {
        return wallet;
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
}
