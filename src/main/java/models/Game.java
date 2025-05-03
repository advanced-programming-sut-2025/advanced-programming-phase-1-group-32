package models;

import models.entities.Entity;
import models.enums.Weather;
import models.player.Player;

import java.util.ArrayList;

public class Game {
    private Weather todayWeather;
    private Weather tomorrowWeather;
    private Date date = new Date();
    private GameMap activeMap;
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private ArrayList<Entity> plantedEntities = new ArrayList<>();
    private boolean mapVisible = true;

    public Game(Account[] accounts) {
        for (Account account : accounts) {
            addPlayer(new Player(account));
        }

        initGame();
    }

    public void initGame() {
        setCurrentPlayer(players.get(0));

        setActiveMap(new GameMap(1000, 1000));

        this.todayWeather = Weather.SUNNY;
        this.tomorrowWeather = Weather.SUNNY;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void nextTurn(){
        Player tmp = currentPlayer;
        players.remove(0);
        players.add(tmp);
        setCurrentPlayer(players.get(0));
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

    public Player findPlayer(String playerName) {
        for (Player player : players) {
            if (player.getAccount().getUsername().equals(playerName)) {
                return player;
            }
        }

        return null;
    }
}
