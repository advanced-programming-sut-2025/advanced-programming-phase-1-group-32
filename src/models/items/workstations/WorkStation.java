package models.items.workstations;

import models.interfaces.Placable;
import models.interfaces.Updatable;
import models.items.Item;

import java.util.ArrayList;

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
