package models.entities.workstations;

import models.Date;
import models.entities.Entity;

import java.util.ArrayList;

public class ItemProcess{
    private Entity output;
    private Date startTime;

    public ItemProcess(Entity output, Date startTime) {
        this.output = output;
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}