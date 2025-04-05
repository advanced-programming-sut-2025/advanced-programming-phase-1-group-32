package models.enums;

public enum Tools {
    FISHING_POLE(),
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
