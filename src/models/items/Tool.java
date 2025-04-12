package models.items;

import models.enums.Material;

public class Tool extends Item {
    protected Material material ;
    private ToolType type;

    public Tool(ToolType type){
        this.type = type;
    }
    public void use(){
        this.type.use();
    }
}
