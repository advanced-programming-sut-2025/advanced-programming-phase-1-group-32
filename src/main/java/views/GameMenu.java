package views;

import controllers.GameMenuController;
import models.App;
import models.Commands.GameMenuCommands;
import models.Game;
import models.GameMap;
import models.Tile;
import views.inGame.Renderer;

import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;

public class GameMenu implements AppMenu{

    @Override
    public void checker(Scanner scanner) {
        Game activeGame = App.getLoggedInAccount().getActiveGame();
        //
        printMap(activeGame.getActiveMap());

        String input = scanner.nextLine().trim();
        Matcher matcher;
        GameMenuController controller = new GameMenuController();

        if (GameMenuCommands.DATE.getMatcher(input) != null) {
            System.out.println(controller.getDate());
        } else if (GameMenuCommands.DATETIME.getMatcher(input) != null) {
            System.out.println(controller.getDateTime());
        } else if (GameMenuCommands.TIME.getMatcher(input) != null) {
            System.out.println(controller.getTime());
        } else if (GameMenuCommands.DAT_OF_THE_WEEK.getMatcher(input) != null) {
            System.out.println(controller.getDayOfTheWeek());
        }


    }

    public void printMap(GameMap map) {
        Renderer renderer = new Renderer();
        Tile[][] tiles = map.getTiles();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                char ch = '#';
                Tile tile = tiles[i][j];
                renderer.clear();
                renderer.mvAddchColored(tile.getCol(), tile.getRow(), ch, tile.getColor());

            }
        }

    }
}
