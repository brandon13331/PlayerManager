package model;

public class User {
    private Account account;
    private Team team;

    public User() {
        account = new Account();
        team = new Team();
    }
}
