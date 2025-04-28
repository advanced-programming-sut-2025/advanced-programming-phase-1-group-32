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
}
