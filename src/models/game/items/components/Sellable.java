package models.game.items.components;

public class Sellable extends ItemComponent{
    private final double price;

    public Sellable(ItemComponentType type, double price) {
        super(type);
        this.price = price;
    }
}
