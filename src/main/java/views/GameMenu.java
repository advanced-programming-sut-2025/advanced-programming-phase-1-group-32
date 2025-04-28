package views;

import controllers.GameMenuController;
import models.App;
import models.Commands.GameMenuCommands;
import models.Game;
import models.GameMap;
import models.Tile;
import views.inGame.Renderer;

import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu{

    @Override
    public void checker(Scanner scanner) {
        Game activeGame = App.getLoggedInAccount().getActiveGame();
        //
        String input = scanner.nextLine().trim();

//        printMap(activeGame.getActiveMap());

        Matcher matcher;
        GameMenuController controller = new GameMenuController();

        if (GameMenuCommands.DATE.getMatcher(input) != null) {
            System.out.println(controller.getDate());

        } else if (GameMenuCommands.DATETIME.getMatcher(input) != null) {
            System.out.println(controller.getDateTime());

        } else if (GameMenuCommands.TIME.getMatcher(input) != null) {
            System.out.println(controller.getTime());

        } else if (GameMenuCommands.DAY_OF_THE_WEEK.getMatcher(input) != null) {
            System.out.println(controller.getDayOfTheWeek());

        } else if ((matcher = GameMenuCommands.ADVANCE_TIME.getMatcher(input)) != null) {
            int amount = Integer.parseInt(matcher.group(1));
            System.out.println(controller.advanceTime(amount));

        } else if ((matcher = GameMenuCommands.ADVANCE_DATE.getMatcher(input)) != null) {
            int amount = Integer.parseInt(matcher.group(1));
            System.out.println(controller.advanceDate(amount));

        } else if (GameMenuCommands.SEASON.getMatcher(input) != null) {
            System.out.println(controller.showSeason());

        } else if (GameMenuCommands.WEATHER.getMatcher(input) != null) {
            System.out.println(controller.showWeather());

        } else if (GameMenuCommands.WEATHER_FORECAST.getMatcher(input) != null) {
            System.out.println(controller.weatherForecast());

        } else if ((matcher = GameMenuCommands.SET_WEATHER.getMatcher(input)) != null) {
            System.out.println(controller.setWeather(matcher.group("type")));
        } else {
            System.out.println("Invalid Command!");
        }


    }

    public void printMap(GameMap map) {
        Renderer renderer = new Renderer();
        Tile[][] tiles = map.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                char ch = '1';
                Tile tile = tiles[i][j];
                renderer.clear();
                renderer.mvAddchColored(tile.getCol(), tile.getRow(), 'A', tile.getColor());

            }
        }

    }
}
