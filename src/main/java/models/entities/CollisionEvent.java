package models.entities;

import models.player.Player;
import records.Result;

public abstract class CollisionEvent {
    public Result onCollision(Player player, Entity entity){
        return new Result(false, "");
    }
    public Result onEnter(){
        return new Result(false, "");
    };
    public Result onExit(){
        return new Result(false, "");
    };
}
