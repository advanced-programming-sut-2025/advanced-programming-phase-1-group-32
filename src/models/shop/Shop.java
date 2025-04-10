package models.shop;

import models.Buildings.Building;
import models.Map;
import models.Position;
import models.items.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Shop extends Building{
    private int startHour;
    private int endHour;
    private Character seller;
    private final HashMap<Item, Integer> itemIntegerHashMap;
    private HashMap<Item, Integer> itemsSoldToday;

    public Shop(Position position, int width, int height, Map map) {
        super(position, width, height, map);
    }
}
