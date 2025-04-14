package src.main.java.models.plant;

import src.main.java.models.interfaces.Harvestable;
import src.main.java.models.items.Tool;
import src.main.java.models.player.Player;

public class Tree extends Plant implements Harvestable {
    @Override
    public boolean canHarvest(Player player, Tool tool) {
        return false;
    }

    @Override
    public void harvest(Player player, Tool tool) {

    }
}
