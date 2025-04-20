package models.enums;

import java.util.regex.Matcher;

public enum LoginMenuCommands implements Commands{
    REGISTER("register\\s+-u\\s+(?<username>\\w+)\\s+-p\\s+(?<password>\\S+)\\s+(?<passwordConfirm>\\S+)\\s+" +
            "-n\\s+(?<name>\\S+)\\s+-e\\s+(?<email>\\S+)\\s+-g\\s+(?<gender>\\S+)\\s*"),
    PICK_QUESTION("\\s*pick\\s+question\\s+-q\\s+(?<number>\\d+)\\s+-a\\s+(?<answer>.+)\\s+-c\\s+(?<answerConfirm>.+)"),
    FORGOT_PASSWORD ("^\\s*forgot\\s+password\\s+-u" +
                        "\\s+(?<username>\\S+)\\s*$"),
    LOGIN           ("^\\s*login\\s+-u" +
                        "\\s+(?<username>\\S+)\\s+-p" +
                        "\\s+(?<password>\\S+)" +
                        "(?:\\s+(?<stayLogged>-stayLogged))?\\s*$"),
    EXIT            ("^\\s*exit\\s*$")
    ;

    private final String pattern;

    LoginMenuCommands(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String getPattern() {
        return this.pattern;
    }
}
