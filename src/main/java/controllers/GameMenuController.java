package controllers;

import models.*;
import models.Date;
import models.entities.Entity;
import models.entities.components.*;
import models.enums.EntityTag;
import models.enums.TileType;
import models.entities.EntityRegistry;
import models.entities.components.*;
import models.entities.components.inventory.Inventory;
import models.enums.Weather;
import models.player.Energy;
import models.player.Player;
import records.Result;
import records.WalkProposal;
import views.inGame.Renderer;

import java.util.*;

public class GameMenuController implements Controller {
    @Override
    public Result changeMenu(String menuName) {
        return null;
    }

    public Result gameMap() {
        //TODO
        return null;
    }

    public Result exitGame() {
        //TODO
        return null;
    }

    public Result deleteGame() {
        //TODO
        return null;
    }

    public Result nextTurn() {
        //TODO
        return null;
    }

    public Result getTime() {
        Date currentDate = App.getLoggedInAccount().getActiveGame().getDate();

        return new Result(true, String.valueOf(currentDate.getHour()));
    }

    public Result getDate() {
        Date currentDate = App.getLoggedInAccount().getActiveGame().getDate();

        return new Result(true, String.valueOf(currentDate.getDay()));
    }

    public Result getDateTime() {
        Date currentDate = App.getLoggedInAccount().getActiveGame().getDate();
        return new Result(true, "Date: " + currentDate.getDay() +
                "\tHour: " + currentDate.getHour());
    }

    public Result getDayOfTheWeek() {
        Date currentDate = App.getLoggedInAccount().getActiveGame().getDate();

        return new Result(true, currentDate.getWeekDay().toString().toLowerCase());
    }

    public Result advanceTime(int amount) {
        Game game = App.getLoggedInAccount().getActiveGame();
        Date date = game.getDate();

        // this function will update data about game.Date
        date.addHour(amount, game);
        game.setDate(date);

        return new Result(true, "We've traveled through time for " + amount + " hours!");
    }

    public Result advanceDate(int amount) {
        Game game = App.getLoggedInAccount().getActiveGame();
        Date date = game.getDate();

        // this function will update data about game.Date
        date.addDay(amount, game);
        game.setDate(date);
        return new Result(true, "We've traveled through time for " + amount + " days");
    }

    public Result showSeason() {
        Date currentDate = App.getLoggedInAccount().getActiveGame().getDate();
        return new Result(true, currentDate.getSeason().name().toLowerCase());
    }

    public void checkTurn() {
        //TODO
        //ghash
    }

    public Result thor() {
        //TODO
        return null;
    }

    public Result showWeather() {
        Weather weather = App.getLoggedInAccount().getActiveGame().getTodayWeather();
        return new Result(true, weather.toString().toLowerCase());
    }

    public Result weatherForecast() {
        Weather weather = App.getLoggedInAccount().getActiveGame().getTomorrowWeather();

        return new Result(true, weather.toString().toLowerCase());
    }

    public Result setWeather(String weatherString) {
        Weather weather = Weather.getweather(weatherString);
        if (weather == null) {
            return new Result(false, "Weather not found");
        }

        Game game = App.getLoggedInAccount().getActiveGame();
        game.setTomorrowWeather(weather);
        return new Result(true, "Tomorrow's weather changed to " + weather.name().toLowerCase());
    }

    public WalkProposal proposeWalk(int x, int y) {
        Game game = App.getLoggedInAccount().getActiveGame();
        Player player = game.getCurrentPlayer();
        GameMap map = game.getActiveMap();
        Tile start = map.getTileByPosition(
                game.getCurrentPlayer().getPosition()
        );
        Tile goal = map.getTileByPosition(new Position(y, x));
        if(start.equals(goal))
            return new WalkProposal(
                    false,
                    "you are already in " + goal.getPosition(),
                    0, x, y
            );
        int distance = shortestPath(goal, start, map.getTiles()).size();
        if(distance == 0)
            return new WalkProposal(false, "you can't reach " + goal.getPosition(), 0, x ,y);
        return new WalkProposal(true, "OK", distance / 20, x, y);


    }

    public Result executeWalk(WalkProposal p) {
        Player player = App.getLoggedInAccount().getActiveGame().getCurrentPlayer();
        int initialEnergyAmount = player.getEnergy().getAmount();

        if(!p.isAllowed()) {
            return new Result(false, "No walk was proposed");
        }
        player.setPosition(new Position(p.y(), p.x()));
        player.getEnergy().setAmount(initialEnergyAmount - p.energyCost());
        return new Result(true, "you walked to "
                + player.getPosition()
                + " (-" + p.energyCost() + " energy)");
    }

    private List<Tile> shortestPath(Tile destination, Tile src, Tile[][] tiles) {
        int rows = tiles.length;
        int cols = tiles[0].length;

        Queue<Tile> queue = new LinkedList<>();
        Map<Tile, Tile> cameFrom = new HashMap<>();
        queue.add(src);
        cameFrom.put(src, null);

        int[][] directions = {
                {0,1}, {1,0}, {0, -1}, {0, 1}
                , {1,-1}, {1,1}, {-1,1}, {-1,-1} /* Comment this line to walk vertically and horizontally only*/
        };

        while (!queue.isEmpty()) {
            Tile current = queue.poll();

            if(current.equals(destination))
                break;


            for (int[] dir : directions) {
                int newRow = current.getRow() + dir[0];
                int newCol = current.getCol() + dir[1];

                if(newCol >= 0 && newCol < cols && newRow >= 0 && newRow < rows) {
                    Tile neighbor = tiles[newRow][newCol];
                    if(neighbor.getContent() == null || neighbor.getContent().getComponent(Placeable.class).isWalkable())
                        if(!cameFrom.containsKey(neighbor)) {
                            queue.add(neighbor);
                            cameFrom.put(neighbor, current);
                        }
                }
            }

        }
        if(!cameFrom.containsKey(destination)) {
            return Collections.emptyList();
        }

        List<Tile> path = new ArrayList<>();
        Tile current = destination;
        while(current != null) {
            path.add(current);
            current = cameFrom.get(current);
        }
        Collections.reverse(path);
        return path;


    }

    public Result printMap() {
        //TODO
        return null;
    }

    public Result helpReadingMap() {
        //TODO
        return null;
    }

    public Result energyShow() {
        Game game = App.getLoggedInAccount().getActiveGame();
        Player player = game.getCurrentPlayer();
        Energy energy = player.getEnergy();


        return new Result(true, "energy left: " + energy.getAmount());
    }

    public Result energySet(int amount) {
        Game game = App.getLoggedInAccount().getActiveGame();
        Player player = game.getCurrentPlayer();
        Energy energy = player.getEnergy();

        energy.setAmount(amount);
        return new Result(true, "energy set to " + energy.getAmount());
    }

    public Result energyUnlimited() {
        Game game = App.getLoggedInAccount().getActiveGame();
        Player player = game.getCurrentPlayer();
        Energy energy = player.getEnergy();

        energy.toggleUnlimited();

        return new Result(true, "energy unlimited: " + energy.isUnlimited());
    }

    public Result showInventory() {
        //TODO
        return null;
    }

    public Result inventoryTrash() {
        //TODO
        return null;
    }

    public Result toolsEquip() {
        //TODO
        return null;
    }

    public Result showTools() {
        //TODO
        return null;
    }

    public Result toolsUpgrade() {
        //TODO
        return null;
    }

    public Result toolsUse() {
        //TODO
        return null;
    }

    public Result craftInfo(String name) {
        if(!App.entityRegistry.doesEntityExist(name)) {
            return new Result(false, "There is no crop with name" + name);
        }
        Entity crop = App.entityRegistry.makeEntity(name);
        if (!crop.hasTag(EntityTag.CROP)) {
            return new Result(false, "There is no crop with name" + name);
        }
        Growable growable = crop.getComponent(Growable.class);
        Edible edible = crop.getComponent(Edible.class);
        Sellable sellable = crop.getComponent(Sellable.class);
        Harvestable harvestable = crop.getComponent(Harvestable.class);

        StringBuilder message = new StringBuilder();
        message.append("Name: ").append(crop.getName()).append("\n").
                append("Source: ").append(growable.getSeed()).append("\n")
                .append("Stages: ").append(growable.getStages()).append("\n")
                .append("Total Harvest Time: ").append(growable.getTotalHarvestTime()).append("\n")
                .append("One Time: ").append(harvestable.isOneTime()).append("\n");

        if (harvestable.getRegrowthTime() > 0) {
            message.append("Regrowth Time: ").append(harvestable.getRegrowthTime()).append("\n");
        } else {
            message.append("Regrowth Time:\n");
        }

        message.append("Base Sell Price: ").append(sellable.getPrice()).append("\n");

        if (edible != null) {
            message.append("""
                    Is Edible: false
                    Base Energy: 0
                    """);
        } else {
            message.append("Is Edible: true\n" + "Base Energy: ").append(edible.getEnergy()).append("\n");
        }

        message.append("Season: ").append(growable.getGrowingSeasons()).append("\n")
                .append("Can Become Giant: ").append(growable.isCanBecomeGiant());


        return new Result(true, message.toString());
    }

    public Result plant(String seedString, String direction) {
        Game game = App.getLoggedInAccount().getActiveGame();
        Player player = game.getCurrentPlayer();
        if (!App.entityRegistry.doesEntityExist(seedString)) {
            return new Result(false, "There is no seed with name" + seedString);
        }

        Entity seed = App.entityRegistry.makeEntity(seedString);

        if (!seed.hasTag(EntityTag.SEED)) {
            return new Result(false, "There is no seed with name" + seedString);
        }

        Position position = player.getPosition().changeByDirection(direction);
        if (position == null) {
            return new Result(false, "type a valid direction");
        }


        //TODO: check if its available in inventory

        Tile tile = game.getActiveMap().getTileByPosition(position);
        if (tile.getType() != TileType.PLANTED_GROUND) tile.setType(TileType.HOED_GROUND);

        if (tile == null || !tile.getType().equals(TileType.HOED_GROUND)) {
            return new Result(false, "tile is unavailable for planting");
        }


        tile.plant(seed);
        return new Result(true, "planted succusfully");
    }

    public Result showPlant(int col, int row) {
        Position position = new Position(row, col);
        Game game = App.getLoggedInAccount().getActiveGame();
        GameMap gameMap = game.getActiveMap();
        Tile tile = gameMap.getTileByPosition(position);

        if (tile == null) {
            return new Result(false, "No tile found");
        }

        if (tile.getType() != TileType.PLANTED_GROUND) {
            return new Result(false, "Tile is not a planted ground");
        }

        Entity plantedEntity = tile.getContent();

        StringBuilder message = new StringBuilder();
        message.append("Name: ").append(plantedEntity.getName()).append("\n");
        message.append(plantedEntity.getComponent(Growable.class).getInfo());
        return new Result(true, message.toString());
    }

    public Result fertilize() {
        //TODO
        return null;
    }

    public Result checkWater(){
        //TODO
        return null;
    }

    public Result showCratingRecipes() {
        //TODO
        return null;
    }

    public Result craft(){
        //TODO
        return null;
    }

    public Result placeItem() {
        //TODO
        return null;
    }

    public Result addItem() {
        //TODO
        return null;
    }

    public Result showCookingRecipes() {
        //TODO
        return null;
    }

    public Result refrigerator() {
        //TODO
        return null;
    }

    public Result cookingPrepare() {
        //TODO
        return null;
    }

    public Result eat() {
        //TODO
        return null;
    }

    public Result buildAnimalHouse() {
        //TODO
        return null;
    }

    public Result buyAnimal() {
        //TODO
        return null;
    }

    public Result pet() {
        //TODO
        return null;
    }

    public Result animals() {
        //TODO
        return null;
    }

    public Result shephreAnimal() {
        //TODO
        return null;
    }

    public Result feedHay(){
        //TODO
        return null;
    }

    public Result showProduces() {
        //TODO
        return null;
    }

    public Result collectProduces() {
        //TODO
        return null;
    }

    public Result sellAnimal() {
        //TODO
        return null;
    }

    public Result fishing() {
        //TODO
        return null;
    }

    public Result useArtisan() {
        //TODO
        return null;
    }

    public Result getArtisan() {
        //TODO
        return null;
    }

    public Result friendship() {
        //TODO
        return null;
    }

    public Result talk() {
        //TODO
        return null;
    }

    public Result talkHistory() {
        //TODO
        return null;
    }

    public Result meetNPC(){
        //TODO
        return null;
    }

    public Result giftNPC() {
        //TODO
        return null;
    }

    public Result friendshipNPC() {
        //TODO
        return null;
    }


    private void saveGame() {
        //TODO
    }

    public Result switchInputType(){
        App.getView().switchInputType();
        if(App.getView().isRawMode()){
            return new Result(true, "You are in raw mode");
        }else{
            return new Result(true, "You are in normal mode");
        }
    }
    public Result handleRawInput(char c){
        Player player = App.getLoggedInAccount().getActiveGame().getCurrentPlayer();
        switch (c){
            case 'a':
                player.changePosition(-1, 0);
                break;
            case 's':
                player.changePosition(0, 1);
                break;
            case 'w':
                player.changePosition(0, -1);
                break;
            case 'd':
                player.changePosition(1, 0);
                break;
            default:
                break;
        }
        return null;
    }

    public Result toggleMap(){
        App.getLoggedInAccount().getActiveGame().toggleMapVisibility();
        return null;
    }
    public Result cheatGiveItem(String name, int quantity){
        Player currentPlayer = App.getLoggedInAccount().getActiveGame().getCurrentPlayer();
        if(!App.entityRegistry.doesEntityExist(name)){
            return new Result(false, "entity doesnt exist");
        }
        Entity entity = App.entityRegistry.makeEntity(name);
        if(entity.getComponent(Pickable.class) == null){
            return new Result(false, "entity isn't pickable");
        }
        entity.getComponent(Pickable.class).changeStackSize(quantity);
        currentPlayer.getComponent(Inventory.class).addItem(entity);
        return new Result(true, quantity + " " + name + (quantity > 1 ? "s" : "") + " were given to " + currentPlayer.getAccount().getNickname());
    }
}
