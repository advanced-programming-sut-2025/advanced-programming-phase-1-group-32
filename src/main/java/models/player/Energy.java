package models.player;

public class Energy {
    private int amount;
    private int modifierDaysLeft;
    private float modifier;
    private boolean isUnlimited;

    public void changeEnergy() {

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
        int amount = this.amount;
        if (modifierDaysLeft > 0) amount = (int) (amount * modifier);
        return amount;
    }

    public void toggleUnlimited() {
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
        //TODO: other effects
    }
}
