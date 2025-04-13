package models.game.interfaces;

import models.game.items.Item;
import models.game.player.Player;

public interface Harvestable {
    public boolean canHarvest(Player player, Item tool);
    public void harvest(Player player, Item tool);
}
