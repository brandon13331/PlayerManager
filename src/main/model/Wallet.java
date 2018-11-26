package model;

public class Wallet {
    private int balance;

    public Wallet() {
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
