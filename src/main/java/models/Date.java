package src.main.java.models;

import src.main.java.models.enums.WeekDay;
import src.main.java.models.enums.Season;

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
        return null;
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
