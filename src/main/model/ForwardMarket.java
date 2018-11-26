package model;

import exceptions.IncorrectPosition;
import exceptions.NotAForward;

public class ForwardMarket extends TransferMarket {

    @Override
    public void addPlayer(Player player) throws IncorrectPosition {
        if (!player.getPosition().contains("W") || !player.getPosition().contains("ST")) {
            throw new NotAForward();
        }
        super.addPlayer(player);
    }
}
