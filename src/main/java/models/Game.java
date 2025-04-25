package models;

import models.enums.Weather;
import models.player.Player;

import java.util.ArrayList;

public class Game {
    private Weather todayWeather;
    private Weather tomorrowWeather;
    private Date date;
    private GameMap activeMap;
    private final ArrayList<Player> players = new ArrayList<>();

    public Game() {

    }

    public void addPlayer(Player player) {
        players.add(player);
    }
    public void setWeather(){

    }

    public void initGame(){
    }

    public GameMap getActiveMap() {
        return activeMap;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
