package models.items;

import models.Position;
import models.interfaces.Consumable;
import models.interfaces.Sellable;

public class Food extends Item implements Consumable, Sellable {
    @Override
    public int getEnergy() {
        return 0;
    }

    @Override
    public void useItem() {

    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition() {

    }

    @Override
    public void placeOnGround() {

    }

    @Override
    public double getPrice() {
        return 0;
    }
}
