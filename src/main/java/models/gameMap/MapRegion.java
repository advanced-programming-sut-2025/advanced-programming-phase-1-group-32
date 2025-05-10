package models.gameMap;

import models.Position;
import models.Tile;
import models.player.Player;
import views.inGame.Color;

import java.util.ArrayList;

public class MapRegion {
    String name;
    Player owner;
    ArrayList<Tile> tiles = new ArrayList<>();
    Color color;
    Position center = new Position(0, 0);

    public void addTile(Tile tile){
        //position is not double so we cant do this:
        //this.center.multiply(this.tiles.size()).change(tile.getPosition()).multiply((double) 1 / (this.tiles.size() + 1));

        if(tiles.contains(tile)) return;
        tiles.add(tile);

        float x = 0, y = 0;
        for(Tile t : tiles){
            x += t.getCol();
            y += t.getRow();
        }
        x /= tiles.size();
        y /= tiles.size();
        this.center = new Position((int) y, (int) x);
    }
    public boolean hasTile(Tile tile){
        return this.tiles.contains(tile);
    }
    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    private MapRegion() {}
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
    public void setOwner(Player player){
        this.owner = player;
    }
    public Player getOwner(){
        return owner;
    }
}