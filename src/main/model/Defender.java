package model;

import exceptions.IncorrectPosition;
import exceptions.NotADefender;

public class Defender extends Player {

    public Defender(String name) {
        super(name);
    }

    @Override
    public void setPosition(String position) throws IncorrectPosition {
        if (position.contains("B")) {
            throw new NotADefender();
        }
        super.setPosition(position);
    }
}
