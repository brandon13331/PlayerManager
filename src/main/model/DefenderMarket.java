package model;

import exceptions.IncorrectPosition;
import exceptions.NotADefender;

public class DefenderMarket extends TransferMarket {

    @Override
    public void addPlayer(Player player) throws IncorrectPosition {
        if (!player.getPosition().contains("B")) {
            throw new NotADefender();
        }
        super.addPlayer(player);
    }
}
