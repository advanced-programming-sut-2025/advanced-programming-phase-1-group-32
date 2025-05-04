package models;

import models.enums.WeekDay;
import models.enums.Season;

public class Date {
    private Season season;
    private int day;
    private int hour;

    public Date() {
        this.season = Season.SPRING;
        this.day = 1;
        this.hour = 0;
    }

    public Season getSeason() {
        return season;
    }

    public int getDay() {
        return day;
    }

    public WeekDay getWeekDay() {
        WeekDay weekDay;
        weekDay = WeekDay.getWeekDay(day % 7);

        return weekDay;
    }

    public int getHour() {
        return hour;
    }

    public void addHour(int amount, Game game) {
        for (int i = 0 ; i < amount ; i++) {
            game.updateGamePerHour();
            if (hour == 23) {
                hour = 0;
                game.updateGamePerDay();
                addDay(1, game);
            } else {
                hour++;
            }
        }
    }

    public void addDay(int amount, Game game) {
        for (int i = 0 ; i < amount ; i++) {
            game.updateGamePerDay();
            if (day == 28)  {
                day = 1;
                this.season = Season.nextSeason(this.season);
            } else {
                day++;
            }
        }
    }

    @Override
    public String toString() {
        return "Day: " + day + "  Hour: " + hour + "  Season: " + season.name().toLowerCase();
    }
}
