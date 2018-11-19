package model;

public abstract class Player {
    private String name;
    private String position;
    private int ratings;
    private double price;

    public Player(String name, String position) {
        this.name = name;
        this.position = position;
        ratings = 0;
        price = 0.0;
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

    public double getPrice() {
        return price;
    }
}
