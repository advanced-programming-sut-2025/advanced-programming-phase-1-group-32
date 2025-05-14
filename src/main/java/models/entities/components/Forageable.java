package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Forageable extends EntityComponent{
    private boolean foraged = false;

    public boolean isForaged() {
        return foraged;
    }

    public void setForaged(boolean foraged) {
        this.foraged = foraged;
    }

    @Override
    public EntityComponent clone() {
        return null;
    }
}
