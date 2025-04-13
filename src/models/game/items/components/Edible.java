package models.game.items.components;

import models.game.player.Buff;

public class Edible extends ItemComponent{
    private int energy;
    private Buff buff;

    public Edible(int energy, Buff buff) {
        super(ItemComponentType.EDIBLE);
        this.energy = energy;
        this.buff = buff;
    }
}
