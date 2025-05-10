package controllers;

import models.*;
import models.Date;
import models.NPC.NPC;
import models.NPC.NpcFriendship;
import models.NPC.Quest;
import models.crafting.Recipe;
import models.crafting.RecipeType;
import models.entities.Entity;
import models.entities.components.*;
import models.enums.Direction;
import models.enums.EntityTag;
import models.enums.TileType;
import models.enums.*;
import models.entities.components.inventory.Inventory;
import models.entities.components.inventory.InventorySlot;
import models.enums.Weather;
import models.gameMap.GameMap;
import models.player.Energy;
import models.player.Gift;
import models.player.Message;
import models.player.Player;
import models.player.*;
import models.player.friendship.PlayerFriendship;
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
        Game game = App.getActiveGame();
        ArrayList<Player> players = game.getPlayers();
        Player currentPlayer = game.getCurrentPlayer();
        int index = players.indexOf(game.getCurrentPlayer());

        if (index == game.getPlayers().size() - 1) {
             game.setCurrentPlayer(players.get(0));
            advanceTime(1);
        } else {
            game.setCurrentPlayer(players.get(index + 1));
        }

        StringBuilder message = new StringBuilder();
        message.append("You are playing as ").append(game.getCurrentPlayer().getAccount().getNickname());
        message.append("\n").append(game.getCurrentPlayer().newMessages());

        return new Result(true, message.toString());
    }

    public Result getTime() {
        Date currentDate = App.getActiveGame().getDate();

        return new Result(true, String.valueOf(currentDate.getHour()));
    }

    public Result getDate() {
        Date currentDate = App.getActiveGame().getDate();

        return new Result(true, String.valueOf(currentDate.getDay()));
    }

    public Result getDateTime() {
        Date currentDate = App.getActiveGame().getDate();
        return new Result(true, "Date: " + currentDate.getDay() +
                "\tHour: " + currentDate.getHour());
    }

    public Result getDayOfTheWeek() {
        Date currentDate = App.getActiveGame().getDate();

        return new Result(true, currentDate.getWeekDay().toString().toLowerCase());
    }

    // dont add large number to this
    public Result advanceTime(int amount) {
        Game game = App.getActiveGame();
        Date date = game.getDate();

        // this function will update data about game.Date
        date.addHour(amount, game);
        game.setDate(date);

        return new Result(true, "We've traveled through time for " + amount + " hours!");
    }

    public Result advanceDate(int amount) {
        Game game = App.getActiveGame();
        Date date = game.getDate();

        // this function will update data about game.Date
        date.addDay(amount, game);
        game.setDate(date);
        return new Result(true, "We've traveled through time for " + amount + " days");
    }

    public Result showSeason() {
        Date currentDate = App.getActiveGame().getDate();
        return new Result(true, currentDate.getSeason().name().toLowerCase());
    }

    public void checkTurn() {
        //TODO
        //ghash
    }

    public Result thor(int x, int y) {
        Position position = new Position(x, y);
        Game game = App.getActiveGame();
        Tile tile = game.getActiveMap().getTileByPosition(position);

        game.thorTile(tile);

        return new Result(true, "tile at position " + x + ", " + y + " thord successfully!");
    }

    public Result showWeather() {
        Weather weather = App.getActiveGame().getTodayWeather();
        return new Result(true, weather.toString().toLowerCase());
    }

    public Result weatherForecast() {
        Weather weather = App.getActiveGame().getTomorrowWeather();

        return new Result(true, weather.toString().toLowerCase());
    }

    public Result setWeather(String weatherString) {
        Weather weather = Weather.getweather(weatherString);
        if (weather == null) {
            return new Result(false, "Weather not found");
        }

        Game game = App.getActiveGame();
        game.setTomorrowWeather(weather);
        return new Result(true, "Tomorrow's weather changed to " + weather.name().toLowerCase());
    }

    public WalkProposal proposeWalk(int x, int y) {
        Game game = App.getActiveGame();
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
        if(!p.isAllowed()) {
            return new Result(false, "No walk was proposed");
        }
        player.setPosition(new Position(p.y(), p.x()));
        player.reduceEnergy(p.energyCost());
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

    public Result helpReadingMap() {
        //TODO
        return null;
    }

    public Result energyShow() {
        Game game = App.getActiveGame();
        Player player = game.getCurrentPlayer();
        Energy energy = player.getEnergy();


        return new Result(true, "energy left: " + energy.getAmount());
    }

    public Result energySet(int amount) {
        Game game = App.getActiveGame();
        Player player = game.getCurrentPlayer();
        Energy energy = player.getEnergy();

        energy.setAmount(amount);
        return new Result(true, "energy set to " + energy.getAmount());
    }

    public Result energyUnlimited() {
        Game game = App.getActiveGame();
        Player player = game.getCurrentPlayer();
        Energy energy = player.getEnergy();

        energy.toggleUnlimited();

        return new Result(true, "energy unlimited: " + energy.isUnlimited());
    }

    public Result inventoryTrash() {
        //TODO
        return null;
    }

    /* ---------------------------------- Tools ---------------------------------- */
    public Result toolsEquip(String toolName) {
        Player player = App.getLoggedInAccount().getActiveGame().getCurrentPlayer();
        InventorySlot slot = player.getComponent(Inventory.class).getSlot(toolName);
        if(slot == null)
            return new Result(false, "This tool doesn't exist in inventory");
        player.setActiveSlot(slot);
        return new Result(true, "Tool equipped successfully");
    }

    public Result toolsShowCurrent() {
        Player player = App.getLoggedInAccount().getActiveGame().getCurrentPlayer();
        Entity active = player.getActiveSlot().getEntity();
        if(active == null || !active.hasTag(EntityTag.TOOL))
            return new Result(false, "This is not a tool");
        return new Result(true, active.getName());

    }

    public Result toolsShowAvailable() {
        Player player = App.getLoggedInAccount().getActiveGame().getCurrentPlayer();
        ArrayList<Entity> tools = player.getComponent(Inventory.class).getItemsByTag(EntityTag.TOOL);
        if (tools.isEmpty())
            return new Result(false, "There is no tool in Backpack");
        StringBuilder sb  = new StringBuilder("Tools available:");
        for (Entity tool : tools) {
            sb.append("\n").append(tool.getName());
        }
        return new Result(true, sb.toString());

    }

    public Result toolsUpgrade() {
        //TODO: workshop needed
        return null;
    }

    public Result toolsUse(Direction dir) {
        Position playerPosition = App.getLoggedInAccount().getActiveGame().getCurrentPlayer().getPosition();
        GameMap map = App.getLoggedInAccount().getActiveGame().getActiveMap();
        if(App.getLoggedInAccount().getActiveGame().getCurrentPlayer().getActiveSlot() == null){
            return new Result(false, "nothing equipped");
        }
        Entity tool = App.getLoggedInAccount().getActiveGame().getCurrentPlayer().getActiveSlot().getEntity();

        if(
                dir == null
                || playerPosition.getRow() + dir.dy > map.getHeight() || playerPosition.getRow() + dir.dy < 0
                || playerPosition.getCol() + dir.dx > map.getWidth() || playerPosition.getCol() + dir.dx < 0
        )
            return new Result(false, "Invalid Direction");
        Position position = new Position(playerPosition.getRow() + dir.dy, playerPosition.getCol() + dir.dx);
        if(tool == null || (!tool.getTags().contains(EntityTag.TOOL)))
            return  new Result(false, "You should equip a tool first");
        return tool.getComponent(Useable.class).use(map.getTileByPosition(position));
    }
    /* --------------------------------------  -------------------------------------- */

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

        StringBuilder message = new StringBuilder();
        message.append("Name: ").append(crop.getName()).append("\n").
                append("Source: ").append(growable.getSeed()).append("\n")
                .append("Stages: ").append(growable.getStages()).append("\n")
                .append("Total Harvest Time: ").append(growable.getTotalHarvestTime()).append("\n")
                .append("One Time: ").append(growable.isOneTime()).append("\n");

        if (growable.getRegrowthTime() > 0) {
            message.append("Regrowth Time: ").append(growable.getRegrowthTime()).append("\n");
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
        Game game = App.getActiveGame();
        Player player = game.getCurrentPlayer();
        if (!App.entityRegistry.doesEntityExist(seedString)) {
            return new Result(false, "There is no seed with name" + seedString);
        }

        Entity seedDetails = App.entityRegistry.getEntityDetails(seedString);

        if (!seedDetails.hasTag(EntityTag.SEED)) {
            return new Result(false, "There is no seed with name" + seedString);
        }

        Position position = player.getPosition().changeByDirection(direction);
        if (position == null) {
            return new Result(false, "type a valid direction");
        }
        if(!player.getComponent(Inventory.class).doesHaveItem(seedDetails)){
            return new Result(false, "you don't have that seed");
        }

        Entity seed = player.getComponent(Inventory.class).takeFromInventory(seedDetails, 1);

        Tile tile = game.getActiveMap().getTileByPosition(position);

        if (tile == null || !tile.getType().equals(TileType.PLOWED)) {
            return new Result(false, "tile is unavailable for planting");
        }

//        Entity plant = App.entityRegistry.makeEntity(seed.getComponent(SeedComponent.class).getGrowingPlant());
        tile.plant(seed);
        return new Result(true, "planted succusfully");
    }

    public Result showPlant(int col, int row) {
        Position position = new Position(row, col);
        Game game = App.getActiveGame();
        GameMap gameMap = game.getActiveMap();
        Tile tile = gameMap.getTileByPosition(position);

        if (tile == null) {
            return new Result(false, "No tile found");
        }

        Entity plantedEntity = tile.getContent();
        if (plantedEntity == null ||
                !(plantedEntity.hasTag(EntityTag.TREE) || plantedEntity.hasTag(EntityTag.CROP))) {
            return new Result(false, "Tile is not a planted ground");
        }


        StringBuilder message = new StringBuilder();
        message.append("Name: ").append(plantedEntity.getName()).append("\n");
        message.append(plantedEntity.getComponent(Growable.class).getInfo());
        return new Result(true, message.toString());
    }

    public Result fertilize(String fertilizerString, String direction) {
        // get the fertilizer
        if (!App.entityRegistry.doesEntityExist(fertilizerString)) {
            return new Result(false, "There is no fertilizer with name" + fertilizerString);
        }
        //TODO: get fertilizer

        Game game = App.getLoggedInAccount().getActiveGame();
        Player player = game.getCurrentPlayer();

        // get the position
        Position position = player.getPosition().changeByDirection(direction);
        if (position == null) {
            return new Result(false, "type a valid direction");
        }
        Tile tile = game.getActiveMap().getTileByPosition(position);
        if (tile == null) {
            return new Result(false, "No tile found");
        }

        // check existence of a plant
        if (tile.getType() != TileType.PLANTED_GROUND) {
            return new Result(false, "Tile is not a planted ground");
        }

        // TODO: its effects :)

        // Fertilize!
        Entity plantedEntity = tile.getContent();
        plantedEntity.getComponent(Growable.class).setFertilized(true);
        return new Result(true, "fertilized successfully!");
    }

    public Result checkWater(){
        //TODO
        return null;
    }

    public Result showRecipes(String recipeType) {
        RecipeType type = RecipeType.fromName(recipeType);
        Player activePlayer = App.getActiveGame().getCurrentPlayer();

        ArrayList<Recipe> recipes = activePlayer.getUnlockedRecipes();
        StringBuilder sb = new StringBuilder();
        for (Recipe recipe : recipes) {
            if(recipe.getType().equals(type))
                sb.append(recipe);
        }
        return new Result(true, sb.toString());
    }

    /*
    private Result craftRecipe(String recipeName){
        Player player = App.getActiveGame().getCurrentPlayer();
        Recipe recipe = App.recipeRegistry.getRecipe(recipeName);
        if(recipe == null)
            return new Result(false, "Invalid recipe name");

        if(!player.hasRecipe(recipe))
            return new Result(false, "You didn't unlocked this recipe");
        if(!recipe.canCraft(player.getComponent(Inventory.class)))
            return new Result(false, "you don't have enough items");
        Entity output = recipe.craft(player.getComponent(Inventory.class));
        //TODO: if inventory is full drop output on ground
        Result result = player.getComponent(Inventory.class).addItem(output);
        if(!result.isSuccessful())
            return new Result(false, "something wrong happened!");
        return new Result(true, recipeName + " added to your inventory successfully");

    }
    *
     */
    public Result craftingCraft(String recipeName) {
        Player player = App.getActiveGame().getCurrentPlayer();
        Recipe recipe = App.recipeRegistry.getRecipe(recipeName);
        if(recipe == null)
            return new Result(false, "Invalid recipe name");
        if(!player.hasRecipe(recipe))
            return new Result(false, "You didn't unlocked this recipe");
        if(!recipe.getType().equals(RecipeType.CRAFTING))
            return new Result(false, "this is not a crafting recipe!");
        if(!recipe.canCraft(player.getComponent(Inventory.class)))
            return new Result(false, "you don't have enough items");
        Entity output = recipe.craft(player.getComponent(Inventory.class));
        //TODO: if inventory is full drop output on ground
        Result result = player.getComponent(Inventory.class).addItem(output);
        if(!result.isSuccessful())
            return new Result(false, "something wrong happened!");

        App.getActiveGame().getCurrentPlayer().reduceEnergy(2);
        return new Result(true, recipeName + " crafted and added to your inventory successfully");
    }

    public Result placeItem() {
        //TODO
        return null;
    }

    public Result addItem() {
        //TODO
        return null;
    }

    public Result refrigerator() {
        //TODO
        return null;
    }

    public Result cookingPrepare(String recipeName) {
        //TODO: it should handle player inventory and refrigerator inventory

        Player player = App.getActiveGame().getCurrentPlayer();
        Recipe recipe = App.recipeRegistry.getRecipe(recipeName);
        if(recipe == null)
            return new Result(false, "Invalid recipe name");
        if(!player.hasRecipe(recipe))
            return new Result(false, "You didn't unlocked this recipe");
        if(!recipe.getType().equals(RecipeType.COOKING))
            return new Result(false, "this is not a cooking recipe!");
        if(!recipe.canCraft(player.getComponent(Inventory.class)))
            return new Result(false, "you don't have enough items");
        Entity output = recipe.craft(player.getComponent(Inventory.class));
        //TODO: if inventory is full drop output on ground
        Result result = player.getComponent(Inventory.class).addItem(output);
        if(!result.isSuccessful())
            return new Result(false, "something wrong happened!");

        App.getActiveGame().getCurrentPlayer().reduceEnergy(3);
        return new Result(true, recipeName + " cooked and added to your inventory successfully");

    }

    public Result eat(String foodName) {
        //TODO: buff
        Player player = App.getActiveGame().getCurrentPlayer();
        Inventory inventory = player.getComponent(Inventory.class);
        Entity food = inventory.getItem(foodName);
        if (food == null)
            return new Result(false, "You don't have " + foodName);
        Edible edible = food.getComponent(Edible.class);
        if(edible == null)
            return new Result(false, "This is not Edible");
        player.reduceEnergy(-edible.getEnergy());
        inventory.takeFromInventory(food, 1);
        return new Result(true, "eated " + foodName + " successfully");
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
        Game game = App.getActiveGame();
        ArrayList<PlayerFriendship> playerFriendships = game.getCurrentPlayerFriendships();
        String message = PlayerFriendship.buildFriendshipMessage(game.getCurrentPlayer(), playerFriendships);

        return new Result(true, message);
    }

    public Result giveGift(String playerName, String itemName, int amount) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player giftedPlayer = game.findPlayer(playerName);

        if (giftedPlayer == null) {
            return new Result(false, "Player not found");
        }

        if (giftedPlayer == currentPlayer) {
            return new Result(false, "you can't gift yourself!");
        }

        if (!game.checkPlayerDistance(currentPlayer, giftedPlayer)) {
            return new Result(false, giftedPlayer.getName() + " is out of distance");
        }

        if (!App.entityRegistry.doesEntityExist(itemName)) {
            return new Result(false, "Item not found");
        }

        if (game.getFriendshipWith(giftedPlayer).getLevel() == 0) {
//            return new Result(false, "Don't be cousin too soon:)");
        }

        Entity item = App.entityRegistry.makeEntity(itemName);
        if (item.getComponent(Pickable.class) == null) {
            return new Result(false, "You can't gift this item!");
        }
        item.getComponent(Pickable.class).setStackSize(amount);

        //TODO: check is there enough of the item and reduce
//        if (!currentPlayer.getComponent(Inventory.class).doesHaveItem(itemName, amount)) {
//            return new Result(false, "You don't have " + amount + " items!");
//        }
//        currentPlayer.getComponent(Inventory.class).removeItem(itemName, amount);

        Gift gift = new Gift(currentPlayer, giftedPlayer, item, game.getDate());
        giftedPlayer.receiveGift(gift);


        return new Result(true, "You gave gift to " + giftedPlayer.getUsername());
    }

    public Result giftList() {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        StringBuilder message = new StringBuilder("Your gift list:\n");

        for (Gift gift : currentPlayer.getGiftLog()) {
            message.append(gift.toString());
        }

        return new Result(true, message.toString());
    }

    public Result giftRate(int giftNumber, int rating) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        Gift gift = currentPlayer.findGift(giftNumber);

        if (gift == null) {
            return new Result(false, "Gift not found");
        }

        if (gift.getRating() != -1) {
            return new Result(false, "You have already rated this gift.");
        }

        if (rating < 1 || rating > 5) {
            return new Result(false, "Rating must be between 1 and 5");
        }

        gift.setRating(rating);

        PlayerFriendship playerFriendship = game.getFriendshipWith(gift.getSender());
        if (rating < 3) playerFriendship.reduceXp((3 - rating) * 30 - 15);
        else playerFriendship.addXp((rating - 3) * 30 + 15);

        return new Result(true, "Rated successfully!");
    }

    public Result giftHistory(String username) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player giftedPlayer = game.findPlayer(username);
        if (giftedPlayer == null) {
            return new Result(false, "Player not found");
        }

        if (giftedPlayer == currentPlayer) {
            return new Result(false, ":/");
        }

        StringBuilder message = new StringBuilder("Your gift history with ");
        message.append(giftedPlayer.getUsername()).append(":\n");

        ArrayList<Gift> gifts = new ArrayList<>();
        for (Gift gift : currentPlayer.getGiftLog()) {
            if (gift.getSender().equals(giftedPlayer)) {
                gifts.add(gift);
            }
        }

        for (Gift gift : giftedPlayer.getGiftLog()) {
            if (gift.getSender().equals(giftedPlayer)) {
                gifts.add(gift);
            }
        }

        for (Gift gift : gifts) {
            message.append(gift.getGiftDetail()).append("\n");
        }


        return new Result(true, message.toString());
    }

    public Result hug(String username) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player huggedPlayer = game.findPlayer(username);
        if (huggedPlayer == null) {
            return new Result(false, "Player not found");
        }

        if (huggedPlayer == currentPlayer) {
            return new Result(false, "You can't hug yourself!");
        }

        if (!game.checkPlayerDistance(huggedPlayer, currentPlayer)) {
            return new Result(false, "You can't hug from this distance!");
        }

        PlayerFriendship playerFriendship = game.getFriendshipWith(huggedPlayer);

        if(playerFriendship.getLevel() < 2) {
            return new Result(false, "too soon for a hug;)");
        }


        if (playerFriendship.isHadHuggedToday()) {
            return new Result(false, "You have already hugged today!");
        }

        playerFriendship.setHadHuggedToday(true);
        playerFriendship.addXp(60);

        return new Result(true, "Hugged successfully!");
    }

    public Result flower(String username) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player floweredPlayer = game.findPlayer(username);

        if (floweredPlayer == null) {
            return new Result(false, "Player not found");
        }

        if (floweredPlayer == currentPlayer) {
            return new Result(false, "You can't flower yourself!");
        }

        if (!game.checkPlayerDistance(floweredPlayer, currentPlayer)) {
            return new Result(false, "You can't flower from this distance!");
        }

        PlayerFriendship playerFriendship = game.getFriendshipWith(floweredPlayer);
        if (playerFriendship.getLevel() < 2 || playerFriendship.getXp() < 300) {
            return new Result(false, "not time for flower");
        }

        // TODO: check inventory and reduce

        if (playerFriendship.getLevel() == 2) {
            playerFriendship.setLevel(3);
            playerFriendship.setXp(0);
        }

        return new Result(true, "You have flowered " + floweredPlayer.getUsername() + "!");
    }

    public Result askMarriage(String username, String ringName) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player marriagePlayer = game.findPlayer(username);


        if (currentPlayer.getAccount().getGender().equals(Gender.FEMALE)) {
            // equal to "sangin bash dokhtar"
            return new Result(false, "Be heavy girl!");
        }

        if (marriagePlayer == null) {
            return new Result(false, "Player not found! You can't marry with ghosts!");
        }

        if (marriagePlayer.getAccount().getGender().equals(Gender.MALE)) {
            return new Result(false, "Astaghforrellah...");
        }

        if (currentPlayer.getSpouse() != null) {
            if (currentPlayer.getSpouse().equals(marriagePlayer)) {
                return new Result(false, "You are married with her bro!");
            } else {
                return new Result(false, "you cant cheat on your spouse!");
            }
        }

        if (marriagePlayer.getSpouse() != null) {
            return new Result(false, "She is married...");
        }

        PlayerFriendship playerFriendship = game.getFriendshipWith(marriagePlayer);
        if (playerFriendship.getLevel() < 3 || playerFriendship.getXp() < 400) {
            return new Result(false, "Your friendship is not at appropriate level!");
        }

        if (!game.checkPlayerDistance(marriagePlayer, currentPlayer)) {
            return new Result(false, "get closer to her!");
        }

        if (!App.entityRegistry.doesEntityExist(ringName)) {
            return new Result(false, "Ring " + ringName + " doesn't exist!");
        }
        Entity ring = App.entityRegistry.makeEntity(ringName);
        Inventory inventory = currentPlayer.getComponent(Inventory.class);

        //TODO: check that its ring preferably by tag

        if (!inventory.doesHaveItem(ring)) {
            return new Result(false, "You don't have this ring!");
        }

        // do all the stuff in this function
        marriagePlayer.addSuitor(currentPlayer, ring);
        inventory.removeItem(ring);

        return new Result(true, "your request sent successfully!");
    }

    public Result respond(String respond, String username) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        Player respondPlayer = game.findPlayer(username);
        if (respondPlayer == null) {
            return new Result(false, "Player not found!");
        }

        if (!currentPlayer.getSuitors().containsKey(respondPlayer)) {
            return new Result(false, "Baa! He doesn't request you...");
        }
        Entity ring = currentPlayer.getSuitors().get(respondPlayer);

        if (respond.equals("accept")) {
            game.marry(currentPlayer, respondPlayer);
            currentPlayer.getComponent(Inventory.class).addItem(ring);
            return new Result(true, "lalalala mobarak bada!");
        } else if (respond.equals("reject")) {
            currentPlayer.getSuitors().remove(respondPlayer);
            respondPlayer.getComponent(Inventory.class).addItem(ring);
            //effects on friendship
            game.getFriendshipWith(respondPlayer).setLevel(0);
            game.getFriendshipWith(respondPlayer).setXp(0);

            // effects on energy
            respondPlayer.getEnergy().setModifier(0.5f, 7);

            return new Result(true, "hala fekr kardi ki hasti??");
        } else {
            return new Result(false, "type \"accept\" or \"reject\" as a response...");
        }


    }

    public Result startTrade(){
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        App.setCurrentMenu(Menu.TRADE_MENU);
        StringBuilder message = new StringBuilder("Welcome to TradeMenu!\nYour new offers: \n");

        for (TradeOffer tradeOffer : currentPlayer.getTrades()) {
            if (tradeOffer.getReceiver().equals(currentPlayer) && !tradeOffer.isSeen()) {
                tradeOffer.setSeen(true);
                message.append(tradeOffer.infoMessage(false));
            }
        }

        return new Result(true, message.toString());
    }

    public Result talk(String receiverPlayerName, String messageString) {
        Game game = App.getActiveGame();
        Player receiverPlayer = game.findPlayer(receiverPlayerName);
        Player currentPlayer = game.getCurrentPlayer();

        // check player existence
        if (receiverPlayer == null) {
            return new Result(false, "Player with name " + receiverPlayerName + " not found");
        }

        if (!game.checkPlayerDistance(receiverPlayer, currentPlayer)) {
            return new Result(false, "Player is not next to you...");
        }

        Message message = new Message(game.getDate(), messageString, receiverPlayer, currentPlayer);
        currentPlayer.getMessageLog().add(message);
        receiverPlayer.getMessageLog().add(message);
        receiverPlayer.setHaveNewMessage(true);


        PlayerFriendship playerFriendship = game.getFriendshipWith(receiverPlayer);

        if (!playerFriendship.isHadMessageToday()) {
            playerFriendship.setHadMessageToday(true);
            playerFriendship.addXp(20);
        }

        return new Result(true, "Your message has been sent successfully!");
    }

    public Result talkHistory(String playerName) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        currentPlayer.makeMessagesSeen();
        Player talkedPlayer = game.findPlayer(playerName);
        currentPlayer.setHaveNewMessage(false);

        if (talkedPlayer == null) {
            return new Result(false, "Player with name " + playerName + " not found");
        }

        ArrayList<Message> commonMessages = new ArrayList<>(currentPlayer.getMessageLog());
        commonMessages.retainAll(talkedPlayer.getMessageLog());

        String outcomeMessage = Message.buildMessageHistory(currentPlayer, talkedPlayer, commonMessages);

        return new Result(true, outcomeMessage);

    }

    public Result meetNPC(String npcName) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        NPC npc = game.findNPC(npcName);
        NpcFriendship npcFriendship = currentPlayer.getNpcFriendships().get(npc);

        if (npc == null) {
            return new Result(false, "NPC with name " + npcName + " not found");
        }

        //TODO: check distance

        String message = npc.getCorrectDialogue(game.getDate().getSeason(), npcFriendship.getLevel(),
                game.getTodayWeather(), game.getDate().getHour() < 16);
        if (message == null) {
            message = "not set yet!";
        }

        if (!npcFriendship.wasMetToday()) {
            npcFriendship.setWasMetToday(true);
            npcFriendship.addXp(20);
        }

        //TODO
        return new Result(true, message);
    }

    public Result giftNPC(String npcName, String itemName) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();

        NPC npc = game.findNPC(npcName);
        if (npc == null) {
            return new Result(false, "NPC with name " + npcName + " not found");
        }

        if (!App.entityRegistry.doesEntityExist(itemName)) {
            return new Result(false, "Item with name " + itemName + " does not exist");
        }
        Entity item = App.entityRegistry.makeEntity(itemName);

        Inventory inventory = currentPlayer.getComponent(Inventory.class);
        if (!inventory.doesHaveItem(itemName)) {
            return new Result(false, "You dont have this item");
        }

        if (item.hasTag(EntityTag.TOOL)) {
            return new Result(false, "You can't gift tools to NPC!");
        }

        inventory.takeFromInventory(itemName, 1);
        currentPlayer.addFriendshipByGift(npc, item);

        return new Result(true, "Your gift has been sent successfully!");
    }

    public Result questList() {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        StringBuilder message = new StringBuilder("Available quests:\n");

        for (Quest quest : game.getQuests()) {
            if (quest.doesHaveAccess(currentPlayer).isSuccessful()) {
                message.append(quest);
            }

        }
        return new Result(true, message.toString());
    }

    public Result questFinish(int questId) {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        Quest quest = game.findQuest(questId);
        if (quest == null) {
            return new Result(false, "Quest with id " + questId + " not found");
        }

        Result haveAccess  = quest.doesHaveAccess(currentPlayer);
        if (!haveAccess.isSuccessful()) {
            return haveAccess;
        }
        if (!App.entityRegistry.doesEntityExist(quest.getRequest())) {
            return new Result(false, "This item isnt set yet!");
        }


        Entity item = App.entityRegistry.makeEntity(quest.getRequest());
        int itemAmount = quest.getRequestNumber();

        Inventory inventory = currentPlayer.getComponent(Inventory.class);
        if (!inventory.doesHaveItem(item.getName(), itemAmount)) {
            return new Result(false, "You dont have enough \"" + item.getName() + "\" items");
        }

        // TODO: check distance

        inventory.takeFromInventory(item.getName(), itemAmount);

        if (quest.getReward().equalsIgnoreCase("Gold")) {
            currentPlayer.getWallet().changeBalance(quest.getRewardNumber());
            return new Result(true, "Quest finished successfully!\n" +
                    "You got: " + quest.getRewardNumber() + "Golds");
        }


        if (!App.entityRegistry.doesEntityExist(quest.getReward())) {
            return new Result(false, "Item not have set yet");
        }
        Entity reward = App.entityRegistry.makeEntity(quest.getReward());
        reward.getComponent(Pickable.class).setStackSize(quest.getRewardNumber());


        inventory.addItem(reward);

        return new Result(true, "Quest finished successfully!\n" +
                "You got: " + quest.getRewardNumber() + item.getName());
    }


    public Result friendshipNPC() {
        Game game = App.getActiveGame();
        Player currentPlayer = game.getCurrentPlayer();
        StringBuilder message = new StringBuilder("NPC Friendship: \n");

        message.append(currentPlayer.npcFriendshipDetails());

        return new Result(true, message.toString());
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
        Player player = App.getActiveGame().getCurrentPlayer();
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
            case 'x':
                switchInputType();
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
                return toolsUse(Direction.getDirection(c - '0'));
            default:
                break;
        }
        return new Result(true, "");
    }

    public Result toggleMap(){
        App.getActiveGame().toggleMapVisibility();
        return null;
    }

    public Result cheatGiveItem(String name, int quantity){
        Player currentPlayer = App.getActiveGame().getCurrentPlayer();
        if(!App.entityRegistry.doesEntityExist(name)){
            return new Result(false, "entity doesnt exist");
        }
        Entity entity = App.entityRegistry.makeEntity(name);
        if(entity.getComponent(Pickable.class) == null){
            return new Result(false, "entity isn't pickable");
        }
        entity.getComponent(Pickable.class).changeStackSize(quantity);
        currentPlayer.getComponent(Inventory.class).addItem(entity);
        return new Result(true, quantity + " " + name + (quantity > 1 ? "s" : "") +
                " were given to " + currentPlayer.getAccount().getNickname());
    }

}
