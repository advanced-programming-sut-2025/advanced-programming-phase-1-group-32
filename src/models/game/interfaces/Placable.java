package models.game.interfaces;

import models.game.Position;

public interface Placable {
    public Position getPosition() ;
    public void setPosition();
    public void placeOnGround();

}
