package models;

import models.enums.TileType;
import models.interfaces.Placable;
import views.inGame.Color;

public class Tile{
    private TileType type;
    final private Position position;
    private Color color;
    private Placable content;

    public Tile(Position position) {
        this.position = position;
        color = new Color(255, 0, 0);
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

    public int getRow() {
        return position.getRow();
    }

    public int getCol() {
        return position.getCol();
    }

    public Color getColor() {
        return color;
    }
}
