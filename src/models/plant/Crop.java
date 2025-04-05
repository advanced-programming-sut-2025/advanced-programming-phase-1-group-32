package models.plant;

import models.Updatable;
import models.enums.Season;

import java.util.ArrayList;

public class Crop implements Updatable {
    private String name;
    private ArrayList<Season> growingSeasons;
    private boolean wateredToday;
    private boolean oneTime;
    private final int fruitId;
    @Override
    public void updatePerHour() {

    }

    @Override
    public void updatePerDay() {

    }
}
