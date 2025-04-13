package models.game.crafting;

import models.game.items.Item;
import models.game.items.workstations.WorkStation;
import models.game.player.Skill;

import java.util.ArrayList;
import java.util.Map;

public enum Recipe {
    ;
    private final Item output;
    private final int productionTime;
    private final WorkStation workStation;
    private final ArrayList<Item> ingredients;
    private final Map<Skill, Integer> minimumSkill;
}
