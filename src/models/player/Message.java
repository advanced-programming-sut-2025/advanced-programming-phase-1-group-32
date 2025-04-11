package models.player;

import models.Date;

public class Message {
    private final Player sender;
    private final Player reciever;
    private final String message;
    private final Date date;
    private boolean seen;
}
