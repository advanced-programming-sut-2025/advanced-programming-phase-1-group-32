package models.game;

import models.game.enums.WeekDay;
import models.game.enums.Season;

public class Date {
    private Season season;
    private int date;
    private int hour;

    public Season getSeason() {
        return season;
    }

    public int getDate() {
        return date;
    }

    public WeekDay getWeekDay() {
        //TODO
    }

    public int getHour() {
        return hour;
    }

    public void addHour() {

    }

    public void addDay() {

    }

    public void addSeason() {

    }
}
