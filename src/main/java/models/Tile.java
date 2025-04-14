package src.main.java.models;

import src.main.java.models.enums.TileType;
import src.main.java.models.interfaces.Placable;

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
