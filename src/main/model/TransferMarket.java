package model;

import exceptions.IncorrectPosition;
import exceptions.InsufficientBalance;

import java.util.ArrayList;
import java.util.List;

public abstract class TransferMarket {
    private List<Player> players;
    private User user;

    public TransferMarket() {
        players = new ArrayList<>();
        user = new User("");
    }

    public void addPlayer(Player player) throws IncorrectPosition {
        if (!players.contains(player)) {
            players.add(player);
        }
    }

    public void removePlayer(Player player) {
        if (players.contains(player)) {
            players.remove(player);
        }
    }

    public void buyPlayer(Player player, User user) throws InsufficientBalance {
        if (user.getWallet().getBalance() < player.getPrice()) {
            throw new InsufficientBalance();
        }
        user.getPlayers().add(player);
        removePlayer(player);
    }

    public void sellPlayer(Player player, User user) {
        if (user.getPlayers().contains(player)) {
            user.getPlayers().remove(player);
            try {
                addPlayer(player);
            } catch (IncorrectPosition e) {
                System.out.println();
            }
            user.getWallet().addBalance(player.getPrice());
        }
    }
}
