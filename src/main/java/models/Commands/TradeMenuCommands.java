package models.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommands implements Commands {
    TRADE_BY_MONEY("\\s*trade\\s+-u\\s+(?<username>.+)\\s+-t\\s+(?<type>.+)\\s+-i\\s+(?<item>.+)\\s+-a\\s+" +
            "(?<amount>\\d+)\\s+-p\\s+(?<price>\\d+(?:\\.\\d+)?)\\s*$"),
    TRADE_ITEM("/^trade\\s+-u\\s+(?<username>.+)\\s+-t\\s+(?<type>.+)\\s+-i\\s+(?<item>.+)\\s+" +
            "-a\\s+(?<amount>\\d+)\\s+-ti\\s+(?<targetItem>.+)\\s+-ta\\s+(?<targetAmount>\\d+)\\s*$"),
    TRADE_LIST("\\s*trade\\s+list\\s*"),
    TRADE_RESPONSE("\\s*trade\\s+(?<response>.+)\\s+-i\\s+(?<id>\\d+)\\s*"),
    TRADE_HISTORY("\\s*trade\\s+history\\s*"),
    ;

    private final String pattern;
    TradeMenuCommands(String command) {
        this.pattern = command;
    }
    @Override
    public String getPattern() {
        return pattern;
    }
}
