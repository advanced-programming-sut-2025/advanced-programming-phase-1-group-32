package models.entities;

import models.App;
import models.Tile;
import models.entities.components.Harvestable;
import models.enums.EntityTag;
import models.enums.SkillType;
import models.enums.TileType;
import models.player.Player;
import records.Result;

public enum UseFunction {
    //TODO: check functionalities
    PLOW {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            tile.setType(TileType.HOED_GROUND);
            return new Result(true, "Ground converted to Hoed_ground");
        }
    },
    DE_PLOW {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            if(tile.getType().equals(TileType.HOED_GROUND))
                tile.setType(TileType.GRASS); //TODO: grass or something else?
            return new Result(true, "");
        }
    },
    MINING {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            /*TODO*/
            return null;
        }
    },
    DESTROY_ITEMS {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            /*TODO*/
            return null;
        }
    },
    CHOP_TREE {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            player.getSkill(skill).addExperience(5);
            Entity entity = tile.getContent();
            if(!entity.hasTag(EntityTag.TREE)) // TODO: shakhe
                return new Result(false, "there is no tree");
            Harvestable harvestable = entity.getComponent(Harvestable.class);
            //TODO: claim materials? (or in harvest())
            return harvestable.harvest();
        }
    },
    DESTROY_BRANCHES {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            /*TODO*/
            return null;
        }
    },
    WATER_GROUND {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            /*TODO*/
            return null;
        }
    },
    FILL_WATER {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            /*TODO*/
            return null;
        }
    },
    FISH {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            /*TODO*/
            return null;
        }
    },
    CUT_GRASS {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            /*TODO*/
            return null;
        }
    },
    HARVEST_CROPS {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            /*TODO*/
            return null;
        }
    },
    EXTRACT_MILK {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            /*TODO*/
            return null;
        }
    },
    COLLECT_WOOL {
        @Override
        public Result use(Player player, Entity tool, Tile tile) {
            /* TODO */
            return null;
        }
    },
    ;

    protected SkillType skill;
    abstract public Result use(Player player, Entity tool, Tile tile);
}