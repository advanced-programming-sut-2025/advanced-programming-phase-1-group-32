package models.game.items.components;

import models.game.enums.Material;
import models.game.items.ToolType;

public class Tool extends ItemComponent{
    private ToolType type;

    public Tool(ToolType type){
        this.type = type;
    }
}
