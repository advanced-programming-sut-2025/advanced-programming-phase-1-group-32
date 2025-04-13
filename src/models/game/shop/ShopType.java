package models.game.shop;

public enum ShopType {
    BLACKSMITH(new Shop()),


    ;


    private final Shop shop;

    ShopType(Shop shop) {
        this.shop = shop;
    }
}
