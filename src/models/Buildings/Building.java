package models.Buildings;

import models.Map;
import models.Position;

abstract public class Building {
    protected final Position position;
    protected final int width, height;
    protected final Map map;

    public Building(Position position, int width, int height, Map map) {
        this.position = position;
        this.width = width;
        this.height = height;
        this.map = map;
    }


}
