package src.main.java.models.items.workstations;

import src.main.java.models.Position;
import src.main.java.models.interfaces.Placable;
import src.main.java.models.interfaces.Updatable;
import src.main.java.models.items.Item;

public class WorkStation extends Item implements Placable, Updatable {
    private ItemProcess currentProcess;

    private void startProcess(){

    }
    private boolean processDone(){
        return false;
    }
    private Item getProducts(){
        return null;
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
    public void updatePerHour() {

    }

    @Override
    public void updatePerDay() {

    }
}
