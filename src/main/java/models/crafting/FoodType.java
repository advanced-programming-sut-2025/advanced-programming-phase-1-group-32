package models.crafting;

public enum FoodType {
    FRIED_EGG(new Recipe(true, null)), /*TODO implement after workshops*/
    BAKED_FISH(),
    SALAD(),
    OLMELET(),
    PUMPKIN_PIE(),
    SPAGHETTI(),
    PIZZA(),
    TORTILLA(),
    MAKI_ROLL(),
    TRIPLE_SHOT_ESPRESSO(),
    COOKIE(),
    HASH_BROWNS(),
    PANCAKES(),
    FRUIT_SALAD(),
    RED_PLATE(),
    BREAD(),
    SALMON_DINNER(),
    VEGETABLE_MEDLEY(),
    FARMERS_LUNCH(),
    SURVIVAL_BURGER(),
    DISH_O_THE_SEA(),
    SEAFORM_PUDDING(),
    MINER_TREAT(),
    ;
    private final Recipe recipe;
    FoodType(Recipe recipe){
        this.recipe = recipe;
    }
    FoodType(){
        this(null);
    }
}
