package models.items.components;

import models.Result;
import models.items.Entity;
import models.items.UseFunction;
import models.player.Player;

import java.util.ArrayList;

public class Useable extends EntityComponent {
    private ArrayList<UseFunction> functions;

    private Result use(Entity usedEntity, Player player, Entity destination){
        for(UseFunction f : functions){
            if(f.canUse(usedEntity, player, destination).isSuccessful()){
                f.use(usedEntity, player, destination);
            }
        }
        return null;
    }
}
