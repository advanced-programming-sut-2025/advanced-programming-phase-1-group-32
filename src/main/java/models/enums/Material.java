package models.enums;

public enum Material {
    COAL(0),
    STONE(0),
    INITIAL(0),
    COPPER(1),
    IRON(2),
    GOLD(3),
    IRIDIUM(4),

    ;

    private final int level;

    Material(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
