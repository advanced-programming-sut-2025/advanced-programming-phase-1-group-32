package views;

import com.fasterxml.jackson.databind.deser.impl.InnerClassProperty;
import controllers.GameMenuController;
import models.*;
import models.Commands.GameMenuCommands;
import models.entities.Entity;
import models.entities.components.Pickable;
import models.entities.components.inventory.Inventory;
import models.entities.components.Renderable;
import models.entities.components.inventory.InventorySlot;
import models.enums.Direction;
import models.player.Player;
import records.Result;
import records.WalkProposal;
import views.inGame.Color;

import java.io.IOException;
import java.util.Map;
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

            } else if ((matcher = GameMenuCommands.GREEN_HOUSE_BUILD.getMatcher(input)) != null) {
                System.out.println(/*TODO*/);

            } else if ((matcher = GameMenuCommands.WALK.getMatcher(input)) != null) {
                int x = Integer.parseInt(matcher.group("x"));
                int y = Integer.parseInt(matcher.group("y"));
                handleWalk(x, y, scanner);

            } else if (GameMenuCommands.ENERGY_SHOW.getMatcher(input) != null) {
                System.out.println(controller.energyShow());

            } else if ((matcher = GameMenuCommands.ENERGY_SET.getMatcher(input)) != null) {
                System.out.println(controller.energySet(Integer.parseInt(matcher.group(1))));

            } else if (GameMenuCommands.ENERGY_UNLIMITED.getMatcher(input) != null) {
                System.out.println(controller.energyUnlimited());

            } else if (GameMenuCommands.CHANGE_INPUT_TYPE.getMatcher(input) != null) {
                System.out.println(controller.switchInputType());

            } else if ((matcher = GameMenuCommands.CRAFTINFO.getMatcher(input)) != null) {
                System.out.println(controller.craftInfo(matcher.group(1).trim()));

            } else if ((matcher = GameMenuCommands.TOGGLE_MAP.getMatcher(input)) != null) {
                System.out.println(controller.toggleMap());

            } else if ((matcher = GameMenuCommands.SHOW_INVENTORY.getMatcher(input)) != null) {
                showInventory(App.getActiveGame().getCurrentPlayer().getComponent(Inventory.class));
            }else if ((matcher = GameMenuCommands.CHEAT_GIVE_ITEM.getMatcher(input)) != null) {
                System.out.println(controller.cheatGiveItem(matcher.group("name"), Integer.parseInt(matcher.group("quantity"))));
            } else if ((matcher = GameMenuCommands.PLANT_SEED.getMatcher(input)) != null) {
                System.out.println(controller.plant(matcher.group(1), matcher.group(2)));
            } else if ((matcher = GameMenuCommands.SHOW_PLANT.getMatcher(input)) != null) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                System.out.println(controller.showPlant(x, y));
            } else if ((matcher = GameMenuCommands.NEXT_TURN.getMatcher(input)) != null) {
                System.out.println(controller.nextTurn());
            } else if (input.startsWith("tools")) {
                toolsCommandParser(input);
            } else {
                System.out.println("Invalid Command!");
            }
        }

    }
    public void renderGame(){
        Game activeGame = App.getActiveGame();
        if(activeGame.isMapVisible()){
            printMap(activeGame.getActiveMap());
            Position playerPosition = App.getActiveGame().getCurrentPlayer().getPosition();
            for(Player p : activeGame.getPlayers()){
                App.getView().getRenderer().mvAddchColored(p.getPosition().getCol(), p.getPosition().getRow(), '@', new Color(255, 255, 50), playerPosition);
            }
            App.getView().getRenderer().render();
            App.getView().getRenderer().moveCurser(0, 0);
            showInventory(App.getActiveGame().getCurrentPlayer().getComponent(Inventory.class));
        }
    }
    public void printMap(GameMap map) {
        Tile[][] tiles = map.getTiles();
        App.getView().getRenderer().clear();
        Position position = App.getActiveGame().getCurrentPlayer().getPosition();
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[i].length; j++) {
                Tile tile = tiles[i][j];
                Entity entity = tile.getContent();
                if(entity != null){
                    Renderable component = entity.getComponent(Renderable.class);
                    if(component == null){
                        throw new RuntimeException("Entity " + entity.getName() + " is on the ground, but it doesn't have a Renderable component");
                    }
                    App.getView().getRenderer().mvAddchColored(tile.getCol(), tile.getRow(), component.getCharacter(), component.getColor(), position);
                }else{
                    App.getView().getRenderer().mvAddchColored(tile.getCol(), tile.getRow(), tile.getCharacter(), tile.getColor(), position);
                }
            }
        }
    }

    private void handleWalk(int x, int y, Scanner scanner) {
        WalkProposal proposal = controller.proposeWalk(x, y);
        if(!proposal.isAllowed()) {
            System.out.println(proposal.message());
            return;
        }
        System.out.printf(
                "It will cost %d energy, Proceed? (y/n)%n",
                proposal.energyCost()
        );
        String ans = scanner.nextLine().trim().toLowerCase();
        if(ans.startsWith("y")) {
            controller.executeWalk(proposal);
        }
        else {
            System.out.println("Walk cancelled");
        }
    }
    public void showInventory(Inventory inventory){
        int i = 1;
        for(InventorySlot s : inventory.getSlots()){
            Entity entity = s.getEntity();
            System.out.printf("%-2d: ", i);
            if(entity != null){
                System.out.printf("%s \t%d\n", entity.getName(), entity.getComponent(Pickable.class).getStackSize());
            }else{
                System.out.print("-\n");
            }
            i++;
        }
    }

    public void toolsCommandParser(String input) {
        Player player = App.getLoggedInAccount().getActiveGame().getCurrentPlayer();
        Matcher matcher;
        if((matcher = GameMenuCommands.TOOLS_EQUIP.getMatcher(input)) != null) {
            System.out.println(controller.toolsEquip(matcher.group("toolName")));
        } else if((matcher = GameMenuCommands.TOOLS_SHOW_CURRENT.getMatcher(input)) != null) {
            System.out.println(controller.toolsShowCurrent());
        } else if((matcher = GameMenuCommands.TOOLS_AVAILABLE.getMatcher(input)) != null) {
            System.out.println(controller.toolsShowAvailable());
        } else if((matcher = GameMenuCommands.TOOLS_UPGRADE.getMatcher(input)) != null) {
            System.out.println(controller.toolsUpgrade(/*TODO*/));
        } else if((matcher = GameMenuCommands.TOOLS_USE.getMatcher(input)) != null) {
            System.out.println(controller.toolsUse(Direction.getDirection(Integer.parseInt(matcher.group("direction")))));

        } else {
            System.out.println("Invalid Command!");
        }
    }
}
