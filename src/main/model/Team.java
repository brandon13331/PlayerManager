package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Team {
    private Map<User, ArrayList<Player>> team;

    public Team() {
        team = new HashMap<>();
    }

    public void addUser(User user) {
        team.put(user, new ArrayList<>());
    }

    public void addPlayer(User user, Player player) {
        ArrayList<Player> players = team.get(user);
        players.add(player);
    }

    public ArrayList<Player> getPlayers(User user) {
        return team.get(user);
    }
}
