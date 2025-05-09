package models.NPC;

import models.entities.Entity;
import models.player.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Quest {
    private Entity request;
    private Entity reward;
    private boolean completed;
    private Player doneBy = null;


    public Entity getRequest() {
        return request;
    }

    public void setRequest(Entity request) {
        this.request = request;
    }

    public Entity getReward() {
        return reward;
    }

    public void setReward(Entity reward) {
        this.reward = reward;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Player getDoneBy() {
        return doneBy;
    }

    public void setDoneBy(Player doneBy) {
        this.doneBy = doneBy;
    }
}
