package model;

import exceptions.IncorrectPosition;

public abstract class Player {
    private String name;
    private String position;
    private int ratings;
    private int price;
    private User user;

    public Player(String name) {
        this.name = name;
        position = "";
        ratings = 0;
        price = 0;
        user = new User("");
    }

    public void setPosition(String position) throws IncorrectPosition {
        this.position = position;
    }

    // EFFECTS: returns the name of a player
    public String getName() {
        return name;
    }

    // EFFECTS: returns the position of a player
    public String getPosition() {
        return position;
    }

    // EFFECTS: returns the ratings of a player
    public int getRatings() {
        return ratings;
    }

    public int getPrice() {
        return price;
    }

    public void setUser(User user) {
        this.user = user;
        user.addPlayer(this);
    }
}
