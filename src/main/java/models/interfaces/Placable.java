package models.interfaces;

import models.Position;

public interface Placable {
    public Position getPosition() ;
    public void setPosition();
    public void placeOnGround();
}
