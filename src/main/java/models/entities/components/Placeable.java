package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Placeable extends EntityComponent{
    @JsonProperty("isWalkable")
    private final boolean isWalkable;

    public Placeable(boolean isWalkable) {
        this.isWalkable = isWalkable;
    }
    public Placeable(){
        this(false);
    }

    public boolean isWalkable() {
        return isWalkable;
    }

    @Override
    public String toString() {
        return "Placeable{" +
                "isWalkable=" + isWalkable +
                '}';
    }
}
