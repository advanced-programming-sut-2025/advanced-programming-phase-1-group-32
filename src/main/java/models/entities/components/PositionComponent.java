package models.entities.components;

import models.Position;
import models.Vec2;

public class PositionComponent extends EntityComponent{
    private Position position;

    public PositionComponent(double x, double y){
        position = new Position(x, y);
    }

    public PositionComponent(Vec2 position) {
        this(position.getX(), position.getY());
    }

    public PositionComponent(Position position) {
        this(position.getCol(), position.getRow());
    }

    public PositionComponent(PositionComponent other){
        this(other.position);
    }

    public PositionComponent() {
    }

    public Position get(){
        return position;
    }

    public double getX(){
        return position.getX();
    }

    public double getY(){
        return position.getY();
    }

    public int getCol(){
        return position.getCol();
    }

    public int getRow(){
        return position.getRow();
    }

    public void setPosition(Vec2 position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public void setPosition(Position position){
        this.position.setX(position.getCol());
        this.position.setY(position.getRow());
    }

    @Override
    public EntityComponent clone() {
        return new PositionComponent(this);
    }
}
