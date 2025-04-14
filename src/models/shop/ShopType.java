package models.shop;

public enum ShopType {
    BLACKSMITH(new Shop()),
    JOJAMART(),
    GENERAL_STROE(),
    CARPENTERS_SHOP(),
    FISH_SHOP(),
    RANCH(),
    SALOON(),
    ;


    private final Shop shop;

    ShopType(Shop shop) {
        this.shop = shop;
    }
}
