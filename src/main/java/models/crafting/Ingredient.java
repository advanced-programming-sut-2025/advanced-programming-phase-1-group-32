package models.crafting;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.entities.Entity;
import models.entities.EntityRegistry;
import models.enums.EntityTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ingredient {
    @JsonProperty("contents")
    private List<String> contents;
    @JsonProperty("validTags")
    private List<EntityTag> validTags;
    @JsonProperty("amount")
    int amount;

    private Ingredient(List<String> contents, List<EntityTag> validTags, int amount) {
        this.contents = contents;
        this.validTags = validTags;
        this.amount = amount;
        for (String content : contents) {
            App.entityRegistry.getEntityDetails(content);
        }
    }

    public static Ingredient fromEntities(List<String> entities, int amount) {
        return new Ingredient(entities, new ArrayList<>(), amount);
    }

    public static Ingredient fromValidTags(List<EntityTag> tags, int amount) {
        return new Ingredient(new ArrayList<>(), tags, amount);
    }

    public void removeTag(EntityTag tag) {
        validTags.remove(tag);
    }

    public boolean isInIngredients(Entity e, int availableAmount) {
        if(!contents.isEmpty()) {
            return contents.contains(e.getName()) && availableAmount >= amount;
        }

        if(!validTags.isEmpty()) {
            return e.getTags().stream().anyMatch(validTags::contains) && availableAmount >= amount;
        }
        return true;
    }









}
