package models.entities;

import models.Tile;
import models.enums.TileType;
import records.Result;

public enum UseFunction {
    HOE {
        @Override
        public Result use(Tile tile) {
            Result result = super.use(tile);
            if(!result.isSuccessful())
                return result;
            tile.setType(TileType.HOED_GROUND);
            return new Result(true, "")
        }
    },
    PICKAXE {

    },
    CHOP_TREE,
    WATER_GROUND,
    FISH,

    ;
    public Result canUse(){
        return null;
    }
    public Result use(Tile tile){
        //TODO: check adjacency and energy
        return null;
    }
}