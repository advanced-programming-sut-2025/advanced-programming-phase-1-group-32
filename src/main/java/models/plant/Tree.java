package main.java.models.plant;

import main.java.models.interfaces.Harvestable;
import main.java.models.items.Tool;
import main.java.models.player.Player;

public class Tree extends Plant implements Harvestable {
    @Override
    public boolean canHarvest(Player player, Tool tool) {
        return false;
    }

    @Override
    public void harvest(Player player, Tool tool) {

    }
}
