package models.enums;

import views.inGame.Color;

public enum TileType {
    GRASS('.', new Color(51, 107, 14)),
    WATER('#', new Color(50, 150, 255)),
    PLOWED('.', new Color(107, 60, 21)),
    DIRT('.', new Color(107, 60, 21)),
    WOOD('.', new Color(107, 60, 21)),
    PLANTED_GROUND('.', new Color(107, 60, 21)),
    WALL('#', new Color(200, 200, 200)),
    DOOR('D', new Color(78, 52, 46)),
    ;

    TileType(char character, Color color) {
        this.character = character;
        this.color = color;
    }

    public final char character;
    public final Color color;
}
