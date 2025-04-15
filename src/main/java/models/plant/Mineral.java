package models.plant;

import models.Position;
import models.interfaces.Harvestable;
import models.interfaces.Placable;
import models.items.Item;
import models.items.Tool;
import models.player.Player;

public class Mineral implements Harvestable, Placable {
    private Item resource;

    @Override
    public boolean canHarvest(Player player, Tool tool) {
        return false;
    }

    @Override
    public void harvest(Player player, Tool tool) {

    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition() {

    }

    @Override
    public void placeOnGround() {

    }
}
