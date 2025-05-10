package controllers.test;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.Account;
import models.App;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import com.esotericsoftware.kryo.Kryo;
import models.Game;
import models.Tile;
import models.entities.Entity;
import models.entities.components.EntityComponent;
import models.gameMap.GameMap;
import models.gameMap.MapRegion;
import models.player.Player;

import java.sql.SQLException;


public class GameDataBase {

//    private final static String savedPath = "./src/main/java/controllers/test/moz.json";
//private final static String savedPath = "./src/main/java/controllers/test/moz.dat";
    private final static String savedPath = "./src/main/java/controllers/test/moz.ser";
//    private final static Kryo kryo = new Kryo();
//    static {
//        kryo.register(AppState.class);
//        kryo.register(App.class);
//        kryo.register(Player.class);
//        kryo.register(Account.class);
//        kryo.register(MapRegion.class);
//        kryo.register(GameMap.class);
//        kryo.register(Game.class);
//        kryo.register(Tile.class);
//        kryo.register(MapRegion.class);
//        kryo.register(Entity.class);
//        kryo.register(EntityComponent.class);
//        kryo.register(java.util.ArrayList.class);
//        kryo.register(models.enums.Gender.class);
//        kryo.register(java.util.HashMap.class);
//        kryo.register(models.enums.SecurityQuestions.class);
//        kryo.register(java.util.HashSet.class);
//        kryo.register(models.building.Building.class);
//        kryo.register(models.entities.components.Placeable.class);
//
//    }

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

//    public static void saveGame () throws IOException {
//
//        AppState state = new AppState("just for test");
//        mapper.writeValue(new File(savedPath), state);
//    }
//
//    public static AppState loadGame() throws IOException {
//        return mapper.readValue(new File(savedPath), AppState.class);
//    }

    public static void saveGame () throws IOException {
        AppState state = new AppState("test");
        FileOutputStream fileOut = new FileOutputStream(savedPath);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(state);
        out.close();
        fileOut.close();


    }
    public static AppState loadGame() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream("savegame.ser");
        ObjectInputStream in = new ObjectInputStream(fileIn);
        AppState loaded = (AppState) in.readObject();
        in.close();
        fileIn.close();
        return loaded;
    }

}
