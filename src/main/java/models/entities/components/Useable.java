package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.entities.UseFunction;

import java.util.ArrayList;
import java.util.Arrays;

public class Useable extends EntityComponent{
    @JsonProperty("useFunctions")
    private final ArrayList<UseFunction> functions = new ArrayList<>();

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
                "functions=" + functions +
                '}';
    }

    @Override
    public EntityComponent clone() {
        return new Useable(this);
    }
}
