package models.game.interfaces;

import models.game.items.Tool;
import models.game.player.Player;

public interface Harvestable {
    public boolean canHarvest(Player player, Tool tool);
    public void harvest(Player player, Tool tool);
}
