package models.gameMap;

import models.Position;
import models.player.Player;
import views.inGame.Color;

import java.util.ArrayList;

public class MapRegion {
    String name;
    Player owner;
    ArrayList<Tile> tiles = new ArrayList<>();
    Color color;
    Position center = new Position(0, 0);

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

    public MapRegion(String name, Color color) {
        this.name = name;
        this.color = color;
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
}