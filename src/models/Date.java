package models;

import models.enums.WeekDay;
import models.enums.Season;

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
}
