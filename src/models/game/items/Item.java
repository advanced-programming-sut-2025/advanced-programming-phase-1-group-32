package models.game.items;

import models.game.items.components.ItemComponent;
import models.game.items.components.ItemComponentType;

import java.util.Map;

public class Item {
    private final String name;
    private final Map<ItemComponentType, ItemComponent> components;

    public Item(String name, Map<ItemComponentType, ItemComponent> components) {
        this.name = name;
        this.components = components;
    }

    public ItemComponent hasComponent(ItemComponentType componentType){
        return this.components.get(componentType);
    }
}
