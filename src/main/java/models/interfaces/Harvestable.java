package main.java.models.interfaces;

import main.java.models.items.Tool;
import main.java.models.player.Player;

public interface Harvestable {
    public boolean canHarvest(Player player, Tool tool);
    public void harvest(Player player, Tool tool);
}
