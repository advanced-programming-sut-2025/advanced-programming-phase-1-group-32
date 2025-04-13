package models.game.items.components;

import models.game.Position;

public class Placeable extends ItemComponent{
    private Position position;
    public Placeable(){
        super(ItemComponentType.PLACEABLE);
        this.position = new Position();
    }
}
