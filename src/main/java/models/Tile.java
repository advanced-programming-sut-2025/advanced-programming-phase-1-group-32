package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import models.entities.Entity;
import models.entities.EntityObserver;
import models.entities.components.SeedComponent;
import models.enums.TileType;
import models.gameMap.MapRegion;
import models.player.Player;
import views.inGame.Color;

import java.io.Serializable;

public class Tile implements EntityObserver, Serializable {
    private TileType type;
    final private Position position;
    private Entity content;
    @JsonBackReference
    private final MapRegion region;
    private Tile(){this(null, null, null);}

    public Tile() {
        position = null;
        region = null;
    }

    public Tile(Position position, TileType type, MapRegion region) {
        this.position = position;
        this.type = type;
        this.region = region;
        if(region != null){
            region.addTile(this);
        }

        if(type == null){
            throw new RuntimeException("Tile type can't be null");
        }
    }

    public void plant(Entity seed) {
        Entity plant = seed.getComponent(SeedComponent.class).getGrowingPlant();
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
    public MapRegion getRegion() {
        return region;
    }

    public Player getOwner(){
        return this.region.getOwner();
    }

    @Override
    public void onDelete(Entity entity) {
        this.content = null;
    }
}
