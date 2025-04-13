package models.game.items.components;

import models.game.player.Buff;

public class Edible extends ItemComponent{
    private int energy;
    private Buff buff;

    public Edible(ItemComponentType type, int energy, Buff buff) {
        super(type);
        this.energy = energy;
        this.buff = buff;
    }
}
