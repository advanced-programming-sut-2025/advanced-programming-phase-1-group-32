package views;

import controllers.GameMenuController;
import models.*;
import models.Commands.GameMenuCommands;
import models.entities.Entity;
import models.entities.components.EntityComponent;
import models.entities.components.Renderable;
import models.player.Player;
import views.inGame.Color;
import views.inGame.Renderer;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu {
    private final GameMenuController controller = new GameMenuController();
    @Override
    public void checker(Scanner scanner) {

        renderGame();

        if(App.getView().isRawMode()){
            int c = 0;
            try {
                if(App.getView().getTerminal().reader().peek(1000) > 0){
                    c = App.getView().getTerminal().reader().read();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            controller.handleRawInput((char) c);
        }else{
            String input = scanner.nextLine().trim();
            Matcher matcher;
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

<<<<<<< HEAD
            } else if (GameMenuCommands.ENERGY_SHOW.getMatcher(input) != null) {
                System.out.println(controller.energyShow());

            } else if ((matcher = GameMenuCommands.ENERGY_SET.getMatcher(input)) != null) {
                System.out.println(controller.energySet(Integer.parseInt(matcher.group(1))));

            } else if (GameMenuCommands.ENERGY_UNLIMITED.getMatcher(input) != null) {
                System.out.println(controller.energyUnlimited());

            } else if (GameMenuCommands.CHANGE_INPUT_TYPE.getMatcher(input) != null) {
                System.out.println(controller.switchInputType());
            } else {
                System.out.println("Invalid Command!");
            }
=======
        } else if ((matcher = GameMenuCommands.SET_WEATHER.getMatcher(input)) != null) {
            System.out.println(controller.setWeather(matcher.group("type")));

        } else if (GameMenuCommands.ENERGY_SHOW.getMatcher(input) != null) {
            System.out.println(controller.energyShow());

        } else if ((matcher = GameMenuCommands.ENERGY_SET.getMatcher(input)) != null) {
            System.out.println(controller.energySet(Integer.parseInt(matcher.group(1))));

        } else if (GameMenuCommands.ENERGY_UNLIMITED.getMatcher(input) != null) {
            System.out.println(controller.energyUnlimited());

        } else {
            System.out.println("Invalid Command!");
>>>>>>> Ali
        }

    }
    public void renderGame(){
        Game activeGame = App.getLoggedInAccount().getActiveGame();
        printMap(activeGame.getActiveMap());
        Position playerPosition = App.getLoggedInAccount().getActiveGame().getCurrentPlayer().getPosition();
        App.getView().getRenderer().mvAddchColored(App.getView().getTerminalWidth() / 2, App.getView().getTerminalHeight() / 2, '@', new Color(255, 255, 50));
        App.getView().getRenderer().render();
    }
    public void printMap(GameMap map) {
        Tile[][] tiles = map.getTiles();
        App.getView().getRenderer().clear();
        Position position = App.getLoggedInAccount().getActiveGame().getCurrentPlayer().getPosition();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile tile = tiles[i][j];
                Entity entity = tile.getContent();
                if(entity != null){
                    Renderable component = entity.getComponent(Renderable.class);
                    if(component == null){
                        throw new RuntimeException("Entity " + entity.getName() + " is on the ground, but it doesn't have a Renderable component");
                    }
                    App.getView().getRenderer().mvAddchColored(tile.getCol(), tile.getRow(), component.getCharacter(), component.getColor(), position.getCol(), position.getRow());
                }else{
                    App.getView().getRenderer().mvAddchColored(tile.getCol(), tile.getRow(), tile.getCharacter(), tile.getColor(), position.getCol(), position.getRow());
                }
            }
        }
    }
}
