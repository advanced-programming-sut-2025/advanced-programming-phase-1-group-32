package models;

import models.entities.Entity;
import models.enums.TileType;
import views.inGame.Color;

public class Tile{
    private TileType type;
    final private Position position;
    private Entity content;

    public Tile(Position position, TileType type) {
        this.position = position;
        this.type = type;
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

    public int getRow() {
        return position.getRow();
    }

    public int getCol() {
        return position.getCol();
    }

    public Color getColor() {
        return this.type.color;
    }
    public char getCharacter(){
        return this.type.character;
    }
}
