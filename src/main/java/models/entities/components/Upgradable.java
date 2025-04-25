package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Upgradable extends EntityComponent{
    @JsonProperty("xp")
    int xp;
    @JsonProperty("level")
    int level;

    public Upgradable(int xp,int level) {
        this.xp = xp;
        this.level = level;
    }
    public Upgradable(){
        this(0, 0);
    }

    public int getXp() {
        return xp;
    }

    public int getLevel() {
        return level;
    }
}
