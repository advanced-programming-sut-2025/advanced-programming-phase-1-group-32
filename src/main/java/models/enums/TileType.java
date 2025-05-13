package models.enums;

import views.inGame.Color;

public enum TileType {
    GRASS('.', new Color(51, 107, 14)),
    WATER('#', new Color(50, 150, 255), false),
    PLOWED('.', new Color(3, 252, 244)),
    DIRT('.', new Color(207, 60, 21)),
    STONE('.', new Color(97, 97, 97)),
    WOOD('.', new Color(107, 60, 21)),
    PLANTED_GROUND('.', new Color(107, 60, 21)),
    DOOR('.', Color.BLACK),

    WALL('#', new Color(200, 200, 200), false),
    ROAD('.', new Color(158, 158, 158)),
    ;

    TileType(char character, Color color) {
        this.character = character;
        this.color = color;
        this.isWalkable = true;
    }
    TileType(char character, Color color, boolean isWalkable) {
        this.character = character;
        this.color = color;
        this.isWalkable = isWalkable;
    }

    public final char character;
    public final Color color;
    public final boolean isWalkable;
}
