package models.player;

public class Energy {
    private int amount;
    private int modifierDaysLeft;
    private float modifier;
    private boolean isUnlimited;

    public int getEnergy(){
        return 0;
    }
    public void changeEnergy(){

    }

    public Energy() {
        this.amount = 200;
        this.modifierDaysLeft = 0;
        isUnlimited = false;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public int getAmount() {
        // TODO: check modifier effects
        return amount;
    }

    public void updateEnergy(){
        // TODO: for daily updates
    }

    public void toggleUnlimited(){
        isUnlimited = !isUnlimited;
    }
}
