package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import views.inGame.Color;

public class Renderable extends EntityComponent{
    @JsonProperty("char")
    private char character;
    @JsonProperty("color")
    private Color color;

    public Renderable(char character, Color color) {
        this.character = character;
        this.color = color;
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
}