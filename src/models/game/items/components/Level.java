package models.items.components;

import models.enums.Material;

public class Level extends ItemComponent{
    private final Material material;

    public Level() {
        super(ItemComponentType.LEVEL);
        this.material = Material.STONE;
    }
}
