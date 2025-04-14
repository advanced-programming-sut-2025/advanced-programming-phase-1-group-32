package src.main.java.models.animal;

import src.main.java.models.Position;
import src.main.java.models.interfaces.Sellable;
import src.main.java.models.interfaces.Updatable;
import src.main.java.models.player.Player;
import src.main.java.models.player.friendship.AnimalFriendship;

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
