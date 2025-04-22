package models.enums;

public enum ProfileMenuCommands implements Commands{
    MENU_ENTER("^menu\\s+enter\\s+(?<menuName>.+)"),
    SHOW_CURRENT_MENU("^show\\s+current\\s+menu$"),
    CHANGE_USERNAME("^change\\s+username\\s+-u\\s+(?<username>.+)$"),
    CHANGE_NICKNAME("^change\\s+nickname\\s+-u\\s+(?<nickname>.+)$"),
    CHANGE_EMAIL("^change\\s+email\\s+-e(?<email>.+)$"),
    CHANGE_PASSWORD("^change\\s+password\\s+-p\\s+(?<newPassword>.+)\\s+-o(?<pldPassword>.+)$"),
    USER_INFO("^user\\s+info$")

    ;


    private final String pattern;

    ProfileMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
