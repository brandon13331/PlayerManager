package model;

import java.util.ArrayList;
import java.util.Objects;

public class User {
    private String id;
    private Account account;
    private ArrayList<Player> players;

    public User(String id) {
        this.id = id;
        account = new Account();
        players = new ArrayList<>();
    }

    public String getID() {
        return id;
    }

    public Account getAccount() {
        return account;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
