package model;

import exceptions.IncorrectPosition;
import exceptions.NotAMidfielder;

public class MidfielderMarket extends TransferMarket {

    @Override
    public void addPlayer(Player player) throws IncorrectPosition {
        if (!player.getPosition().contains("M")) {
            throw new NotAMidfielder();
        }
        super.addPlayer(player);
    }
}
