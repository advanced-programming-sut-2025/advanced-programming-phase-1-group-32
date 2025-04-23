package models.plant;

import models.interfaces.Harvestable;
import models.interfaces.Placable;
import models.items.Tool;
import models.player.Player;

public class Tree extends Plant implements Harvestable, Placable {
    @Override
    public boolean canHarvest(Player player, Tool tool) {
        return false;
    }

    @Override
    public void harvest(Player player, Tool tool) {

    }
}
