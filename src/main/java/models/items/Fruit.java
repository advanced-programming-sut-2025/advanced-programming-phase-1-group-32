package models.items;

import models.Position;
import models.interfaces.Sellable;

public class Fruit extends Item implements Sellable {
    public boolean isEdible;

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
