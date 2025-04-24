package models.items.components;

public class Sellable extends ItemComponent{
    private final double price;

    public Sellable(double price) {
        super(ItemComponentType.SELLABLE);
        this.price = price;
    }
}
