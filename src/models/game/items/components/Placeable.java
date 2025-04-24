package models.items.components;

import models.Position;

public class Placeable extends ItemComponent{
    private Position position;
    public Placeable(){
        super(ItemComponentType.PLACEABLE);
        this.position = new Position();
    }
}
