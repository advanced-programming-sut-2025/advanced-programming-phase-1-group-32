package models.Commands;

public enum GameMenuCommands implements Commands {
    TIME("\\s*time\\s*"),
    DATE("\\s*date\\s*"),
    DATETIME("\\s*datetime\\s*"),
    DAT_OF_THE_WEEK("\\s*date\\s+of\\s+the\\s+week\\s*"),
    ;

    private final String pattern;

    GameMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
