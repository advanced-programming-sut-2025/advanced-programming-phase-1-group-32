package models;

import models.entities.Entity;
import models.entities.EntityObserver;
import models.entities.components.SeedComponent;
import models.enums.TileType;
import views.inGame.Color;

public class Tile implements EntityObserver {
    private TileType type;
    final private Position position;
    private Entity content;

    public Tile(Position position, TileType type) {
        this.position = position;
        this.type = type;
        if(type == null){
            throw new RuntimeException("Tile type can't be null");
        }
    }

    public void plant(Entity seed) {
        Entity plant = App.entityRegistry.makeEntity(seed.getComponent(SeedComponent.class).);
        this.setContent(plant);
        this.type = TileType.PLANTED_GROUND;
        Game game = App.getActiveGame();
        game.getPlantedEntities().add(content);
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
        if(this.content != null){
            this.content.removeObserveer(this);
        }
        this.content = content;
        if(this.content != null){
            this.content.addObserver(this);
        }
    }

    public Position getPosition() {
        return position;
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
        try {
            return this.type.character;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDelete(Entity entity) {
        this.content = null;
    }
}
