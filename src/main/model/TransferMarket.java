package model;

import java.util.ArrayList;
import java.util.List;

public abstract class TransferMarket {
    private List<Player> players;
    private Team team;

    public TransferMarket() {
        players = new ArrayList<>();
        team = new Team();
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
    }
}
