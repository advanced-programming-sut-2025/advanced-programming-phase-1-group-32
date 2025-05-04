package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Sellable extends EntityComponent{
    @JsonProperty("price")
    private int price;

    public Sellable(int price) {
        this.price = price;
    }
    private Sellable(Sellable other){
        this.price = other.price;
    }
    public Sellable(){
        this(0);
    }

    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public EntityComponent clone() {
        return new Sellable(this);
    }

    @Override
    public boolean isTheSame(EntityComponent other) {
        if(!(other instanceof Sellable)) return false;

        Sellable otherSellable = (Sellable) other;
        return this.price == otherSellable.price;
    }
}
