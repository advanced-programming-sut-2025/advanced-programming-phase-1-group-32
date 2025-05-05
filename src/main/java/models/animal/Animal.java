package models.animal;

import models.Position;
import models.entities.Entity;
import models.entities.components.Renderable;
import models.interfaces.Updatable;
import models.player.Player;
import models.player.friendship.AnimalFriendship;

import java.util.HashMap;
import java.util.Map;

public abstract class Animal extends Entity implements Updatable {
    private HashMap<Player, AnimalFriendship> friendshipHashMap;
    private Map<AnimalProduct, Integer>       timeLeftTillProduction;
    private final AnimalType type;
    private Player   owner;
    private Position position;

    public Animal(AnimalType type, String name, Player owner) {
        super(name, new Renderable(/*TODO*/));
        this.type  = type;
        this.owner = owner;
    }

    public void pet() {

    }

    public void checkForProducts(Player player){

    }
}
