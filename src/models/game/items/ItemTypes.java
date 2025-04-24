package models.items;

import models.items.components.*;

import java.util.Map;

public enum ItemTypes {
    /// Tools
    FISHING_POLE("Fishing pole", Map.of(ItemComponentType.TOOL, new Tool(ToolType.FISHING_POLE), ItemComponentType.LEVEL, new Level())),
    HOE         (),
    PICKAXE     (),
    AXE         (),
    WATERING_CAN(),
    SCYTHE      (),
    MILK_PAIL   (),
    SHEAR       (),
    BACKPACK    ("Backpack", Map.of(ItemComponentType.INVENTORY, new Inventory(12))),
    /// Food
    FRIED_EGG   ("Fried egg", Map.of(ItemComponentType.EDIBLE, new Edible(10, null))),
    BAKED_FISH(),
    SALAD(),
    OLMELET(),
    PUMPKIN_PIE(),
    SPAGHETTI(),
    PIZZA(),
    TORTILLA(),
    MAKI_ROLL(),
    TRIPLE_SHOT_ESPRESSO(),
    COOKIE(),
    HASH_BROWNS(),
    PANCAKES(),
    FRUIT_SALAD(),
    RED_PLATE(),
    BREAD(),
    SALMON_DINNER(),
    VEGETABLE_MEDLEY(),
    FARMERS_LUNCH(),
    SURVIVAL_BURGER(),
    DISH_O_THE_SEA(),
    SEAFORM_PUDDING(),
    MINER_TREAT(),
    /// Useables
    CHERRY_BOMB(),
    BOMB(),
    MEGA_BOMB(),
    SPRINKLER(),
    QUALITY_SPRINKLER(),
    IRIDIUM_SPRINKLER(),
    CHARCOAL_KLIN("Charcoal klin", Map.of(ItemComponentType.PLACEABLE, new Placeable())),
    FURNACE(),
    SCARECROW(),
    DELUXE_SCARECROW(),
    BEE_HOUSE(),
    CHEESE_PRESS(),
    KEG(),
    LOOM(),
    MAYONNAISE_MACHINE(),
    OIL_MAKER(),
    PRESERVES_JAR(),
    DEHYDRATOR(),
    FISH_SMOKER(),
    MYSTIC_TREE_SEED();
    private final Item item;

    ItemTypes(String name, Map<ItemComponentType, ItemComponent> components) {
        this.item = new Item(name, components);
    }
}
