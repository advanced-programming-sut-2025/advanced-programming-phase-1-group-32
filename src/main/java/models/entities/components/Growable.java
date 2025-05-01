package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.enums.Season;

import java.util.ArrayList;

public class Growable extends EntityComponent{
    @JsonProperty("seasons")
    private ArrayList<Season> growingSeasons = new ArrayList<>();
    @JsonProperty("fruit")
    private final String fruit;
    @JsonProperty("seed")
    private final String seed;
    @JsonProperty("stages")
    private ArrayList<Integer> stages = new ArrayList<>();
    @JsonProperty("totalHarvestTime")
    private int totalHarvestTime;
    private boolean wateredToday;
    @JsonProperty("canBecomeGiant")
    private boolean canBecomeGiant;
    private int daysPastFromPlant;
    private boolean isFertilized;

    public Growable(ArrayList<Season> growingSeasons, String fruit, String seed, int totalHarvestTime) {
        this.growingSeasons = growingSeasons;
        this.fruit = fruit;
        this.seed = seed;
        this.totalHarvestTime = totalHarvestTime;
    }

    public Growable(Growable other) {
        this.growingSeasons.addAll(other.growingSeasons);
        this.fruit = other.fruit;
        this.seed = other.seed;
        this.totalHarvestTime = other.totalHarvestTime;
        this.wateredToday = other.wateredToday;
        this.canBecomeGiant = other.canBecomeGiant;
        this.daysPastFromPlant = other.daysPastFromPlant;
        this.isFertilized = other.isFertilized;

    }

    public Growable() {
        this.seed = null;
        this.fruit = null;
    }

    public int getTotalHarvestTime() {
        return totalHarvestTime;
    }

    public ArrayList<Season> getGrowingSeasons() {
        return growingSeasons;
    }

    public void setGrowingSeasons(ArrayList<Season> growingSeasons) {
        this.growingSeasons = growingSeasons;
    }

    public String getFruit() {
        return fruit;
    }

    public String getSeed() {
        return seed;
    }

    public ArrayList<Integer> getStages() {
        return stages;
    }

    public boolean isWateredToday() {
        return wateredToday;
    }

    public void setWateredToday(boolean wateredToday) {
        this.wateredToday = wateredToday;
    }

    public boolean isCanBecomeGiant() {
        return canBecomeGiant;
    }

    public void setCanBecomeGiant(boolean canBecomeGiant) {
        this.canBecomeGiant = canBecomeGiant;
    }

    @Override
    public EntityComponent clone() {
        return new Growable(this);
    }

    public String getInfo() {
        StringBuilder message = new StringBuilder();
        message.append("Days left to harvest: ").append(totalHarvestTime - daysPastFromPlant).append("\n");
        message.append("Stage: ").append(getStage()).append("\n");
        message.append("IsWateredToday: ").append(wateredToday).append("\n");
        message.append("IsFertilized: ").append(isFertilized).append("");

        return message.toString();
    }

    public int getStage() {
        int count = 0;
        int stage = 0;
        for (Integer i : stages) {
            count += i;
            stage++;
            if (count >= totalHarvestTime) {
                return stage;
            }
        }
        return stage;
    }
}