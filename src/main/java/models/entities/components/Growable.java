package models.entities.components;

import models.entities.Entity;
import models.enums.Season;

import java.util.ArrayList;

public class Growable extends EntityComponent{
    protected String name;
    protected ArrayList<Season> growingSeasons;
    protected boolean wateredToday;
    protected boolean oneTime;
    protected final Entity fruit = null;
    protected final Entity seed = null;
    protected final ArrayList<Integer> stages = null;
    protected int regrowthTime;
}