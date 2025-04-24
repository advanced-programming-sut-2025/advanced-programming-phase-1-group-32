package models.entities.components;

import models.entities.UseFunction;

import java.util.ArrayList;
import java.util.Arrays;

public class Useable extends EntityComponent{
    private final ArrayList<UseFunction> functions = new ArrayList<>();

    public Useable(UseFunction... functions) {
        this.functions.addAll(Arrays.asList(functions));
    }
}
