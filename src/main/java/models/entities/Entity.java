package models.entities;

import models.entities.components.EntityComponent;

import java.util.ArrayList;
import java.util.Arrays;

public class Entity {
    private final String name;
    private ArrayList<EntityComponent> components = new ArrayList<>();

    public Entity(String name, EntityComponent... components){
        this.name = name;
        this.components.addAll(Arrays.asList(components));
    }

    public <T extends EntityComponent> T getComponent(Class<T> componentClass) {
        for (EntityComponent c : components) {
            if (componentClass.isInstance(c)) {
                return (T) c;
            }
        }
        return null;
    }
}
