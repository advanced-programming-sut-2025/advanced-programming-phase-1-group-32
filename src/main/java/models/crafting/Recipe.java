package models.crafting;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import models.entities.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@JsonDeserialize(builder = Recipe.Builder.class)
public class Recipe{
    private final String name;
    private final ArrayList<Ingredient> ingredients;
    private final boolean isUnlocked;
    private final int day;
    private final int hour;
    private RecipeType type;

    private Recipe(Builder builder) {
        this.name = builder.name;
        this.ingredients = new ArrayList<>(builder.ingredients);
        this.isUnlocked = builder.isUnlocked;
        this.day = builder.day;
        this.hour = builder.hour;
        this.type = builder.type;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Ingredient> getIngredients() {
        return new ArrayList<>(ingredients);
    }

    protected boolean isUnlocked() {
        return isUnlocked;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public RecipeType getType() {
        return type;
    }

    public void setType(RecipeType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("---------------------------------------------------\n")
                .append(name).append(" Recipe:\n");
        for (Ingredient ingredient : ingredients) {
            sb.append(ingredient.toString()).append("\n");
        }
        sb.append("---------------------------------------------------\n");
        return sb.toString();
    }

    @JsonPOJOBuilder(withPrefix = "")
    //@JsonIgnoreProperties(ignoreUnknown = true)
    public static class Builder {
        private String name;
        private ArrayList<Ingredient> ingredients = new ArrayList<>();
        private boolean isUnlocked;
        private int day;
        private int hour;
        private RecipeType type;

        public Builder() {
        }

        /**
         * Name of the recipe
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Ingredients list
         */
        public Builder ingredients(ArrayList<Ingredient> ingredients) {
            this.ingredients = new ArrayList<>(ingredients);
            return this;
        }

        /**
         * is unlock at start of game
         */
        public Builder isUnlocked(boolean isUnlocked) {
            this.isUnlocked = isUnlocked;
            return this;
        }

        /*
        * Processing day
        * */
        public Builder day(int day) {
            this.day = day;
            return this;
        }

        /*
        * Processing hour
        * */
        public Builder hour(int hour) {
            this.hour = hour;
            return this;
        }
        /*
        * Recipe type
        * */
        public Builder type(RecipeType type) {
            this.type = type;
            return this;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }
}
