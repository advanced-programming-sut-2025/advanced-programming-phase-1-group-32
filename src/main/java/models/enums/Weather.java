package models.enums;

public enum Weather {
    SUNNY,
    RAINY,
    STORMY,
    SNOWY
    ;

    public static Weather getweather(String weatherString) {
        for (Weather weather : Weather.values()) {
            if (weather.name().equalsIgnoreCase(weatherString)) {
                return weather;
            }
        }
        return null;
    }

    public double getFishingEffect() {
        switch (this) {
            case SUNNY -> {
                return 1.5;
            }
            case RAINY -> {
                return 1.2;
            }
            case STORMY -> {
                return 0.5;
            }
            default -> {
                return 1;
            }
        }
    }
}
