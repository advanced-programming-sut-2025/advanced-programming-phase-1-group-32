package controllers;

import models.App;
import models.Date;
import models.Result;
import controllers.Controller;
import models.enums.Weather;

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

        return new Result(true, String.valueOf(currentDate.getDate()));
    }

    public Result getDateTime() {
        Date currentDate = App.getLoggedInAccount().getActiveGame().getDate();
        return new Result(true, "Date: " + currentDate.getDate() +
                "\tHour: " + currentDate.getHour());
    }

    public Result getDayOfTheWeek() {
        Date currentDate = App.getLoggedInAccount().getActiveGame().getDate();

        return new Result(true, currentDate.getWeekDay().toString().toLowerCase());
    }

    public Result advanceTime() {
        //TODO
        return null;
    }

    public Result advanceDate() {
        //TODO
        return null;
    }

    public void checkTurn() {
        //TODO
        //ghash
    }

    public void updatePerHour() {
        //TODO
    }

    public void updatePerDay() {
        //TODO
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
        //TODO
        return null;
    }

    public Result setWeather() {
        //TODO
        return null;
    }

    public Result walk() {
        //TODO
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
