package models;

import models.entities.Entity;
import models.entities.components.Growable;
import models.enums.EntityTag;
import models.enums.TileType;
import models.enums.Weather;
import models.gameMap.Environment;
import models.gameMap.GameMap;
import models.gameMap.GameMapType;
import models.player.Player;
import models.player.Wallet;
import models.player.friendship.PlayerFriendship;

import java.util.ArrayList;

public class Game {
    private Weather todayWeather;
    private Weather tomorrowWeather;
    private Date date = new Date();
    private GameMap activeMap;
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private ArrayList<Entity> plantedEntities = new ArrayList<>();
    private ArrayList<PlayerFriendship> playerFriendships = new ArrayList<>();
    private boolean mapVisible = true;
    private int tradeId = 1000;

    public Game(Account[] accounts) {
        for (Account account : accounts) {
            addPlayer(new Player(account));
        }

        initGame();
    }

    public void initGame() {
        setCurrentPlayer(players.get(0));

        setActiveMap(new GameMap(GameMapType.DEFAULT, Environment.OUTDOOR));

        this.todayWeather = Weather.SUNNY;
        this.tomorrowWeather = Weather.SUNNY;

        // init player's friendships
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                this.playerFriendships.add(new PlayerFriendship(players.get(i), players.get(j)));
            }
        }

        //player farms
        for(int i = 0 ; i < players.size(); i++){
            players.get(i).addRegion(activeMap.getRegions().get(i));
        }
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void nextTurn(){

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

    public int getTradeId() {
        tradeId++;
        return tradeId;

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

    public boolean checkPlayerDistance(Player player1, Player player2) {
        Position position1 = player1.getPosition();
        Position position2 = player2.getPosition();

        int distance = (position1.getRow() - position2.getRow()) * (position1.getRow() - position2.getRow())
                + (position1.getCol() - position2.getCol()) * (position1.getCol() - position2.getCol());

        return distance < 3;
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

        for (PlayerFriendship playerFriendship : playerFriendships) {
            playerFriendship.updateDaily();
        }

        for (Entity entity : plantedEntities) {
            entity.getComponent(Growable.class).updatePerDay();
        }

        for (Player player : players) {
            player.updatePerDay();
        }


        //TODO
    }

    public void dailyThor() {
        if (!getTodayWeather().equals(Weather.STORMY)) {
            return;
        }

        ArrayList<Tile> affectedTiles = new ArrayList<>();
        // TODO: add tiles to effect

        for (Tile tile : affectedTiles) {
            thorTile(tile);
        }


    }

    public void thorTile(Tile tile) {
        if (tile.getContent() != null && tile.getContent().hasTag(EntityTag.CROP)) {
            tile.setContent(null);
            tile.setType(TileType.GRASS);
        }

        if (tile.getContent() != null &&tile.getContent().hasTag(EntityTag.TREE)) {
                tile.setContent(App.entityRegistry.makeEntity("Burned Tree"));
//            tile.setContent(null);
            tile.setType(TileType.GRASS);
            // TODO: change it to coal
        }

    }

    public void marry(Player girl, Player boy) {
        girl.setSpouse(boy);
        boy.setSpouse(girl);

        // combining wallets
        Wallet boyWallet = boy.getWallet();
        Wallet girlWallet = girl.getWallet();
        Wallet wallet = Wallet.combineWallets(boyWallet, girlWallet);

        boy.setWallet(wallet);
        girl.setWallet(wallet);

        //set friendship
        PlayerFriendship playerFriendship = getFriendshipBetween(girl, boy);
        playerFriendship.setLevel(4);

        //TODO: other effects

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

    public ArrayList<PlayerFriendship> getCurrentPlayerFriendships() {
        ArrayList<PlayerFriendship> friendships = new ArrayList<>();
        for (PlayerFriendship playerFriendships : playerFriendships) {
            if (playerFriendships.getFriends().contains(currentPlayer)) {
                friendships.add(playerFriendships);
            }
        }

        return friendships;
    }

    public PlayerFriendship getFriendshipWith(Player friend) {
        for (PlayerFriendship playerFriendship : playerFriendships) {
            if (playerFriendship.getFriends().contains(friend) &&
            playerFriendship.getFriends().contains(currentPlayer)) {
                return playerFriendship;
            }
        }
        return null;
    }

    public PlayerFriendship getFriendshipBetween(Player friend1, Player friend2) {
        for (PlayerFriendship playerFriendship : playerFriendships) {
            if (playerFriendship.getFriends().contains(friend1) &&
                    playerFriendship.getFriends().contains(friend2)) {
                return playerFriendship;
            }
        }
        return null;
    }
}
