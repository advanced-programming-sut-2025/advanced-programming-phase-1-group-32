package main.java.models.player;

import main.java.models.Date;
import main.java.models.items.Item;

public class Gift {
    private final Player sender;
    private final Player reciever;
    private final Item content;
    private final Date date;
    private boolean seen;
    private int rating;
}
