package controllers;

import models.*;
import models.entities.components.EntityComponent;
import models.enums.Weather;

import java.util.concurrent.TimeoutException;

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

    public Result walk() {
        Tile tile = new Tile(new Position(10, 10));
        return null;
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
        //TODO
        return null;
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

}
