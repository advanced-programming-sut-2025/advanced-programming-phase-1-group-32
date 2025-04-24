package models.game.Quest;

<<<<<<<< HEAD:src/models/game/Quest/ItemQuest.java
import models.game.items.Item;
========
import models.items.Entity;
>>>>>>>> main:src/main/java/models/Quest/ItemQuest.java

import java.util.ArrayList;

public class ItemQuest extends Quest {
    private final ArrayList<Entity> itemsToGive = null;
    @Override
    void onComplete() {

    }

    @Override
    void isComplete() {

    }
}
