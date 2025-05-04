package models.player;

import models.Date;

public class TradeOffer {
    private final Player sender;
    private final Player receiver;
    // 1 for item to item , 2 for offer, 3 for request
    private final int type;
    private final double price;
    private final String targetItem;
    private final int targetItemAmount;
    private final String givenItem;
    private final int givenItemAmount;
    private final Date date;
    private final int id;
    private boolean decided = false;
    private boolean accepted = false;
    private boolean seen = false;


    public TradeOffer(Player sender, Player receiver, int type, double price, String targetItem, int targetItemAmount,
                      String givenItem, int givenItemAmount, Date date, int id) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
        this.price = price;
        this.targetItem = targetItem;
        this.targetItemAmount = targetItemAmount;
        this.givenItem = givenItem;
        this.givenItemAmount = givenItemAmount;
        this.date = date;
        this.id = id;
    }

    public Player getSender() {
        return sender;
    }

    public Player getReceiver() {
        return receiver;
    }

    public int getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public String getTargetItem() {
        return targetItem;
    }

    public int getTargetItemAmount() {
        return targetItemAmount;
    }

    public String getGivenItem() {
        return givenItem;
    }

    public int getGivenItemAmount() {
        return givenItemAmount;
    }

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isDecided() {
        return decided;
    }

    public void setDecided(boolean decided) {
        this.decided = decided;
    }

    public String infoMessage(boolean forHistory) {
        StringBuilder result = new StringBuilder();
        result.append("ID: ").append(id).append("\n");
        result.append("Sender: ").append(sender).append("\n");
        if (forHistory) {
            result.append("Receiver: ").append(receiver).append("\n");
        }

        switch (type) {
            case 1 -> {
                result.append("givenItem: ").append(givenItem).append(" ").append(givenItemAmount)
                        .append("X").append("\n");
                result.append("targetItem: ").append(targetItem).append(" ").append(targetItemAmount)
                        .append("X").append("\n");
            }
            case 2 -> {
                result.append("givenItem: ").append(givenItem).append(" ").append(givenItemAmount)
                        .append("X").append("\n");
                result.append("targetMoney: ").append(price).append("$").append("\n");
            }
            case 3 -> {
                result.append("givenMoney: ").append(price).append("$").append("\n");
                result.append("targetItem: ").append(targetItem).append(" ").append(targetItemAmount)
                        .append("X").append("\n");
            }
        }

        result.append("Date: ").append(date).append("\n");
        if (forHistory) {
            result.append("Statue: ");
            if (isAccepted()) {
                result.append("Accepted");
            } else {
                result.append("Rejected");
            }
        }

        //TODO: check more
        result.append("--------------------------------------------------------\n");

        return result.toString();
    }

    public void reject() {
        //TODO
    }

    public void accept() {
        //TODO
    }
}
