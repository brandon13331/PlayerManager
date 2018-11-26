package model;

import exceptions.IncorrectPosition;
import exceptions.NotAMidfielder;

public class Midfielder extends Player {

    public Midfielder(String name) {
        super(name);
    }

    @Override
    public void setPosition(String position) throws IncorrectPosition {
        if (position.contains("M")) {
            throw new NotAMidfielder();
        }
        super.setPosition(position);
    }
}
