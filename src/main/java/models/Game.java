package models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.NPC.Dialogue;
import models.NPC.NPC;
import models.NPC.NpcFriendship;
import models.NPC.Quest;
import models.entities.Entity;
import models.entities.EntityRegistry;
import models.entities.EntityList;
import models.entities.components.Growable;
import models.entities.components.InteriorComponent;
import models.entities.components.PositionComponent;
import models.entities.systems.EntityPlacementSystem;
import models.entities.systems.ForageSpawnSystem;
import models.entities.systems.GrowthSystem;
import models.enums.EntityTag;
import models.enums.Season;
import models.enums.TileType;
import models.enums.Weather;
import models.gameMap.*;
import models.player.Player;
import models.player.Skill;
import models.player.Wallet;
import models.player.friendship.PlayerFriendship;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Game {
    private Weather todayWeather;
    private Weather tomorrowWeather;
    private Date date = new Date();
    private WorldMap mainMap;
    private ArrayList<Player> players = new ArrayList<>();
    private Player currentPlayer;
    private ArrayList<PlayerFriendship> playerFriendships = new ArrayList<>();
    private ArrayList<NPC> gameNPCs = new ArrayList<>();
    private boolean mapVisible = true;
    private ArrayList<Quest> quests = new ArrayList<>();
    private int tradeId = 1000;

    public Game(Account[] accounts) {
        for (Account account : accounts) {
            addPlayer(new Player(account));
        }

        App.setActiveGame(this);

        initGame();
    }

    public void setMainMap(WorldMap mainMap) {
        this.mainMap = mainMap;
    }

    public void initGame() {
        setCurrentPlayer(players.get(0));

        mainMap = new WorldMap(WorldMapType.DEFAULT.getData());
        setActiveMap(mainMap);

        this.todayWeather = Weather.SUNNY;
        this.tomorrowWeather = Weather.SUNNY;

        // init player's friendships
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                this.playerFriendships.add(new PlayerFriendship(players.get(i), players.get(j)));
            }
        }


        //player farms
        Map<MapRegion, FarmDetails> farmsDetails = mainMap.getFarmsDetail();

        for(int i = 0 ; i < players.size(); i++){
            MapRegion region = mainMap.getRegions().get(i);
            Player player = players.get(i);
            player.addRegion(region);
            player.setCurrentMap(mainMap);

            MapData.MapLayerData<String>.ObjectData houseDetails = farmsDetails.get(region).cottage;
            player.setHouse(App.entityRegistry.makeEntity(houseDetails.value));
            EntityPlacementSystem.placeEntity(player.getHouse(), new Vec2(houseDetails.x, houseDetails.y), mainMap);
            for(Entity e : player.getHouse().getComponent(InteriorComponent.class).getMap().getEntities()){
                if(e.getEntityName().equals("Fridge")){
                    player.setRefrigerator(e);
                }
            }

            EntityPlacementSystem.placeOnMap(player, new Position(2, 2), player.getHouse().getComponent(InteriorComponent.class).getMap());
        }

        mainMap.initRandomElements();
        initNPCs();
    }

    public void initNPCs() {
        // create NPC
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            List<NPC> NPCs = objectMapper.readValue(new File("src/data/NPC/npcNames.json"),
                    new TypeReference<List<NPC>>() {});

            for (Entity npc : NPCs) {
                NPC realNPC = new NPC(npc.getEntityName());
                gameNPCs.add(realNPC);
                for (Player player : players) {
                    player.getNpcFriendships().put(realNPC, new NpcFriendship());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //init quests
        try {
            // TODO: Complete quests.jason
            ObjectMapper mapper = new ObjectMapper();
            List<Quest> quests = mapper.readValue(
                    new File("src/data/NPC/quests.json"),
                    new TypeReference<List<Quest>>() {}
            );

            for (Quest quest : quests) {
                quest.setNpc(findNPC(quest.getNpcName()));
                this.quests.add(quest);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        // Initialize dialogs
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Dialogue> dialogues = mapper.readValue(
                    new File("src/data/NPC/dialogues.json"),
                    new TypeReference<List<Dialogue>>() {}
            );

            for (Dialogue dialogue : dialogues) {
                NPC npc = findNPC(dialogue.getNpcName());
                npc.getDialogues().add(dialogue);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Prints the reason for failure
        }

        // Put NPC on the Map
        for (NPC npc : gameNPCs) {

        }

        //TODO
    }

    public ArrayList<Quest> getQuests() {
        return quests;
    }

    public void setQuests(ArrayList<Quest> quests) {
        this.quests = quests;
    }

    public NPC findNPC(String npcName) {
        for (NPC npc : gameNPCs) {
            if (npc.getName().equals(npcName)) {
                return npc;
            }
        }
        return null;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void nextTurn(){

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public GameMap getActiveMap() {
        return currentPlayer.getCurrentMap();
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

        GrowthSystem.updatePerDay(this);

        ForageSpawnSystem.updatePerDay();

        for (Player player : players) {
            player.updatePerDay();
        }

        for (Quest quest : quests) {
            quest.reduceDaysToUnlocked();
        }

        dailyThor();

        crowAttack();
    }

    public void crowAttack() {
        for (Player player : players) {
            ArrayList<Tile> tiles = new ArrayList<>(); //TODO: get planted tiles
//            tiles = player.ma
            for (int i = 0; i < Math.floor((double) tiles.size() / 16); i++) {
                Tile tile = tiles.get((int) (Math.random() * tiles.size()));
                //TODO
            }
        }
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
            EntityPlacementSystem.emptyTile(tile);
            tile.setType(TileType.GRASS);
        }

        if (tile.getContent() != null &&tile.getContent().hasTag(EntityTag.TREE)) {
            EntityPlacementSystem.placeOnTile(App.entityRegistry.makeEntity("Burned Tree"), tile);
//          tile.setContent(null);
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
        this.currentPlayer.setCurrentMap(map);
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

    public Quest findQuest(int questId) {
        for (Quest quest : quests) {
            if (quest.getId() == questId) {
                return quest;
            }
        }

        return null;
    }

    public WorldMap getMainMap() {
        return mainMap;
    }

    /**
      this is the ugliest function of the project, it will take the season and fishing skill
      and return name of available fish for fishing. I have done this to avoid making new classes
      for fish...
     */
    public ArrayList<String> getAvailableFish(Season season, Skill fishingSkill) {
        ArrayList<String> availableFish = new ArrayList<>();
        switch (season) {
            case SPRING -> {
                availableFish.add("Ghostfish");
                availableFish.add("Flounder");
                availableFish.add("Lionfish");
                availableFish.add("Herring");
                if (fishingSkill.getLevel() >= 4) {
                    availableFish.add("Legend");
                }
            }
            case SUMMER -> {
                availableFish.add("Tilapia");
                availableFish.add("Dorado");
                availableFish.add("Sunfish");
                availableFish.add("Rainbow Trout");
                if (fishingSkill.getLevel() >= 4) {
                    availableFish.add("Crimsonfish");
                }

            }
            case FALL -> {
                availableFish.add("Salmon");
                availableFish.add("Sardine");
                availableFish.add("Shad");
                availableFish.add("Blue Discus");
                if (fishingSkill.getLevel() >= 4) {
                    availableFish.add("Angler");
                }
            }
            case WINTER -> {
                availableFish.add("Midnight Carp");
                availableFish.add("Squid");
                availableFish.add("Tuna");
                availableFish.add("Perch");
                if (fishingSkill.getLevel() >= 4) {
                    availableFish.add("Glacierfish");
                }

            }
        }

        return availableFish;
    }
}
