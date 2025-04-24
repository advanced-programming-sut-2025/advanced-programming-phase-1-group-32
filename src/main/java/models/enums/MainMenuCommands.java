package models.enums;

public enum MainMenuCommands implements Commands {
    MENU_ENTER("^menu\\s+enter\\s+(?<menuName>.+)"),
    SHOW_CURRENT_MENU("^show\\s+current\\s+menu$"),
    SHOW_MENUS("^show\\s+menus$"),
    USER_LOGOUT("^user\\s+logout$"),

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
