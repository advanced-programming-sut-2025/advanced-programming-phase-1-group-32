package models.interfaces;

import models.items.Item;
import models.player.Player;

public interface Harvestable {
    public boolean canHarvest(Player player, Item tool);
    public void harvest(Player player, Item tool);
}
