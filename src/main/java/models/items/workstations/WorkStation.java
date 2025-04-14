package main.java.models.items.workstations;

import main.java.models.interfaces.Placable;
import main.java.models.interfaces.Updatable;
import main.java.models.items.Item;

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
}
