package models.gameMap;

import models.Position;
import models.player.Player;
import views.inGame.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class MapRegion implements Serializable {
    private String name;
    private Player owner;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private Color color;
    private Position center = new Position(0, 0);
    private boolean isFarm = false;

    public void addTile(Tile tile) {
        //position is not double so we cant do this:
        this.center.multiply(this.tiles.size()).add(tile.getPosition()).multiply((double) 1 / (this.tiles.size() + 1));
        tiles.add(tile);
    }

    public boolean hasTile(Tile tile) {
        return this.tiles.contains(tile);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public MapRegion(String name, Color color, boolean isFarm) {
        this.name = name;
        this.color = color;
        this.isFarm = isFarm;
    }

    public Color getColor() {
        return color;
    }

    public Position getCenter() {
        return center;
    }

    public String getName() {
        return name;
    }

    public void setOwner(Player player) {
        this.owner = player;
    }

    public Player getOwner() {
        return owner;
    }

    public boolean isFarm() {
        return isFarm;
    }
}