package models.entities;

import models.entities.components.EntityComponent;
import models.enums.EntityTag;

import java.util.ArrayList;
import java.util.HashSet;

public class Tool extends Entity{

    public Tool(String name, ArrayList<EntityComponent> components, HashSet<EntityTag> tags, int id) {
        super(name, components, tags, id);
    }

    public Tool(String name, HashSet<EntityTag> tags, EntityComponent... components) {
        super(name, tags, components);
    }

    public Tool(String name, EntityComponent... components) {
        super(name, components);
    }
}
