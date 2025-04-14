package src.main.java.models.shop;

import src.main.java.models.Buildings.Building;
import src.main.java.models.GameMap;
import src.main.java.models.Position;
import src.main.java.models.items.Item;

import java.util.HashMap;

public class Shop extends Building{
    private int startHour;
    private int endHour;
    private Character seller;
    private final HashMap<Item, Integer> stock = null;
    private HashMap<Item, Integer> itemsSoldToday;

    public Shop(Position position, int width, int height, GameMap map) {
        super(position, width, height, map);
    }

}
