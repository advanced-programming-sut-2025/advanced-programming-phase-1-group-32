package models.entities.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import models.Date;

public class Forageable extends EntityComponent{
    private boolean foraged = false;
    private Date dateAdded = null;

    public boolean isForaged() {
        return foraged;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public void setForaged(boolean foraged) {
        this.foraged = foraged;
    }

    @Override
    public EntityComponent clone() {
        return null;
    }
}
