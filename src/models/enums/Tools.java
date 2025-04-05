package models.enums;

<<<<<<< HEAD
import models.FishingPole;
import models.Tool;

public enum Tools {
    FISHING_POLE(new FishingPole()),
=======
public enum Tools {
    FISHING_POLE(),
>>>>>>> main
    HOE(),
    PICKAXE(),
    AXE(),
    WATERING_CAN(),
    SCYTHE(),
    MILK_PAIL(),
    SHEAR(),
    BACKPACK(),

    

    ;

    private final Tool tool;

    Tools(Tool tool) {
        this.tool = tool;
    }

    public void use() {
        this.tool.useTool();
    }
}
