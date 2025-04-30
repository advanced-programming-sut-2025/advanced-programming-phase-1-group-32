package models.entities.components;

import models.entities.workstations.ItemProcess;

//workstations are processor
public class Processor extends EntityComponent{
    private ItemProcess currentProcess;

    @Override
    public EntityComponent clone() {
        return null;
    }
}