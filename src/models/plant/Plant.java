package models.plant;

import models.Position;
import models.enums.Season;
import models.interfaces.Placable;
import models.interfaces.Updatable;
import models.items.Fruit;

import java.util.ArrayList;

public class Plant implements Updatable, Placable {
    protected String name;
    protected ArrayList<Season> growingSeasons;
    protected boolean wateredToday;
    protected boolean oneTime;
    protected final Fruit fruit;
    protected final Seed seed;
    protected final ArrayList<Integer> stages;
    protected int regrowthTime;


    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition() {

    }

    @Override
    public void placeOnGround() {

    }

    @Override
    public void updatePerHour() {

    }

    @Override
    public void updatePerDay() {

    }
}
