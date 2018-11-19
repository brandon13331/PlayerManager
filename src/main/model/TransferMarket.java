package model;

import java.util.ArrayList;
import java.util.List;

public abstract class TransferMarket {
    private List<Player> players;

    public TransferMarket() {
        players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        if (!players.contains(player)) {
            players.add(player);
        }
    }
}
