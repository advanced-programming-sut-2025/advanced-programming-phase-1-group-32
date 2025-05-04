package models.entities;

import models.App;
import models.Tile;
import models.entities.components.Growable;
import models.entities.components.harvestable.Harvestable;
import models.entities.components.Upgradable;
import models.entities.components.inventory.Inventory;
import models.enums.EntityTag;
import models.enums.SkillType;
import models.enums.TileType;
import models.player.Player;
import records.Result;

import java.util.ArrayList;


public enum UseFunction {
    PLOW () {
        @Override
        public Result use(Player player,Entity tool, Tile tile, Entity target) {
            if(tile.getContent() != null){
                return new Result(false, "The tile isn't empty");
            }
            tile.setType(TileType.HOED_GROUND);
            int energyCost = 5 - tool.getComponent(Upgradable.class).getMaterial().getLevel();
            energyCost -= player.getSkill(SkillType.FARMING).getLevel() == 4 ? 1 : 0;
            //TODO: weather effects?
            player.reduceEnergy(Math.abs(energyCost));
            player.addExperince(SkillType.FARMING, 5);
            return new Result(true, "Ground converted to Hoed ground");
        }
    },
    DE_PLOW(){
        @Override
        public Result use(Player player,Entity tool, Tile tile, Entity target) {
            if(tile.getContent() != null){
                return new Result(false, "The tile isn't empty");
            }
            if(tile.getType() != TileType.HOED_GROUND){
                return new Result(false, "not a valid for de-plowing");
            }
            tile.setType(TileType.GRASS);
            int energyCost = 5 - tool.getComponent(Upgradable.class).getMaterial().getLevel();
            energyCost -= player.getSkill(SkillType.FARMING).getLevel() == 4 ? 1 : 0;
            //TODO: weather effects?
            player.reduceEnergy(Math.abs(energyCost));
            player.addExperince(SkillType.FARMING, 5);
            return new Result(true, "Hoed Ground converted to Grass");
        }
    },
    MINE(){
        @Override
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            Entity mineral = tile.getContent();
            if(mineral == null){
                return new Result(false, "Nothing to mine!");
            }
            if(!mineral.hasTag(EntityTag.BREAKABLE_WITH_PICKAXE)){
                return new Result(false, "you can't use a pickaxe on " + mineral.getName());
            }

            Harvestable harvestable = mineral.getComponent(Harvestable.class);
            int energyCost = 5 - tool.getComponent(Upgradable.class).getMaterial().getLevel();
            energyCost -= player.getSkill(SkillType.MINING).getLevel() == 4 ? 1 : 0;
            //TODO: weather effects?
            player.reduceEnergy(Math.abs(energyCost));
            if(harvestable.getMaterial().getLevel() > tool.getComponent(Upgradable.class).getMaterial().getLevel()){
                return new Result(false, "Your pickaxe cant mine that mineral. you need " + harvestable.getMaterial() + " pickaxe.");
            }
            player.addExperince(SkillType.MINING, 5);

            ArrayList<Entity> harvestedEntities = harvestable.harvest();
            mineral.delete();

            for(Entity e : harvestedEntities){
                player.getComponent(Inventory.class).addItem(e);
            }
            return new Result(true, "");
        }
    },
    DESTROY_ITEMS {
        @Override
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    CHOP_TREE {
        @Override
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            Entity tree = tile.getContent();
            if(tree == null){
                return new Result(false, "No tree to chop!");
            }

            if(!tree.hasTag(EntityTag.BREAKABLE_WITH_AXE)){
                return new Result(false, "you can't use an axe on " + tree.getName());
            }

            Harvestable harvestable = tree.getComponent(Harvestable.class);
            int energyCost = 5 - tool.getComponent(Upgradable.class).getMaterial().getLevel();
            energyCost -= player.getSkill(SkillType.FORAGING).getLevel() == 4 ? 1 : 0;
            //TODO: weather effects?
            player.reduceEnergy(Math.abs(energyCost));
            if(harvestable.getMaterial().getLevel() > tool.getComponent(Upgradable.class).getMaterial().getLevel()){
                return new Result(false, "Your axe cant chop that tree. you need " + harvestable.getMaterial() + " axe.");
            }
            player.addExperince(SkillType.FORAGING, 5);

            ArrayList<Entity> harvestedEntities = harvestable.harvest();
            tree.delete();

            for(Entity e : harvestedEntities){
                player.getComponent(Inventory.class).addItem(e);
            }
            return new Result(true, "");
        }
    },
    DESTROY_BRANCHES {
        @Override
        //TODO
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    WATER_GROUND {
        @Override
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    FILL_WATER {
        @Override
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    FISH {
        @Override
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    CUT_GRASS {
        @Override
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    HARVEST_CROPS {
        @Override
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            Entity entity = tile.getContent();
            Growable growable;
            if(entity == null || (growable = entity.getComponent(Growable.class)) == null){
                return new Result(false, "nothing to harvest in that tile");
            }
            return new Result(true, "havij");
        }
    },
    EXTRACT_MILK {
        @Override
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    COLLECT_WOOL {
        @Override
        public Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    ;


    abstract public Result use(Player player,Entity tool, Tile tile, Entity target);
    public Result use(Entity tool, Tile tile, Entity target){
        return this.use(App.getActiveGame().getCurrentPlayer(), tool, tile, target);
    }
    public Result use(Entity tool, Tile tile){
        return this.use(App.getActiveGame().getCurrentPlayer(), tool, tile, null);
    }
    public Result use(Entity tool, Entity target){
        return this.use(App.getActiveGame().getCurrentPlayer(), tool, null, target);
    }
}