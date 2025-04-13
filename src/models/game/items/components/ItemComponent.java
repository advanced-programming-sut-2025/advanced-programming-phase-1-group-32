package models.game.items.components;

public abstract class ItemComponent {
    protected final ItemComponentType type;

    public ItemComponent(ItemComponentType type) {
        this.type = type;
    }

    public boolean isOfType(ItemComponentType type){
        return this.type == type;
    }
}
