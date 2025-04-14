package main.java.models.animal;

import main.java.models.items.Item;

public class AnimalProduct {
    private final Item    item;
    private final double  baseProbability;
    private final boolean moreWhenHappy;        // oonaee ke ba afzayesh khoshhaly ehtemaleshoon bishtare
    private final int     minimumHappiness;     // bara pashm boz
    private final int     prodctionInterval;

    public AnimalProduct(Item item, double baseProbability, int prodctionInterval, boolean moreWhenHappy, int minimumHappiness) {
        this.item               = item;
        this.moreWhenHappy      = moreWhenHappy;
        this.baseProbability    = baseProbability;
        this.minimumHappiness   = minimumHappiness;
        this.prodctionInterval  = prodctionInterval;
    }

    public AnimalProduct(Item item, double baseProbability, int prodctionInterval, boolean moreWhenHappy) {
        this(item, baseProbability, prodctionInterval, moreWhenHappy, 0);
    }
    public AnimalProduct(Item item, double baseProbability, int prodctionInterval) {
        this(item, baseProbability, prodctionInterval, false, 0);
    }

    public double getProbabillity(Animal animal){
        return 1;
    }
}