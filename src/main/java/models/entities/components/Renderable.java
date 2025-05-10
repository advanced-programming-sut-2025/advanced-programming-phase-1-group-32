package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.Position;
import views.inGame.Color;

public class Renderable extends EntityComponent{
    @JsonProperty("char")
    protected char character;
    @JsonProperty("color")
    protected Color color;


    public Renderable(char character, Color color) {
        this.character = character;
        this.color = color;
    }
    private Renderable(Renderable other){
        this.character = other.character;
        this.color = other.color;
    }
    public Renderable(){
        this(' ', null);
    }

    public char getCharacter() {
        return character;
    }
    public Color getColor() {
        return color;
    }

    @Override
    public EntityComponent clone() {
        return new Renderable(this);
    }
}