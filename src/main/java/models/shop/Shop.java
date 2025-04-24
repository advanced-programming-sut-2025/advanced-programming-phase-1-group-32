package models.shop;

import models.Buildings.Building;
import models.GameMap;
import models.Position;
import models.entities.Entity;

import java.util.HashMap;

public class Shop extends Building{
    private int startHour;
    private int endHour;
    private Character seller;
    private final HashMap<Entity, Integer> stock = null;
    private HashMap<Entity, Integer> itemsSoldToday;

    public Shop(Position position, int width, int height, GameMap map) {
        super(position, width, height, map);
    }

}
