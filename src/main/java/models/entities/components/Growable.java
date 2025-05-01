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
    @JsonProperty("stages")
    private final ArrayList<Integer> stages = null;
    @JsonProperty("totalHarvestTime")
    private int totalHarvestTime;
    @JsonProperty("regrowthTime")
    private int regrowthTime;
    @JsonProperty("oneTime")
    private boolean oneTime;
    private boolean wateredToday;
    @JsonProperty("canBecomeGiant")
    private boolean canBecomeGiant;

    public Growable(ArrayList<Season> growingSeasons, String fruit, String seed, int totalHarvestTime) {
        this.growingSeasons = growingSeasons;
        this.fruit = fruit;
        this.seed = seed;
        this.totalHarvestTime = totalHarvestTime;
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

    public int getRegrowthTime() {
        return regrowthTime;
    }

    public void setRegrowthTime(int regrowthTime) {
        this.regrowthTime = regrowthTime;
    }

    public boolean isOneTime() {
        return oneTime;
    }

    public void setOneTime(boolean oneTime) {
        this.oneTime = oneTime;
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