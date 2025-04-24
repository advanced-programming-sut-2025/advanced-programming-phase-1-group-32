package models.Buildings;

<<<<<<<< HEAD:src/models/game/Buildings/Building.java
import models.GameMap;
import models.Map;
import models.Position;
========
import modelsMap;
import models.Position;
>>>>>>>> main:src/main/java/models/Buildings/Building.java

abstract public class Building {
    protected final Position position;
    protected final int width, height;
    protected final GameMap map;

    public Building(Position position, int width, int height, GameMap map) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.map = map;
    }


}
