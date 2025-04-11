<<<<<<<< HEAD:src/models/items/interfaces/Inventory.java
package models.items.interfaces;
========
package models.interfaces;
>>>>>>>> main:src/models/interfaces/Inventory.java

import models.items.Item;

import java.util.List;

public interface Inventory {
    public int getItemCapacity();

    public void setItemCapacity();

    public boolean addItem();

    public void removeItem();

    public boolean hasItem();

    public List<Item> getItems();
}
