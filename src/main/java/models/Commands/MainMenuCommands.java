package models.Commands;

public enum MainMenuCommands implements Commands {
    MENU_ENTER("^menu\\s+enter\\s+(?<menuName>.+)"),
    SHOW_CURRENT_MENU("^show\\s+current\\s+menu$"),
    SHOW_MENUS("^show\\s+menus$"),
    USER_LOGOUT("^user\\s+logout$"),
    NEW_GAME("^game\\s+new\\s+-u\\s+(?<usernames>.+)$"),
    LOAD_GAME("\\s*load\\s+game\\s*"),
    ;


    private final String pattern;

    MainMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
