package models.utils;

import java.util.Locale;

public class StringUtils implements Serializable {

    public static String fridge = "Fridge";

    public static boolean isNamesEqual(String str1, String str2) {
        String normalizedString1 = str1.replaceAll("[\\s'()]+", "").toLowerCase();
        String normalizedString2 = str2.replaceAll("[\\s'()]+", "").toLowerCase();

        return normalizedString1.equals(normalizedString2);
    }
}
