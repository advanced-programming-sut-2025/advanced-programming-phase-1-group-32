package models.Commands;

public enum GameMenuCommands implements Commands {
    WALK              ("^\\s*walk\\s+-l\\s+<\\s*(?<x>-?\\d+)\\s*,\\s*(?<y>-?\\d+)\\s*>\\s*$"),
    TALK              ("\\s*talk\\s+-u\\s+(?<name>.+)\\s+-m\\s+(?<message>.+)\\s*"),
    WEATHER           ("\\s*weather\\s*"),
    CRAFTINFO         ("\\s*craftinfo\\s+-n\\s+(?<name>.+)\\s*"),
    FERTILIZE         ("fertilize\\s+-f\\s+(?<name>.+)\\s+-d\\s+(?<direction>\\S+)\\s*"),
    NEXT_TURN         ("^\\s*next\\s+turn\\s*$"),
    TOGGLE_MAP        ("^\\s*toggle\\s+map\\s*$"),
    PLANT_SEED        ("\\s*plant\\s+-s\\s+(?<seed>.+)\\s+-d\\s+(?<direction>\\S+)\\s*"),
    SHOW_PLANT        ("\\s*showplant\\s+-l\\s+<(?<x>\\d+),(?<y>\\d+)>\\s*"),
    ENERGY_SET        ("\\s*energy\\s+set\\s+-v\\s+(?<amount>\\d+)\\s*"),
    SET_WEATHER       ("cheat\\s+weather\\s*set\\s*(?<type>\\S+)\\s*"),
    ENERGY_SHOW       ("\\s*energy\\s+show\\s*"),
    SHOW_INVENTORY    ("\\s*show\\s+inventory\\s*"),
    WEATHER_FORECAST  ("\\s*weather\\s*forecast\\s*"),
    ENERGY_UNLIMITED  ("\\s*energy\\s+unlimited\\s*"),
    GREEN_HOUSE_BUILD ("\\s*greenhouse\\s+build\\s*"),
    CHANGE_INPUT_TYPE ("^\\s*change\\s+input\\s+type\\s*$"),

    /* --------------------------------------- Friendship commands ---------------------------------------- */
    TALK_HISTORY      ("^\\s*talk\\s+history\\s+-u\\s+(?<name>.+)\\s*"),
    FRIENDSHIPS       ("\\s*friendships\\s*"),
    GIFT              ("\\s*gift\\s+-u\\s+(?<username>.+)\\s+-i\\s+(?<item>.+?)\\s+-a\\s+(?<amount>\\d+)\\s*"),
    GIFT_LIST         ("\\s*gift\\s+list\\s*"),
    GIFT_RATE         ("\\s*gift\\s+rate\\s+-i\\s+(?<giftNumber>\\d+)\\s*-r\\s+(?<rate>-?\\d+)\\s*"),
    GIFT_HISTORY      ("\\s*gift\\s+history\\s+-u(?<username>.+)\\s*"),
    HUG               ("\\s*hug\\s+-u\\s+(?<username>.+)\\s*"),
    FLOWER            ("\\s*flower\\s+-u\\s+(?<username>.+)\\s*"),
    /* -------------------------------------------------- -------------------------------------------------- */

    /* ------------------------------------------ Date commands  ------------------------------------------ */
    DATE              ("\\s*date\\s*"),
    TIME              ("\\s*time\\s*"),
    DATETIME          ("\\s*datetime\\s*"),
    SEASON            ("\\s*season\\s*"),
    ADVANCE_TIME      ("\\s*cheat\\s+advance\\s+time\\s+(?<amount>\\d+)h\\s*"),
    ADVANCE_DATE      ("\\s*cheat\\s+advance\\s+date\\s+(?<amount>\\d+)d\\s*"),
    DAY_OF_THE_WEEK   ("\\s*day\\s+of\\s+the\\s+week\\s*"),
    /* -------------------------------------------------- -------------------------------------------------- */

    /* ------------------------------------------ Tools commands ------------------------------------------ */
    TOOLS_EQUIP("^tools\\s+equip\\s+(?<toolName>.+)$"), //TODO: check name
    TOOLS_SHOW_CURRENT("^tools\\s+show\\s+current$"),
    TOOLS_AVAILABLE("^tools\\s+show\\s+available$"),
    TOOLS_UPGRADE("^tools\\s+upgrade\\s+(?<toolName>.+)$"),//TODO
    TOOLS_USE("^tools\\s+use\\s+-d\\s+(?<direction>.+)"),//TODO
    /* -------------------------------------------------- -------------------------------------------------- */

    /* ------------------------------------------- Item Commands ------------------------------------------- */
    PLACE_ITEM("^place\\s+item\\s+-n\\s+(?<itemName>.+?)\\s+-d\\s+(?<direction>.+)$"),
    CHEAT_GIVE_ITEM   ("^\\s*cheat\\s+give\\s+item\\s+\"(?<name>\\S+)\"\\s+(?<quantity>\\S+)\\s*$"),
    REFRIGERATOR("^cooking\\s+refrigerator\\s+(?<what>put|pick)\\s+(?<item>.+)$"),
    /* -------------------------------------------------- -------------------------------------------------- */

    /* ------------------------------------------ Recipe Commands ------------------------------------------ */
    CRAFTING_SHOW_RECIPES("^crafting\\s+show\\s+recipes$"),
    CRAFTING_CRAFT("^crafting\\s+craft\\s+(?<itemName>.+)"),
    COOKING_SHOW_RECIPES("^cooking\\s+show\\s+recipes$"),
    COOKING_PREPARE("^cooking\\s+prepare\\s+(?<recipeName>.+)$"),
    /* -------------------------------------------------- -------------------------------------------------- */

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
