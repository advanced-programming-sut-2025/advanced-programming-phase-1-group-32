package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.entities.Entity;
import models.enums.Season;

import java.util.ArrayList;

public class Growable extends EntityComponent{
    @JsonProperty("seasons")
    private ArrayList<Season> growingSeasons;
    @JsonProperty("fruit")
    private final String fruit;
    @JsonProperty("seed")
    private final String seed;

    private final ArrayList<Integer> stages = null;
    private int regrowthTime;
    private boolean oneTime;
    private boolean wateredToday;

    public Growable(ArrayList<Season> growingSeasons, String fruit, String seed) {
        this.growingSeasons = growingSeasons;
        this.fruit = fruit;
        this.seed = seed;
    }

    public Growable() {
        this.seed = null;
        this.fruit = null;
    }
}