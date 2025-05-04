package models.crafting;

public enum ArtisanProducts {
    HONEY(),
    CHEESE(),
    GOAT_CHEESE(),


    ;
    private final Recipe recipe;
    ArtisanProducts(Recipe recipe){
        this.recipe = recipe;
    }
    ArtisanProducts(){
        this(null);
    }
}
