package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Account account;
    private List<Player> team;

    public User() {
        account = new Account();
        team = new ArrayList<>();
    }
}
