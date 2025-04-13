package models.game.items.workstations;

import models.game.interfaces.Placable;
import models.game.interfaces.Updatable;
import models.game.items.Item;

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
