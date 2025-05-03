package models.Commands;

public enum GameMenuCommands implements Commands {
    WALK              ("^\\s*walk\\s+-l\\s+<\\s*(?<x>-?\\d+)\\s*,\\s*(?<y>-?\\d+)\\s*>\\s*$"),
    TIME              ("\\s*time\\s*"),
    DATE              ("\\s*date\\s*"),
    SEASON            ("\\s*season\\s*"),
    WEATHER           ("\\s*weather\\s*"),
    DATETIME          ("\\s*datetime\\s*"),
    CRAFTINFO         ("\\s*craftinfo\\s+-n\\s+(?<name>.+)\\s*"),
    NEXT_TURN         ("^\\s*next\\s+turn\\s*$"),
    TOGGLE_MAP        ("^\\s*toggle\\s+map\\s*$"),
    PLANT_SEED        ("\\s*plant\\s+-s\\s+(?<seed>.+)\\s+-d\\s+(?<direction>\\S+)\\s*"),
    SHOW_PLANT        ("\\s*showplant\\s+-l\\s+<(?<x>\\d+),(?<y>\\d+)>\\s*"),
    ENERGY_SET        ("\\s*energy\\s+set\\s+-v\\s+(?<amount>\\d+)\\s*"),
    SET_WEATHER       ("cheat\\s+weather\\s*set\\s*(?<type>\\S+)\\s*"),
    ENERGY_SHOW       ("\\s*energy\\s+show\\s*"),
    ADVANCE_TIME      ("\\s*cheat\\s+advance\\s+time\\s+(?<amount>\\d+)h\\s*"),
    ADVANCE_DATE      ("\\s*cheat\\s+advance\\s+date\\s+(?<amount>\\d+)d\\s*"),
    SHOW_INVENTORY    ("\\s*show\\s+inventory\\s*"),
    CHEAT_GIVE_ITEM   ("^\\s*cheat\\s+give\\s+item\\s+\"(?<name>\\S+)\"\\s+(?<quantity>\\S+)\\s*$"),
    DAY_OF_THE_WEEK   ("\\s*day\\s+of\\s+the\\s+week\\s*"),
    WEATHER_FORECAST  ("\\s*weather\\s*forecast\\s*"),
    ENERGY_UNLIMITED  ("\\s*energy\\s+unlimited\\s*"),
    GREEN_HOUSE_BUILD ("\\s*greenhouse\\s+build\\s*"),
    CHANGE_INPUT_TYPE ("^\\s*change\\s+input\\s+type\\s*$"),

    /* ------------------------------------------ Tools commands ------------------------------------------ */
    TOOLS_EQUIP("^tools\\s+equip\\s+(?<toolName>.+)$"), //TODO: check name
    TOOLS_SHOW_CURRENT("^tools\\s+show\\s+current$"),
    TOOLS_AVAILABLE("^tools\\s+show\\s+available$"),
    TOOLS_UPGRADE("^tools\\s+upgrade\\s+(?<toolName>.+)$"),//TODO
    TOOLS_USE("^tools\\s+use\\s+-d\\s+(?<direction>.+)"),//TODO
    /* -------------------------------------------------- -------------------------------------------------- */
    FERTILIZE("fertilize\\s+-f\\s+(?<name>.+)\\s+-d\\s+(?<direction>\\S+)\\s*"),

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
