package views;

import models.App;
import models.Game;
import models.GameMap;
import models.Tile;
import views.inGame.Renderer;

import java.util.Scanner;

public class GameMenu implements AppMenu{

    @Override
    public void checker(Scanner scanner) {
        Game activeGame = App.getLoggedInAccount().getActiveGame();
        //
        printMap(activeGame.getActiveMap());
        String input = scanner.nextLine().trim();


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
