package models.shop;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.entities.Entity;
import models.enums.Season;

public class OtherShopProduct extends ShopProduct {
    private Season season;

    @JsonCreator
    public OtherShopProduct(@JsonProperty("name") String name,
                            @JsonProperty("dailyLimit") int dailyLimit,
                            @JsonProperty("season") Season season) {
        super(name, dailyLimit);
        this.season = season;
    }

    @Override
    public Entity getEntity() {
        return App.entityRegistry.makeEntity(this.name);
    }

    @Override
    public int getWoodCost() {
        return 0;
    }

    @Override
    public int getStoneCost() {
        return 0;
    }

    public boolean isInSeason(Season season) {
        return this.season.equals(season);
    }

    @Override
    public boolean isAvailable() {
        return this.getStock() != 0 && (season == null || App.getActiveGame().getDate().getSeason().equals(season));
    }

    @Override
    public String toString() {
        if(getStock() == 0)
            return super.toString();
        return super.toString() + " available in " + season.toString();
    }
}
