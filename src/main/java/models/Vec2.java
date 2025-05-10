package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Vec2{
    private double x;
    private double y;

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
    public int getCol(){
        return (int) this.x;
    }
    public int getRow(){
        return (int) this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    public Vec2 add(Vec2 v){
        this.x += v.x;
        this.y += v.y;
        return this;
    }
    public Vec2 subtract(Vec2 v){
        this.x -= v.x;
        this.y -= v.y;
        return this;
    }
    public Vec2 multiply(double m){
        this.x *= m;
        this.y *= m;
        return this;
    }
    public Vec2 divide(double m){
        this.x /= m;
        this.y /= m;
        return this;
    }
}
