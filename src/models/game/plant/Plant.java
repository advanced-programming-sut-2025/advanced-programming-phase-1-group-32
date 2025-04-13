package models.game.plant;

import models.game.Position;
import models.game.enums.Season;
import models.game.interfaces.Placable;
import models.game.interfaces.Updatable;
import models.game.items.Item;

import java.util.ArrayList;

public class Plant implements Updatable, Placable {
    protected String name;
    protected ArrayList<Season> growingSeasons;
    protected boolean wateredToday;
    protected boolean oneTime;
    protected final Item fruit;
    protected final Item seed;
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
