package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sellable extends EntityComponent{
    @JsonProperty("price")
    private int price;

    public Sellable(int price) {
        this.price = price;
    }
    public Sellable(){
        this(0);
    }
}
