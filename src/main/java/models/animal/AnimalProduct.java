package models.animal;

import models.items.Entity;

public class AnimalProduct {
    private final Entity entity;
    private final double  baseProbability;
    private final boolean moreWhenHappy;        // oonaee ke ba afzayesh khoshhaly ehtemaleshoon bishtare
    private final int     minimumHappiness;     // bara pashm boz
    private final int     prodctionInterval;

    public AnimalProduct(Entity entity, double baseProbability, int prodctionInterval, boolean moreWhenHappy, int minimumHappiness) {
        this.entity = entity;
        this.moreWhenHappy      = moreWhenHappy;
        this.baseProbability    = baseProbability;
        this.minimumHappiness   = minimumHappiness;
        this.prodctionInterval  = prodctionInterval;
    }

    public AnimalProduct(Entity entity, double baseProbability, int prodctionInterval, boolean moreWhenHappy) {
        this(entity, baseProbability, prodctionInterval, moreWhenHappy, 0);
    }
    public AnimalProduct(Entity entity, double baseProbability, int prodctionInterval) {
        this(entity, baseProbability, prodctionInterval, false, 0);
    }

    public double getProbabillity(Animal animal){
        return 1;
    }
}