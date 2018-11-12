package model;

public abstract class Player {
    private String name;
    private String position;
    private int ratings;

    public Player(String name, String position) {
        this.name = name;
        this.position = position;
        ratings = 0;
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
}
