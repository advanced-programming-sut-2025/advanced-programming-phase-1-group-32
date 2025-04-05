package models.items;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Inventory {
    public int getItemCapacity();

    public void setItemCapacity();

    public boolean addItem();

    public void removeItem();

    public boolean hasItem();

    public List<Item> getItems();
}
