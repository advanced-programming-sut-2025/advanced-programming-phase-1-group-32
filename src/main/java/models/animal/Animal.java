package models.game.animal;

<<<<<<<< HEAD:src/models/game/animal/Animal.java
import models.game.Position;
import models.game.interfaces.Sellable;
import models.game.interfaces.Updatable;
import models.game.player.Player;
import models.game.player.friendship.AnimalFriendship;
========
import models.Position;
import models.interfaces.Updatable;
import models.player.Player;
import models.player.friendship.AnimalFriendship;
>>>>>>>> main:src/main/java/models/animal/Animal.java

import java.util.HashMap;
import java.util.Map;

public abstract class Animal implements Sellable, Updatable {
    private HashMap<Player, AnimalFriendship> friendshipHashMap;
    private Map<AnimalProduct, Integer>       timeLeftTillProduction;
    private final AnimalType type;
    private Player   owner;
    private String   name;
    private Position position = new Position();

    public Animal(AnimalType type, String name, Player owner) {
        this.type  = type;
        this.name  = name;
        this.owner = owner;
    }

    public void pet() {

    }

    public void checkForProducts(Player player){

    }
}
