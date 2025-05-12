package models;

import models.gameMap.GameMap;

public class Position extends Vec2{
    GameMap map;

    public Position(double x, double y, GameMap map){
        super(x, y);
        this.map = map;
    }
    public Position(double x, double y) {
        this(x, y, null);
    }
    public Position(int x, int y){
        super(x, y);
    }

    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "<" + getCol() + ", " + getRow() + ">";
    }

    public Position changeByDirection(String direction){
        return switch (direction) {
            case "left" -> {
                add(-1, 0);
                yield this;
            }
            case "right" -> {
                add(1, 0);
                yield this;
            }
            case "up" -> {
                add(0, -1);
                yield this;
            }
            case "down" -> {
                add(0, 1);
                yield this;
            }
            case "upleft" -> {
                add(-1, -1);
                yield this;
            }
            case "upright" -> {
                add(1, -1);
                yield this;
            }
            case "downleft" -> {
                add(-1, 1);
                yield this;
            }
            case "downright" -> {
                add(1, 1);
                yield this;
            }
            default -> null;
        };
    }
}
