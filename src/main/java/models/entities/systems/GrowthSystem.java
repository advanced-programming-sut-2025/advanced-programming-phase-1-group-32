package models.entities.systems;

import models.Game;
import models.entities.Entity;
import models.entities.EntityList;
import models.entities.components.Growable;
import models.gameMap.Tile;
import records.Result;

import java.util.ArrayList;

public class GrowthSystem {
    public static Result plantOnTile(Entity seed, Tile tile){
        return null;
    }
    public static void updatePerDay(Game game){
        ArrayList<Growable> growables = game.getMainMap().getComponentsOfType(Growable.class);

        for (Growable growable : growables) {
            growable.updatePerDay();
        }

        ArrayList<Entity> toDelete = new ArrayList<>();
        for (Growable growable : growables) {
            if (growable.getDaysPastFromWatered() >= 3) {
                toDelete.add(growable.getEntity());
            }
        }

        int size = toDelete.size();
        for (int i = 0; i < size; i++) {
            Entity entity = toDelete.get(i);
            entity.delete();
        }
    }
}
