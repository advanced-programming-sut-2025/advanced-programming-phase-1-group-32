package models.entities;

import models.entities.components.EntityComponent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EntityList extends ArrayList<Entity> implements EntityObserver, Serializable{
    private Map<Class<? extends EntityComponent>, ArrayList<EntityComponent>> components = new HashMap<>();

    public EntityList(Collection<? extends Entity> c) {
        super(c);
    }

    public EntityList() {
        super();
    }

    public EntityList(int initialCapacity) {
        super(initialCapacity);
    }

    @Override
    public boolean add(Entity entity) {
        boolean b = super.add(entity);
        entity.addObserver(this);

        for(EntityComponent c : entity.getComponents()){
            components.putIfAbsent(c.getClass(), new ArrayList<>());
            components.get(c.getClass()).add(c);
        }

        return b;
    }

    @Override
    public boolean remove(Object o) {
        if(contains(o)){
            boolean b = super.remove(o);
            if(o instanceof Entity entity){
                (entity).removeObserveer(this);
                for(EntityComponent c : entity.getComponents()){
                    components.putIfAbsent(c.getClass(), new ArrayList<>());
                    components.get(c.getClass()).remove(c);
                }
            }

            return b;
        }
        return false;
    }

    @Override
    public void onDelete(Entity entity) {
        this.remove(entity);
    }

    @SuppressWarnings("unchecked")
    public <T extends EntityComponent> ArrayList<T> getComponentsOfType(Class<T> clazz){
        components.putIfAbsent(clazz, new ArrayList<>());
        return (ArrayList<T>) components.get(clazz);
    }

    @Serial
    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream in)
            throws IOException, ClassNotFoundException {
        // First, read in your own fields (this populates 'components')
        in.defaultReadObject();

        // Now rebuild the list itself
        int size = in.readInt();
        for (int i = 0; i < size; i++) {
            Entity e = (Entity) in.readObject();

            // Use your overridden add() to:
            //  1) add it into the ArrayList
            //  2) re‐attach your EntityObserver
            //  3) re‐populate your components map
            super.add(e);
            e.addObserver(this);
            for (EntityComponent c : e.getComponents()) {
                components
                        .computeIfAbsent(c.getClass(), k -> new ArrayList<>())
                        .add(c);
            }
        }
    }
}

