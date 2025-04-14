package main.java.models.player;

import main.java.models.Date;
import main.java.models.items.Item;

import java.util.ArrayList;

public class TradeOffer {
    private final Player sender;
    private final Player reciever;
    private final ArrayList<Item> targetItems;
    private final double price;
    private final ArrayList<Item> givenItems;
    private final Date date;
    private boolean accepted;
    private boolean seen;
}
