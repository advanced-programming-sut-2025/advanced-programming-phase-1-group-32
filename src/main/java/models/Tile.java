package models;

import models.entities.Entity;
import models.enums.TileType;

public class Tile{
    private TileType type;
    final private Position position;
    private Entity content;

    public Tile(Position position) {
        this.position = position;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public Entity getContent() {
        return content;
    }

    public void setContent(Entity content) {
        this.content = content;
    }
}
