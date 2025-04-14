package src.main.java.models.plant;

import src.main.java.models.Position;
import src.main.java.models.enums.Season;
import src.main.java.models.interfaces.Placable;
import src.main.java.models.interfaces.Updatable;
import src.main.java.models.items.Fruit;

import java.util.ArrayList;

public class Plant implements Updatable, Placable {
    protected String name;
    protected ArrayList<Season> growingSeasons;
    protected boolean wateredToday;
    protected boolean oneTime;
    protected final Fruit fruit = null;
    protected final Seed seed = null;
    protected final ArrayList<Integer> stages = null;
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
