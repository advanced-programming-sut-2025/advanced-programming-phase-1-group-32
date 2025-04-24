package models.items.components;

import models.enums.Material;
import models.items.Entity;

public class Harvestable extends EntityComponent {
    public Entity ressource;
    public Material material;

    public boolean canHarvest(){
        return false;
    }
    public void harvest(){

    }
}
