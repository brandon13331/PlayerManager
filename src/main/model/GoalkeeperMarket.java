package model;

import exceptions.IncorrectPosition;
import exceptions.NotAGoalkeeper;

public class GoalkeeperMarket extends TransferMarket {

    @Override
    public void addPlayer(Player player) throws IncorrectPosition {
        if (!player.getPosition().contains("GK")) {
            throw new NotAGoalkeeper();
        }
        super.addPlayer(player);
    }
}
