package models.player;

public class Wallet {
    private int amount;
    public int getAmount(){
        return 0;
    }
    private double balance;

    public void changeAmount(){

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
}
