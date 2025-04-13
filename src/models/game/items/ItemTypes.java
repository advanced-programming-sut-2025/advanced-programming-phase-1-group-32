package models.game.items;

import models.game.items.components.*;

import java.util.Map;

public enum ItemTypes {
    //Tools
    FISHING_POLE("Fishing pole", Map.of(ItemComponentType.TOOL, new Tool(ToolType.FISHING_POLE), ItemComponentType.LEVEL, new Level())),
    HOE         (),
    PICKAXE     (),
    AXE         (),
    WATERING_CAN(),
    SCYTHE      (),
    MILK_PAIL   (),
    SHEAR       (),
    BACKPACK    ("Backpack", Map.of(ItemComponentType.INVENTORY, new Inventory(12)));
    private final Item item;

    ItemTypes(String name, Map<ItemComponentType, ItemComponent> components) {
        this.item = new Item(name, components);
    }
}
