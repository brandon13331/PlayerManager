package model;

import exceptions.IncorrectPosition;
import exceptions.NotAForward;

public class Forward extends Player {

    public Forward(String name) {
        super(name);
    }

    @Override
    public void setPosition(String position) throws IncorrectPosition {
        if (position.contains("W") || position.contains("ST")) {
            throw new NotAForward();
        }
        super.setPosition(position);
    }
}
