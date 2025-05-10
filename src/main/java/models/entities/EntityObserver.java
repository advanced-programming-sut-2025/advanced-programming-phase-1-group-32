package models.entities;

import java.io.Serializable;

public interface EntityObserver extends Serializable {
    public void onDelete(Entity entity);
}
