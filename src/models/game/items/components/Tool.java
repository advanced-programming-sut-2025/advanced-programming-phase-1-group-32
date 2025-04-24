package models.items.components;

import models.enums.Material;
import models.items.ToolType;

public class Tool extends ItemComponent{
    private ToolType type;

    public Tool(ToolType type){
        super(ItemComponentType.TOOL);
        this.type = type;
    }
}
