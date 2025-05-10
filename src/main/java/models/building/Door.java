package models.building;

import models.App;
import models.entities.CollisionEvent;
import models.entities.Entity;
import models.entities.components.Placeable;
import models.entities.components.Renderable;
import models.gameMap.GameMap;
import models.player.Player;
import records.Result;
import views.inGame.Color;

public class Door extends Entity {
    private final GameMap destination;
    public Door(GameMap destination) {
        super("DOOR");
        this.destination = destination;


        this.addComponent( new Placeable(true, new CollisionEvent() {
            @Override
            public Result onEnter() {
                App.getActiveGame().setActiveMap(Door.this.destination);
                System.out.println("asdasdasd");
                return new Result(false, "");
            }
        }));

        this.addComponent(new Renderable('D', new Color(78, 52, 46)));
    }
}