package model;

import exceptions.IncorrectPosition;
import exceptions.NotAGoalkeeper;

public class Goalkeeper extends Player {

    public Goalkeeper(String name) {
        super(name);
    }

    @Override
    public void setPosition(String position) throws IncorrectPosition {
        if (position.contains("GK")) {
            throw new NotAGoalkeeper();
        }
        super.setPosition(position);
    }
}
