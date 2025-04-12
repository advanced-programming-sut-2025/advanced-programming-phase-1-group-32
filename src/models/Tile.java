package models;

import models.enums.TileType;
import models.interfaces.Placable;

public class Tile{
    private TileType type;
    final private Position position;
    private Placable content;

    public Tile(Position position) {
        this.position = position;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public Placable getContent() {
        return content;
    }

    public void setContent(Placable content) {
        this.content = content;
    }
}
