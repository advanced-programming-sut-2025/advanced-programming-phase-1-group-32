package models;

import models.entities.Entity;
import models.enums.Weather;
import models.player.Player;

import java.util.ArrayList;

public class Game {
    private Weather todayWeather;
    private Weather tomorrowWeather;
    private Date date;
    private GameMap activeMap;
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private ArrayList<Entity> plantedEntities = new ArrayList<>();
    private boolean mapVisible = true;

    public Game() {
        this.date = new Date();
        this.todayWeather = Weather.SUNNY;
        this.tomorrowWeather = Weather.SUNNY;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void initGame() {

    }

    public ArrayList<Entity> getPlantedEntities() {
        return plantedEntities;
    }

    public void setPlantedEntities(ArrayList<Entity> plantedEntities) {
        this.plantedEntities = plantedEntities;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
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

    public Weather getTodayWeather() {
        return todayWeather;
    }

    public void setTodayWeather(Weather todayWeather) {
        this.todayWeather = todayWeather;
    }

    public Weather getTomorrowWeather() {
        return tomorrowWeather;
    }

    public void setTomorrowWeather(Weather tomorrowWeather) {
        this.tomorrowWeather = tomorrowWeather;
    }

    public void updateGamePerHour() {
        // this function should update things related to game
        //TODO

    }

    public void updateGamePerDay() {
        // this function should update things related to game

        // handling weather changes
        todayWeather = tomorrowWeather;
        tomorrowWeather = this.date.getSeason().getWeather();

        //TODO

    }

    public void setActiveMap(GameMap map) {
        this.activeMap = map;
    }

    public void toggleMapVisibility(){
        mapVisible = !mapVisible;
    }
    public boolean isMapVisible(){
        return mapVisible;
    }
}
