package models.entities.workstations;

import models.Date;
import models.entities.Entity;

import java.util.ArrayList;

public class ItemProcess{
    private Entity outputs;
    private Date startTime;

    public ItemProcess(Entity outputs, Date startTime) {
        this.outputs = outputs;
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}