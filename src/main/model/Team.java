package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Team {
    private Map<String, List<Player>> team;

    public Team() {
        team = new HashMap<String, List<Player>>();
    }
}
