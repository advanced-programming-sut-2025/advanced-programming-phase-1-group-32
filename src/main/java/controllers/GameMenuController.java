package controllers;

import models.*;
import models.Date;
import models.entities.components.Placeable;
import models.enums.Weather;
import models.player.Energy;
import models.player.Player;
import records.Result;
import records.WalkProposal;

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
                    if(neighbor.getContent().getComponent(Placeable.class).isWalkable())
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

    public Result craftInfo() {
        //TODO
        return null;
    }

    public Result plant() {
        //TODO
        return null;
    }

    public Result showPlant() {
        //TODO
        return null;
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

}
