package models.items;

import models.Result;
import models.player.Player;

public enum UseFunction {
    AXE_FUNCTION(){

    },
    WATERING_CAN_FUNCTION(){

    },
    ;
    public Result canUse(Entity usedEntity, Player player, Entity destination){
        return null;
    }
    public Result use(Entity usedEntity, Player player, Entity destination){
        return null;
    }
}
