package models.enums;

import java.util.regex.Matcher;

public enum LoginMenuCommands implements Commands{
    REGISTER("register\\s+-u\\s+(?<username>\\w+)\\s+-p\\s+(?<password>\\S+)\\s+(?<rePassword>\\S+)\\s+" +
            "-n\\s+(?<name>\\S+)\\s+-e(?<email>\\S+)\\s+(?<gender>\\S+)\\s*"),
    PICK_QUESTION("\\s*pick\\s+question\\s+-q\\s+(?<number>\\d+)\\s+-a\\s+(?<answer>.+)\\s+-c\\s+(?<answerConfirm>.+)");
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
