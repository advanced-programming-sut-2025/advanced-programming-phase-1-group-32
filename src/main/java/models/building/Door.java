package models.building;

import models.App;
import models.Position;
import models.entities.CollisionEvent;
import models.entities.Entity;
import models.entities.components.Placeable;
import models.entities.components.PositionComponent;
import models.entities.components.Renderable;
import models.gameMap.GameMap;
import models.player.Player;
import records.Result;
import views.inGame.Color;

public class Door extends Entity {
    private Position destination;
    public Door() {
        super("DOOR");
        this.addComponent(new Renderable('D', new Color(78, 52, 46)));
    }
    public void setDestination(Position position){
        this.destination = position;

        this.addComponent( new Placeable(true, new CollisionEvent() {
            @Override
            public Result onEnter() {
                App.getActiveGame().setActiveMap(Door.this.destination.getMap());
                App.getActiveGame().getCurrentPlayer().setPosition(Door.this.destination);
                return new Result(false, "");
            }
        }));
    }
}