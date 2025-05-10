package controllers.test;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.serializers.FieldSerializer;
import com.esotericsoftware.kryo.serializers.JavaSerializer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Account;
import models.App;

import java.io.*;
import java.security.SecureRandom;

import com.esotericsoftware.kryo.Kryo;
import models.Game;
import models.Tile;
import models.entities.Entity;
import models.entities.components.EntityComponent;
import models.gameMap.GameMap;
import models.gameMap.MapRegion;
import models.player.Player;


public class GameDataBase {

    private final static String savedPath = "./src/main/java/controllers/test/moz.json";
    private final static String binaryPath = "./src/main/java/controllers/test/moz.bin";
//    private final static String savedPath = "./src/main/java/controllers/test/moz.ser";
    private final static Kryo kryo = new Kryo();
    static {
        ObjectMapper mapper = new ObjectMapper();
        mapper.addMixIn(Object.class, IdentityMixIn.class);

        kryo.setRegistrationRequired(false);
        kryo.register(SecureRandom.class, new JavaSerializer());
//        kryo.register(Account.class, new JavaSerializer());
        kryo.register(Account.class, new FieldSerializer<>(kryo, Account.class));

        kryo.setReferences(true);

        kryo.register(AppState.class);
        kryo.register(App.class);
        kryo.register(Player.class);
        kryo.register(Account.class);
        kryo.register(MapRegion.class);
        kryo.register(GameMap.class);
        kryo.register(Game.class);
        kryo.register(Tile.class);
        kryo.register(MapRegion.class);
        kryo.register(Entity.class);
        kryo.register(EntityComponent.class);
        kryo.register(java.util.ArrayList.class);
        kryo.register(models.enums.Gender.class);
        kryo.register(java.util.HashMap.class);
        kryo.register(models.enums.SecurityQuestions.class);
        kryo.register(java.util.HashSet.class);
        kryo.register(models.building.Building.class);
        kryo.register(models.entities.components.Placeable.class);
        kryo.register(models.gameMap.Environment.class);
        kryo.register(models.enums.TileType[][].class);
        kryo.register(models.enums.TileType[].class);
        kryo.register(models.enums.TileType.class);
        kryo.register(java.security.SecureRandom.class);
        kryo.register(models.Position.class);
        kryo.register(views.inGame.Color.class);
        kryo.register(int[].class);
        kryo.register(int.class);
        kryo.register(models.Tile[][].class);
        kryo.register(models.Tile[].class);
        kryo.register(models.Tile.class);
        kryo.register(models.entities.components.harvestable.Harvestable.class);
        kryo.register(models.enums.Material.class);
        kryo.register(models.entities.components.harvestable.HarvestableResource.class);
        kryo.register(models.entities.components.Renderable.class);
        kryo.register(models.enums.EntityTag.class);
        kryo.register(models.entities.components.Growable.class);
        kryo.register(models.enums.Season.class);
        kryo.register(models.entities.components.inventory.InventorySlot.class);
        kryo.register(models.entities.components.Useable.class);
        kryo.register(models.entities.UseFunction.class);
        kryo.register(models.entities.components.Pickable.class);
        kryo.register(models.entities.components.Upgradable.class);
        kryo.register(models.entities.components.inventory.Inventory.class);
        kryo.register(models.entities.components.Sellable.class);
        kryo.register(models.entities.components.Edible.class);
        kryo.register(models.player.Energy.class);
        kryo.register(models.player.Message.class);
        kryo.register(models.Date.class);
        kryo.register(models.enums.SkillType.class);
        kryo.register(models.player.Skill.class);
        kryo.register(models.crafting.Recipe.class);
        kryo.register(models.Vec2.class);
        kryo.register(models.crafting.RecipeType.class);
        kryo.register(models.player.Wallet.class);
        kryo.register(models.player.friendship.PlayerFriendship.class);
        kryo.register(models.enums.Weather.class);
        kryo.register(models.enums.Menu.class);
        






    }

    private final static ObjectMapper mapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /*
    private static void save() {
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:game_save.db";
            Connection conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    */



    public static void saveGame () throws IOException {


        AppState state = new AppState("just for test");
        mapper.writeValue(new File(savedPath), state);
    }

    public static AppState loadGame() throws IOException {
        return mapper.readValue(new File(savedPath), AppState.class);
    }

//        public static void saveGame () throws IOException {
//
//            AppState state = new AppState("just for test");
//
//            try (Output output = new Output(new FileOutputStream(binaryPath))) {
//                kryo.writeObject(output, state);
//            }
//        }
//
//        public static AppState loadGame() throws IOException {
//            try (Input input = new Input(new FileInputStream(binaryPath))) {
//                return kryo.readObject(input, AppState.class);
//            }
//        }

//    public static void saveGame () throws IOException {
//        AppState state = new AppState("test");
//        FileOutputStream fileOut = new FileOutputStream(savedPath);
//        ObjectOutputStream out = new ObjectOutputStream(fileOut);
//        out.writeObject(state);
//        out.close();
//        fileOut.close();
//
//
//    }
//    public static AppState loadGame() throws IOException, ClassNotFoundException {
//        FileInputStream fileIn = new FileInputStream("savegame.ser");
//        ObjectInputStream in = new ObjectInputStream(fileIn);
//        AppState loaded = (AppState) in.readObject();
//        in.close();
//        fileIn.close();
//        return loaded;
//    }


}




// Configure ObjectMapper
