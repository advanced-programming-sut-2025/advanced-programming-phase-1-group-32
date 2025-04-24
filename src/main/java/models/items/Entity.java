package models.items;

import models.items.components.EntityComponent;

import java.util.ArrayList;

abstract public class Entity implements Placable {
    private final ArrayList<EntityComponent> components = new ArrayList<>();

    public <T extends EntityComponent> T getComponent(Class<T> componentClass) {
        for (EntityComponent c : components) {
            if (componentClass.isInstance(c)) {
                return (T) c;
            }
        }
        return null;
    }
}
