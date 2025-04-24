package models.game.items.workstations;

<<<<<<<< HEAD:src/models/game/items/workstations/ItemProcess.java
import models.game.items.Item;
========
import models.items.Entity;
>>>>>>>> main:src/main/java/models/items/workstations/ItemProcess.java

import java.util.ArrayList;

public class ItemProcess{
    private ArrayList<Entity> inputs;
    private ArrayList<Entity> outputs;
    private int timeLeft;
}