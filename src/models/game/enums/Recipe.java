package models.enums;

import models.items.Item;
import models.items.workstations.WorkStation;
import models.player.Skill;

import java.util.ArrayList;
import java.util.Map;

public enum Recipe {
    ;
    private final Item output;
    private final RecipeType type;
    private final int productionTime;
    private final WorkStation workStation;
    private final ArrayList<Item> ingredients;
    private final Map<Skill, Integer> minimumSkill;
}
