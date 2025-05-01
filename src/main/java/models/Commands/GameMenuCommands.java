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
    GREEN_HOUSE_BUILD("\\s*greenhouse\\s+build\\s*"),
    WALK("^\\s*walk\\s+-l\\s+<\\s*(?<x>-?\\d+)\\s*,\\s*(?<y>-?\\d+)\\s*>\\s*$"),
    ENERGY_SHOW("\\s*energy\\s+show\\s*"),
    ENERGY_SET("\\s*energy\\s+set\\s+-v\\s+(?<amount>\\d+)\\s*"),
    ENERGY_UNLIMITED("\\s*energy\\s+unlimited\\s*"),
    SHOW_INVENTORY("\\s*show\\s+inventory\\s*"),
    CHANGE_INPUT_TYPE("^\\s*change\\s+input\\s+type\\s*$"),
    CRAFTINFO("\\s*craftinfo\\s+-n\\s+(?<name>.+)\\s*"),
    TOGGLE_MAP("^\\s*toggle\\s+map\\s*$"),
    CHEAT_GIVE_ITEM("^\\s*cheat\\s+give\\s+item\\s+\"(?<name>\\S+)\"\\s+(?<quantity>\\S+)\\s*$"),
    PLANT_SEED("\\s*plant\\s+-s\\s+(?<seed>.+)\\s+-d\\s+(?<direction>\\S+)\\s*"),
    SHOW_PLANT("\\s*showplant\\s+-l\\s+<(?<x>\\d+),(?<y>\\d+)>\\s*"),
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
