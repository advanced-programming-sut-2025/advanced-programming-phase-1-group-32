package models.player;

import java.io.Serializable;

public class Energy implements Serializable {
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

    public void addEnergy(int amount) {
        this.amount += amount;
        if (this.amount > 0) {
            this.amount = 200;
        }
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

    public void setModifier(float modifier, int daysLeft) {
        this.modifier = modifier;
        this.modifierDaysLeft = daysLeft;
    }

    public void updatePerDay() {
        if (modifierDaysLeft > 0) {
            modifierDaysLeft--;
        }
    }
}
