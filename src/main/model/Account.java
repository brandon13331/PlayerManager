package model;

public class Account {
    private int balance;

    public Account() {
        this.balance = 0;
    }

    public void addBalance(int x) {
        if (x > 0) {
            balance = balance + x;
        }
    }

    public void removeBalance(int x) {
        if (x > 0) {
            balance = balance - x;
        }
    }

    public int getBalance() {
        return balance;
    }
}
