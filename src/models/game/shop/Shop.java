package models.game.shop;

import models.game.Buildings.Building;
import models.game.GameMap;
import models.game.Position;
import models.game.items.Item;

import java.util.HashMap;

public class Shop extends Building{
    private int startHour;
    private int endHour;
    private Character seller;
    private final HashMap<Item, Integer> itemIntegerHashMap;
    private HashMap<Item, Integer> itemsSoldToday;

    public Shop(Position position, int width, int height, GameMap map) {
        super(position, width, height, map);
    }
}
