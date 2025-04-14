package src.main.java.models.interfaces;

import src.main.java.models.items.Tool;
import src.main.java.models.player.Player;

public interface Harvestable {
    public boolean canHarvest(Player player, Tool tool);
    public void harvest(Player player, Tool tool);
}
