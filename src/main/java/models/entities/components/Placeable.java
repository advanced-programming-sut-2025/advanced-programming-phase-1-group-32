package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Placeable extends EntityComponent{
    @JsonProperty("isACollider")
    private final boolean isACollider;

    public Placeable(@JsonProperty("isACollider") boolean isACollider) {
        this.isACollider = isACollider;
    }
    public Placeable(){
        this(false);
    }
}
