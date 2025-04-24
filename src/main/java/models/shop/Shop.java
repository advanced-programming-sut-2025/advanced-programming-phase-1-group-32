package models.game.shop;

<<<<<<<< HEAD:src/models/game/shop/Shop.java
import models.game.Buildings.Building;
import models.game.GameMap;
import models.game.Position;
import models.game.items.Item;
========
import models.Buildings.Building;
import models.GameMap;
import models.Position;
import models.items.Entity;
>>>>>>>> main:src/main/java/models/shop/Shop.java

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
