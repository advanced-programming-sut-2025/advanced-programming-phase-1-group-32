package models.game.items.components;

import models.game.enums.Material;

public class Level extends ItemComponent{
    private final Material material;

    public Level() {
        super(ItemComponentType.LEVEL);
        this.material = Material.STONE;
    }
}
