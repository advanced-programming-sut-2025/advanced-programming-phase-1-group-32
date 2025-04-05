package models;

import models.enums.Material;

public class Tool {
    String name;
    Material material;

    public Tool(String name) {
        this.name = name;
        material = Material.INITIAL;
    }

    public void useTool() {
        
    }
}
