package models.player;

import models.Date;
import models.entities.Entity;

public class Gift {
    private final Player sender;
    private final Player receiver;
    private final Entity content;
    private final Date date;
    private boolean seen;
    private int rating;

    public Gift(Player sender, Player receiver, Entity content, Date date) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.date = date;
    }
}
