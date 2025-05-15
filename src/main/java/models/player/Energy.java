package models.player;

import java.util.ArrayList;
import java.util.HashMap;

public class Energy {
    private double amount;
    private double maxEnergy;
    private int maxEnergyChangeHoursLeft;
    private int modifierDaysLeft;
    private double modifier;
    private boolean isUnlimited;

    public void changeEnergy() {

    }

    public Energy() {
        this.amount = 200;
        this.modifierDaysLeft = 0;
        isUnlimited = false;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void addEnergy(int amount) {
        this.amount += amount;
        if (this.amount > maxEnergy) {
            this.amount = maxEnergy;
        }
    }

    public boolean isUnlimited() {
        return isUnlimited;
    }

    public double getAmount() {
        double amount = this.amount;
        if (isUnlimited) return Double.POSITIVE_INFINITY;
        return amount;
    }

    public void toggleUnlimited() {
        isUnlimited = !isUnlimited;
    }

    public void setModifier(double modifier, int daysLeft) {
        if (modifierDaysLeft >= daysLeft && this.modifier > modifier) {
            return;
        }
        this.modifier = modifier;
        this.modifierDaysLeft = daysLeft;
    }


    public void reduceEnergy(double energy) {
        if (isUnlimited) return;
        this.amount -= energy;
        if (this.amount <= 0) {
            this.amount = 0;
        }
    }

    public void buff(double buff, int hoursLeft) {
        maxEnergy = 200 + buff;
        maxEnergyChangeHoursLeft = hoursLeft;
    }

    public void updatePerHour() {
        if (maxEnergyChangeHoursLeft > 0) {
            maxEnergyChangeHoursLeft -= 1;
        }

        if (maxEnergyChangeHoursLeft == 0) {
            maxEnergy = 200;
        }
    }

    public void updatePerDay() {
        if (modifierDaysLeft > 0) {
            modifierDaysLeft--;
        }
        amount = maxEnergy;
        if (modifierDaysLeft > 0) {
            amount *= modifier;
        }
        //TODO: other effects
    }


}
