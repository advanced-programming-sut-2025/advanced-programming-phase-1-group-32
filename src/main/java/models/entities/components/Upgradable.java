package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.enums.Material;

public class Upgradable extends EntityComponent{
    @JsonProperty("xp")
    int xp;
    @JsonProperty("level")
    Material material;

    public Upgradable(int xp, Material material) {
        this.xp = xp;
        this.material = material;
    }
    private Upgradable(Upgradable other){
        this.xp = other.xp;
        this.material = other.material;
    }
    public Upgradable(){
        this(0, Material.STONE);
    }

    public int getXp() {
        return xp;
    }
    public Material getMaterial() {
        return material;
    }

    @Override
    public EntityComponent clone() {
        return new Upgradable(this);
    }

    @Override
    public boolean isTheSame(EntityComponent other) {
        if(!(other instanceof Upgradable)) return false;

        Upgradable otherUpgradable = (Upgradable) other;
        return (this.material == otherUpgradable.material);
    }
}
