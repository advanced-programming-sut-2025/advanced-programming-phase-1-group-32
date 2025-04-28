package models.enums;

public enum Season {
    SPRING,
    SUMMER,
    FALL,
    WINTER;

    public void updatePlant(){

    };

    public static Season nextSeason(Season season) {
        switch (season) {
            case SPRING -> {
                return SUMMER;
            }
            case SUMMER -> {
                return FALL;
            }
            case FALL -> {
                return WINTER;
            }
            case WINTER -> {
                return SPRING;
            }
        }

        return null;
    }
}
