package models.game.player;

import models.game.Date;
import models.game.items.Item;

public class Gift {
    private final Player sender;
    private final Player reciever;
    private final Item content;
    private final Date date;
    private boolean seen;
    private int rating;
}
