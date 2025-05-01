package models.enums;

import views.inGame.Color;

public enum TileType {
    GRASS('.', new Color(51, 107, 14)),
    WATER('.', new Color(50, 150, 255)),
    HOED_GROUND('#', new Color(107, 60, 21)),
    PLANTED_GROUND('.', new Color(107, 60, 21)),
    ;

    TileType(char character, Color color) {
        this.character = character;
        this.color = color;
    }

    public final char character;
    public final Color color;
}
