package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Vec2 implements Serializable {
    private final double x;
    private final double y;

    @JsonCreator
    public Vec2(
            @JsonProperty("x") double x,
            @JsonProperty("y") double y
    ){
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
