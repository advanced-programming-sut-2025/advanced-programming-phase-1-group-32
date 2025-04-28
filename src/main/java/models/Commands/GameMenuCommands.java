package models.Commands;

public enum GameMenuCommands implements Commands {
    TIME("\\s*time\\s*"),
    DATE("\\s*date\\s*"),
    DATETIME("\\s*datetime\\s*"),
    DAY_OF_THE_WEEK("\\s*day\\s+of\\s+the\\s+week\\s*"),
    ADVANCE_TIME("\\s*cheat\\s+advance\\s+time\\s+(?<amount>\\d+)h\\s*"),
    ADVANCE_DATE("\\s*cheat\\s+advance\\s+date\\s+(?<amount>\\d+)d\\s*"),
    SEASON("\\s*season\\s*"),
    WEATHER("\\s*weather\\s*"),
    WEATHER_FORECAST("\\s*weather\\s*forecast\\s*"),
    SET_WEATHER("cheat\\s+weather\\s*set\\s*(?<type>\\S+)\\s*"),

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
