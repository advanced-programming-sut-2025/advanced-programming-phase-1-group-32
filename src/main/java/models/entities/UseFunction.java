package models.entities;

import models.App;
import models.entities.components.Pickable;
import models.entities.systems.EntityPlacementSystem;
import models.gameMap.Tile;
import models.animal.Animal;
import models.entities.components.Container;
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
        protected Result use(Player player,Entity tool, Tile tile, Entity target) {
            if(tile.getContent() != null){
                return new Result(false, "The tile isn't empty");
            }
            if(tile.getType() != TileType.DIRT){
                return new Result(false, "You can only plow dirt");
            }
            tile.setType(TileType.PLOWED);
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
        protected Result use(Player player,Entity tool, Tile tile, Entity target) {
            if(tile.getContent() != null){
                return new Result(false, "The tile isn't empty");
            }
            if(tile.getType() != TileType.PLOWED){
                return new Result(false, "you can only de_plow plowed ground");
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
        protected Result use(Player player, Entity tool, Tile tile, Entity target) {
            Entity mineral = tile.getContent();
            if(mineral == null){
                return new Result(false, "Nothing to mine!");
            }
            if(!mineral.hasTag(EntityTag.BREAKABLE_WITH_PICKAXE)){
                return new Result(false, "you can't use a pickaxe on " + mineral.getEntityName());
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
        protected Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    CHOP_TREE {
        @Override
        protected Result use(Player player, Entity tool, Tile tile, Entity target) {
            Entity tree = tile.getContent();
            if(tree == null){
                return new Result(false, "No tree to chop!");
            }

            if(!tree.hasTag(EntityTag.BREAKABLE_WITH_AXE)){
                return new Result(false, "you can't use an axe on " + tree.getEntityName());
            }

            Harvestable harvestable = tree.getComponent(Harvestable.class);
            int energyCost = 5 - tool.getComponent(Upgradable.class).getMaterial().getLevel();
            energyCost -= player.getSkill(SkillType.FORAGING).getLevel() == 4 ? 1 : 0;
            //TODO: weather effects?
            player.reduceEnergy(Math.max(energyCost, 0));
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
        protected Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    WATER_GROUND {
        @Override
        protected Result use(Player player, Entity tool, Tile tile, Entity target) {
            Container container =  tool.getComponent(Container.class);
            if(!tile.getType().equals(TileType.PLANTED_GROUND /* TODO: is it true?*/))
                return new Result(false, "you can't water this ground");
            tile.getContent().getComponent(Growable.class).setWateredToday(true);
            int energyCost = 5 - tool.getComponent(Upgradable.class).getMaterial().getLevel();
            energyCost -= player.getSkill(SkillType.FARMING).getLevel() == 4 ? 1 : 0;
            player.reduceEnergy(Math.max(energyCost, 0));
            container.decreaseCharge();
            return new Result(true, "tile watered successfully");
        }
    },
    FILL_WATER {
        @Override
        protected Result use(Player player, Entity tool, Tile tile, Entity target) {
            if(tile.getType().equals(TileType.WATER) /*TODO: or greenhouse water*/) {
                tool.getComponent(Container.class).fillContainer();
                int energyCost = 5 - tool.getComponent(Upgradable.class).getMaterial().getLevel();
                energyCost -= player.getSkill(SkillType.FARMING).getLevel() == 4 ? 1 : 0;
                player.reduceEnergy(Math.max(0, energyCost));
                return new Result(true, "watering can filled successfully");
            }
            else
                return new Result(false, "Can't fill water with this tile");
        }
    },
    FISH {
        @Override
        protected Result use(Player player, Entity tool, Tile tile, Entity target) {
            return null;
        }
    },
    CUT_GRASS {
        @Override
        protected Result use(Player player, Entity tool, Tile tile, Entity targetEntity) {
            return null;
        }
    },
    HARVEST_PLANTS {
        @Override
        protected Result use(Player player, Entity tool, Tile tile, Entity target) {
            Entity entity = tile.getContent();
            Growable growable;
            if(entity == null || (growable = entity.getComponent(Growable.class)) == null){
                return new Result(false, "nothing to harvest in that tile");
            }

            Result result = growable.canCollectProduct();
            if (!result.isSuccessful()) {
                return result;
            }

            Inventory inventory = player.getComponent(Inventory.class);

            //TODO: make quality for fruits
            if (growable.isOneTime()) {
                EntityPlacementSystem.emptyTile(tile);
                tile.setType(TileType.DIRT);
                inventory.addItem(entity);
            } else {
                Entity fruit = growable.collectFruit();
                growable.setDaysPastFromRegrowth(0);
                fruit.getComponent(Pickable.class).setStackSize(1);
                inventory.addItem(fruit);
            }

            player.addExperince(SkillType.FARMING, 5);

            //TODO: reduce energy



            return new Result(true, "harvested");
        }
    },
    EXTRACT_MILK {

        @Override
        protected Result use(Player player, Entity tool, Tile tile, Entity target) {

            if(tool.getComponent(Container.class).getCharge() > 0)
                return new Result(false, "milk pail is full");
            player.reduceEnergy(4);// on success and fail
            if(target == null )
                return new Result(false, "you should select an animal");
            if(!(target instanceof Animal))
                return new Result(false, "you should select an animal");


            Animal animal = (Animal) target;
            if (!animal.getAnimalType().getNeededTool().equals("Milk pail")) {
                return new Result(false, target.getEntityName() + " doesn't produce milk.");
            }

            Entity product = animal.getTodayProduct();
            if (product == null) {
                return new Result(false, "It didn't produce milk today.");
            }
            animal.setTodayProduct(null);
            tool.getComponent(Container.class).fillContainer();
            Inventory inventory = player.getComponent(Inventory.class);
            inventory.addItem(product);
            animal.addFriendshipLevel(5);
            player.getSkill(SkillType.FARMING).addExperience(5);
            return new Result(true, "Milk extracted successfully");
        }
    },
    COLLECT_WOOL{
        @Override
        protected Result use(Player player, Entity tool, Tile tile, Entity target) {
            player.reduceEnergy(4);
            if(!(target instanceof Animal))
                return new Result(false, "you should select an animal");

            Animal animal = (Animal) target;
            if (!animal.getAnimalType().getNeededTool().equals("Shear")) {
                return new Result(false, "You can not collect wool from this animal");
            }

            animal.setTodayProduct(null);
            Inventory inventory = player.getComponent(Inventory.class);
            inventory.addItem(animal);
            animal.addFriendshipLevel(5);
            player.getSkill(SkillType.FARMING).addExperience(5);


            return new Result(true, "wool collected successfully");
        }
    },

    ;


    abstract protected Result use(Player player,Entity tool, Tile tile, Entity target);
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