package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import models.App;
import models.Tile;
import models.entities.UseFunction;
import models.player.Player;
import records.Result;

import java.util.ArrayList;
import java.util.Arrays;

public class Useable extends EntityComponent{
    @JsonProperty("useFunctions")
    private final ArrayList<UseFunction> functions = new ArrayList<>();

    @JsonProperty("energyCost")
    private int energyCost;

    public Useable(ArrayList<UseFunction> functions) {
        this.functions.addAll(functions);
    }
    private Useable(Useable other){
        this.functions.addAll(other.functions);
    }
    public Useable(){
    }

    @Override
    public String toString() {
        return "Useable{" +
                "functions=" + functions + ", " +
                "energyCost=" + energyCost +
                '}';
    }

    @Override
    public EntityComponent clone() {
        return new Useable(this);
    }

    public ArrayList<UseFunction> getFunctions() {
        return functions;
    }

    public int getEnergyCost() {
        return energyCost;
    }

    public void setEnergyCost(int energyCost) {
        this.energyCost = energyCost;
    }


    public Result use(Tile targetTile) {
        Player player = App.getLoggedInAccount().getActiveGame().getCurrentPlayer();

        if(
                Math.abs(player.getPosition().getCol() - targetTile.getCol()) > 2
                        || Math.abs(player.getPosition().getRow() - targetTile.getRow()) > 2
        )
            return new Result(false, "You don't access this tile");
        player.reduceEnergy(energyCost);
        return applyFunctions(targetTile);
    }

    private Result applyFunctions(Tile targetTile) {
        StringBuilder errors = new StringBuilder();
        for (UseFunction function : functions) {
            Result result = function.use(App.getLoggedInAccount().getActiveGame().getCurrentPlayer(), entity, targetTile);
            if(!result.isSuccessful())
                errors.append(result).append("\n");
        }
        if(errors.isEmpty())
            return new Result(true, "Tool used successfully");
        errors.deleteCharAt(errors.length() - 1);
        return new Result(false, errors.toString());
    }


}
