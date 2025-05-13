package views;

import controllers.GameMenuController;
import models.*;
import models.Commands.GameMenuCommands;
import models.entities.Entity;
import models.entities.components.Pickable;
import models.entities.components.PositionComponent;
import models.entities.components.inventory.Inventory;
import models.entities.components.Renderable;
import models.entities.components.inventory.InventorySlot;
import models.enums.Direction;
import models.gameMap.GameMap;
import models.gameMap.MapRegion;
import models.gameMap.Tile;
import models.gameMap.WorldMap;
import models.player.Player;
import records.AnimalPurchaseDetails;
import records.Result;
import records.WalkProposal;
import views.inGame.Color;
import views.inGame.Renderer;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu implements AppMenu {
    private final GameMenuController controller = new GameMenuController();
    private Result previousResult = null;

    private enum MapRenderType{
        DEFAULT,
        REGIONS
    }
    MapRenderType mapRenderType = MapRenderType.DEFAULT;

    @Override
    public void checker(Scanner scanner) {
        renderGame();

        previousResult = null;
        if(App.getView().isRawMode()){
            int c = 0;
            try {
                if(App.getView().getTerminal().reader().peek(1000) > 0){
                    c = App.getView().getTerminal().reader().read();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            previousResult = controller.handleRawInput((char) c);
        }else {
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
                System.out.println(controller.cheatGiveItem(
                        matcher.group("name"),
                        Integer.parseInt(matcher.group("quantity"))
                ));

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

            } else if ((matcher = GameMenuCommands.ARTISAN_USE.getMatcher(input)) != null) {
                System.out.println(controller.useArtisan(matcher.group("artisanName"), matcher.group("itemName")));

            } else if ((matcher = GameMenuCommands.ARTISAN_GET.getMatcher(input)) != null) {
                System.out.println(controller.getArtisan(matcher.group("artisanName")));

            }
            else if ((matcher = GameMenuCommands.FERTILIZE.getMatcher(input)) != null ){
                System.out.println(controller.fertilize(matcher.group(1).trim(),matcher.group(2) ));

            } else if ((matcher = GameMenuCommands.TALK.getMatcher(input)) != null ) {
                System.out.println(controller.talk(matcher.group(1).trim(), matcher.group(2).trim()));

            } else if ((matcher = GameMenuCommands.TALK_HISTORY.getMatcher(input)) != null) {
                System.out.println(controller.talkHistory(matcher.group(1).trim()));

            } else if (GameMenuCommands.FRIENDSHIPS.getMatcher(input) != null) {
                System.out.println(controller.friendship());

            } else if ((matcher = GameMenuCommands.GIFT.getMatcher(input)) != null) {
                System.out.println(controller.giveGift(matcher.group(1).trim(), matcher.group(2).trim(),
                        Integer.parseInt(matcher.group(3))));

            } else if (GameMenuCommands.GIFT_LIST.getMatcher(input) != null) {
                System.out.println(controller.giftList());

            } else if ((matcher =GameMenuCommands.GIFT_RATE.getMatcher(input)) != null) {
                System.out.println(controller.giftRate(Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2))));

            } else if ((matcher = GameMenuCommands.GIFT_HISTORY.getMatcher(input)) != null) {
                System.out.println(controller.giftHistory(matcher.group(1).trim()));
                
            } else if ((matcher = GameMenuCommands.HUG.getMatcher(input)) != null) {
                System.out.println(controller.hug(matcher.group(1).trim()));

            } else if ((matcher = GameMenuCommands.FLOWER.getMatcher(input)) != null) {
                System.out.println(controller.flower(matcher.group(1).trim()));

            } else if ((matcher = GameMenuCommands.EAT_FOOD.getMatcher(input)) != null) {
                System.out.println(controller.eat(matcher.group("foodName")));

            }
            /* ------------------------------------------ Recipe Commands ------------------------------------------ */
            else if ((matcher = GameMenuCommands.CRAFTING_SHOW_RECIPES.getMatcher(input)) != null) {
                System.out.println(controller.showRecipes("crafting"));

            } else if ((matcher = GameMenuCommands.CRAFTING_CRAFT.getMatcher(input)) != null) {
                System.out.println(controller.craftingCraft(matcher.group("itemName")));

            } else if ((matcher = GameMenuCommands.COOKING_SHOW_RECIPES.getMatcher(input)) != null) {
                System.out.println(controller.showRecipes("cooking"));

            } else if ((matcher = GameMenuCommands.COOKING_PREPARE.getMatcher(input)) != null) {
                System.out.println(controller.cookingPrepare(matcher.group("recipeName")));

                System.out.println();
            }
            /* -------------------------------------------------- -------------------------------------------------- */

            /* ------------------------------------------- NPC Commands -------------------------------------------- */
            else if ((matcher = GameMenuCommands.MEET_NPC.getMatcher(input)) != null) {
                System.out.println(controller.meetNPC(matcher.group(1).trim()));

            } else if ((matcher = GameMenuCommands.GIFT_NPC.getMatcher(input)) != null) {
                System.out.println(controller.giftNPC(matcher.group(1).trim(), matcher.group(2).trim()));

            } else if ((matcher = GameMenuCommands.FRIENDSHIP_NPC.getMatcher(input)) != null) {
                System.out.println(controller.friendshipNPC());

            } else if ((matcher = GameMenuCommands.QUEST_LIST.getMatcher(input)) != null) {
                System.out.println(controller.questList());

            } else if ((matcher = GameMenuCommands.QUEST_FINISH.getMatcher(input)) != null) {
                System.out.println(controller.questFinish(Integer.parseInt(matcher.group(1).trim())));
            }
            /* -------------------------------------------------- -------------------------------------------------- */

            /* ------------------------------------------- Animal Commands ----------------------------------------- */
            else if ((matcher = GameMenuCommands.BUILD_ANIMAL.getMatcher(input)) != null) {

            } else if ((matcher = GameMenuCommands.BUY_ANIMAL.getMatcher(input)) != null) {
                AnimalPurchaseDetails details = controller.buyAnimal(matcher.group(1).trim(), matcher.group(2).trim());
                System.out.println(details.message());
                if (details.canBuy()) {
                    String chosenName = scanner.nextLine();
                    System.out.println(controller.chooseHouseForAnimal(details, chosenName));
                }

            } else if ((matcher = GameMenuCommands.PET_ANIMAL.getMatcher(input)) != null) {
                System.out.println(controller.pet(matcher.group(1).trim()));

            } else if ((matcher = GameMenuCommands.SET_ANIMAL_FRIENDSHIP.getMatcher(input)) != null) {
                System.out.println(controller.setAnimalFriendship(matcher.group(1).trim(),
                        Integer.parseInt(matcher.group(2))));

            } else if (GameMenuCommands.ANIMAL_INFO.getMatcher(input) != null) {
                System.out.println(controller.animals());

            } else if ((matcher = GameMenuCommands.SHEPHERD_ANIMAL.getMatcher(input)) != null) {
                System.out.println(controller.shepherdAnimal(matcher.group(1).trim()));

            } else if ((matcher = GameMenuCommands.FEED_HAY.getMatcher(input)) != null) {
                System.out.println(controller.feedHay(matcher.group(1).trim()));

            } else if ((matcher = GameMenuCommands.PRODUCES.getMatcher(input)) != null) {
                System.out.println(controller.showProduces());

            } else if ((matcher = GameMenuCommands.COLLECT_PRODUCE.getMatcher(input)) != null) {
                System.out.println(controller.collectProduces(matcher.group(1).trim()));


            } else if ((matcher = GameMenuCommands.SELL_ANIMAL.getMatcher(input)) != null) {
                System.out.println(controller.sellAnimal(matcher.group(1).trim()));

            } else if ((matcher = GameMenuCommands.FISHING.getMatcher(input)) != null) {
                System.out.println(controller.fishing(matcher.group(1).trim()));

            }
            /* ------------------------------------------- cheat Commands ------------------------------------------ */
            else if ((matcher = GameMenuCommands.CHEAT_SKILL.getMatcher(input)) != null){
                System.out.println(controller.cheatAddSkill(matcher.group(1).trim(),
                        Integer.parseInt(matcher.group(2).trim())));

            } else if ((matcher = GameMenuCommands.SKILL_STATUE.getMatcher(input)) != null) {
                System.out.println(controller.skillStatue());

            }



            /* ------------------------------------------- Shop Commands ------------------------------------------- */
            else if ((matcher = GameMenuCommands.SHOW_ALL_AVAILABLE.getMatcher(input)) != null) {
                System.out.println(controller.showAvailableProducts());
            } else if ((matcher = GameMenuCommands.SHOW_ALL_PRODUCTS.getMatcher(input)) != null) {
                System.out.println(controller.showAllProducts());
            } else if ((matcher = GameMenuCommands.PURCHASE.getMatcher(input)) != null) {
                handlePurchase(
                        matcher.group("productName"),
                        matcher.group("count"),
                        scanner
                );
            }
            /* -------------------------------------------------- -------------------------------------------------- */

            else if ((matcher = GameMenuCommands.START_TRADE.getMatcher(input)) != null) {
                System.out.println(controller.startTrade());

            } else if ((matcher = GameMenuCommands.CHEAT_THOR.getMatcher(input)) != null) {
                System.out.println(controller.thor(Integer.parseInt(matcher.group(1)),
                        Integer.parseInt(matcher.group(2))));

            } else if ((matcher = GameMenuCommands.ASK_MARRIAGE.getMatcher(input)) != null) {
                System.out.println(controller.askMarriage(matcher.group(1).trim(), matcher.group(2).trim()));

            } else if ((matcher = GameMenuCommands.RESPOND.getMatcher(input)) != null) {
                System.out.println(controller.respond(matcher.group(1).trim(), matcher.group(2).trim()));

            } else if ((matcher = GameMenuCommands.CHANGE_MAP_RENDER.getMatcher(input)) != null) {
                this.mapRenderType = MapRenderType.values()[(mapRenderType.ordinal() + 1) % MapRenderType.values().length];
            } else if ((matcher = GameMenuCommands.CHEAT_BUILD_BUILDING.getMatcher(input)) != null) {
                System.out.println(controller.cheatBuildBuilding(Integer.parseInt(matcher.group("x")), Integer.parseInt(matcher.group("y")),
                                                                                    matcher.group("name") ,matcher.group("force") != null));
            } else if ((matcher = GameMenuCommands.ADD_MONEY.getMatcher(input)) != null) {
                System.out.println(controller.addMoney(Integer.parseInt(matcher.group(1).trim())));

            } else {
                System.out.println("Invalid Command!");
            }
        }

    }
    public void renderGame(){
        Game activeGame = App.getActiveGame();
        if(activeGame.isMapVisible()){
            printMap(activeGame.getActiveMap());
            App.getView().getRenderer().render();
            App.getView().getRenderer().moveCurser(0, 0);

            Player player = activeGame.getCurrentPlayer();
            showInventory(player.getComponent(Inventory.class));
            System.out.println("energy: " + player.getEnergy().getAmount());

            Position position = player.getPosition();
            System.out.println("position: " + position + " " + position.getMap());
            System.out.println("money: " + player.getWallet().getBalance());

            if(previousResult != null){
                System.out.println(previousResult);
            }
        }
    }
    public void printMap(GameMap map) {
        Tile[][] tiles = map.getTiles();
        App.getView().getRenderer().clear();
        Position position = App.getActiveGame().getCurrentPlayer().getPosition();
        Renderer renderer = App.getView().getRenderer();
        switch (mapRenderType){
            case DEFAULT -> {
                for (Tile[] value : tiles) {
                    for (Tile tile : value) {
                        if(tile.getType() == null) continue;
                        Entity entity = tile.getContent();
                        if (entity != null) {
                            Renderable component = entity.getComponent(Renderable.class);
                            if (component == null) {
                                throw new RuntimeException("Entity " + entity.getEntityName() + " is on the ground, but it doesn't have a Renderable component");
                            }
                            renderer.mvAddchColored(tile.getCol(), tile.getRow(), component.getCharacter(), component.getColor(), position);
                        } else {
                            renderer.mvAddchColored(tile.getCol(), tile.getRow(), tile.getCharacter(), tile.getColor(), position);
                        }
                    }
                }
                for(Entity e : map.getEntities()){
                    PositionComponent positionComponent = e.getComponent(PositionComponent.class);

                    if(e.getComponent(Renderable.class) != null){
                        renderer.mvAddchColored(positionComponent.getCol(), positionComponent.getRow(),
                                                e.getComponent(Renderable.class).getCharacter(),
                                                e.getComponent(Renderable.class).getColor(),
                                                position);
                    }
                }
            }
            case REGIONS -> {
                if(!(map instanceof WorldMap)){
                    break;
                }

                WorldMap map1 = (WorldMap) map;
                for (Tile[] value : tiles) {
                    for (Tile tile : value) {
                        if(tile.getRegion() != null){
                            renderer.mvAddchColored(tile.getCol(), tile.getRow(), '0', tile.getRegion().getColor(), position);
                        }
                    }
                }
                for(MapRegion r : map1.getRegions()){
                    renderer.mvPrint(r.getCenter().getCol(), r.getCenter().getRow(), r.getName(), Color.WHITE, position);
                    if(r.getOwner() != null){
                        renderer.mvPrint(r.getCenter().getCol(), r.getCenter().getRow() + 1, r.getOwner().getAccount().getNickname(), Color.WHITE, position);
                    }else{
                        renderer.mvPrint(r.getCenter().getCol(), r.getCenter().getRow() + 1, "no owner", Color.WHITE, position);
                    }
                }
            }
        }
    }

    private void handlePurchase(String productName, String count, Scanner scanner) {
        Pattern pattern = Pattern.compile(".+?(-?\\d+)[,\\s]+(-?\\d+).+");
        Result result = controller.purchase(productName, count);
        if(result.isSuccessful() && result.message() == null) {
            System.out.println("enter x and y to build " + productName);
            String input = scanner.nextLine().trim();
            Matcher matcher = pattern.matcher(input);
            if(!matcher.matches()) {
                System.out.println("Invalid input! build canceled");
                return;
            }
            int x = Integer.parseInt(matcher.group(1));
            int y = Integer.parseInt(matcher.group(2));
            System.out.println(controller.build(x, y, productName));
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
                System.out.printf("%s \t%d", entity.getEntityName(), entity.getComponent(Pickable.class).getStackSize());
            }else{
                System.out.print("-");
            }
            if(App.getActiveGame().getCurrentPlayer().getActiveSlot() == s){
                System.out.print(" <active>");
            }
            System.out.print("\n");
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
