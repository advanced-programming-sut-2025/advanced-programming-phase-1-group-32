package models.player;

import java.io.Serializable;

public class Wallet implements Serializable {
    private int amount;
    private double balance;

    public void changeAmount(){

    }

    public int getAmount(){
        return 0;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void changeBalance(double price){
        this.balance += price;
    }

    public static Wallet combineWallets(Wallet wallet1, Wallet wallet2) {
        Wallet wallet = new Wallet();
        wallet.setAmount(wallet1.getAmount() + wallet2.getAmount());
        wallet.setBalance(wallet1.getBalance() + wallet2.getBalance());
        return wallet;

    }
}
