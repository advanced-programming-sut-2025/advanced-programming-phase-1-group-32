package models.plant;

import models.interfaces.Harvestable;
import models.items.Tool;
import models.player.Player;

public class Tree extends Plant implements Harvestable {
    @Override
    public boolean canHarvest(Player player, Tool tool) {
        return false;
    }

    @Override
    public void harvest(Player player, Tool tool) {

    }
}
