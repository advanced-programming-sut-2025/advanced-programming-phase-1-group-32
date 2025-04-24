package models.game;

<<<<<<<< HEAD:src/models/game/Tile.java
import models.game.enums.TileType;
import models.game.interfaces.Placable;
========
import models.enums.TileType;
import models.items.Entity;
>>>>>>>> main:src/main/java/models/Tile.java

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

    public Placable getContent() {
        return content;
    }

    public void setContent(Placable content) {
        this.content = content;
    }
}
