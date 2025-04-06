package models.interfaces;

import models.items.tools.Tool;
import models.player.Player;

public interface Harvestable {
    public boolean canHarvest(Player player, Tool tool);
    public void harvest(Player player, Tool tool);
}
