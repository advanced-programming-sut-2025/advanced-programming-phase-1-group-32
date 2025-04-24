package models.items.components;

import models.enums.Season;
import models.items.Entity;
import models.plant.Seed;

import java.util.ArrayList;

public class Growable extends EntityComponent {
    String name;
    ArrayList<Season> growingSeasons;
    boolean wateredToday;
    boolean oneTime;
    final Entity fruit = null;
    final Entity seed = null;
    final ArrayList<Integer> stages = null;
    int regrowthTime;
}