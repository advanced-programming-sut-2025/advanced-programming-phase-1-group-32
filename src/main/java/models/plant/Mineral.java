package src.main.java.models.plant;

import src.main.java.models.Position;
import src.main.java.models.interfaces.Harvestable;
import src.main.java.models.interfaces.Placable;
import src.main.java.models.items.Item;
import src.main.java.models.items.Tool;
import src.main.java.models.player.Player;

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
