package models.entities;

import java.util.ArrayList;

public class EntityList extends ArrayList<Entity> implements EntityObserver {
    @Override
    public boolean add(Entity entity) {
        boolean b = super.add(entity);
        entity.addObserver(this);
        return b;
    }

    @Override
    public boolean remove(Object o) {
        if(contains(o)){
            boolean b = super.remove(o);
            if(o instanceof Entity){
                ((Entity) o).removeObserveer(this);
            }
            return b;
        }
        return false;
    }

    @Override
    public void onDelete(Entity entity) {
        this.remove(entity);
    }
}
