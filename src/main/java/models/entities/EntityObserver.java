package models.entities;

import models.Game;
import models.gameMap.GameMap;

public interface EntityObserver {
    void onDelete(Entity entity);
    default void onTransferToInventory(Entity entity){}
}
