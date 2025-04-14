package src.main.java.models.items;

import src.main.java.models.Position;
import src.main.java.models.enums.Material;

public class Tool extends Item {
    protected Material material ;
    private ToolType type;

    public Tool(ToolType type){
        this.type = type;
    }
    public void use(){
        this.type.use();
    }

    @Override
    public void useItem() {

    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition() {

    }

    @Override
    public void placeOnGround() {

    }
}
