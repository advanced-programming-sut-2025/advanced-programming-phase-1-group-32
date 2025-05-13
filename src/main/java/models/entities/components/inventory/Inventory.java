package models.entities.components.inventory;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.App;
import models.entities.Entity;
import models.entities.components.EntityComponent;
import models.entities.components.Pickable;
import models.enums.EntityTag;
import records.Result;

import java.util.ArrayList;

public class Inventory extends EntityComponent {
    @JsonProperty("slots")
    @JsonIdentityReference(alwaysAsId = true)
    private ArrayList<InventorySlot> slots = new ArrayList<>();
    @JsonProperty("capacity")
    private int capacity;

    public Inventory(int capacity) {
        this.slots = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            this.slots.add(new InventorySlot());
        }
        this.capacity = capacity;
    }

    public Inventory(ArrayList<InventorySlot> slots) {
        this.slots.addAll(slots);
        this.capacity = slots.size();
    }

    private Inventory(Inventory other) {
        this.capacity = other.capacity;
        for (int i = 0; i < capacity; i++) {
            this.slots.add(new InventorySlot());
        }
    }

    public Inventory() {
        this(0);
    }

    /***
     * @param entity the entity you want to add
     * @param slot the destination slot
     */
    public Result addItemToSlot(Entity entity, InventorySlot slot) {
        Entity entity2 = slot.getEntity();
        if (entity2 == null) {
            slot.setEntity(entity);
            return new Result(true, "");
        }

        Pickable pickable = entity.getComponent(Pickable.class);
        if (entity2.isTheSameAs(entity)) {
            Pickable pickable2 = entity2.getComponent(Pickable.class);
            int leftSpace = pickable2.getMaxStack() - pickable2.getStackSize();
            int picked = Math.min(leftSpace, pickable.getStackSize());
            pickable2.changeStackSize(picked);
            pickable.changeStackSize(-picked);
            if (pickable.getStackSize() == 0) {
                return new Result(true, "");
            } else {
                return new Result(false, "");
            }
        }

        return new Result(false, "");
    }

    /***
     * @param entity the entity you want to add
     * @param slotIndex the destination slot index
     */
    public Result addItemToSlot(Entity entity, int slotIndex) {
        return this.addItemToSlot(entity, this.slots.get(slotIndex));
    }

    /***
     * Adds the specified entity to the inventory. if the entity was completely added, it removes the entity from the source array
     * @param entity the entity you want to pick up
     * @param sourceList The list which contains the entity
     */
    public Result addItem(Entity entity, ArrayList<Entity> sourceList) {
        Result result = this.addItem(entity);
        if (result.isSuccessful()) {
            sourceList.remove(entity);
        }
        return result;
    }

    /***
     * adds the item which is in the specified slot to the inventory
     * @param slot source slot
     */
    public Result addItem(InventorySlot slot) {
        Result result = this.addItem(slot.getEntity());
        if (slot.getEntity().getComponent(Pickable.class).getStackSize() == 0) {
            slot.getEntity().delete();
            slot.setEntity(null);
        }
        return result;
    }

    public boolean canAddItem(Entity item, int amount) {
        for (InventorySlot slot : slots) {
            if(slot.getEntity() != null && slot.getEntity().isTheSameAs(item)) {
                Pickable slotPickable = slot.getEntity().getComponent(Pickable.class);
                amount -= slotPickable.getMaxStack() - slotPickable.getStackSize();
            }
        }
        for (InventorySlot slot : slots) {
            if(slot.getEntity() == null) {
                amount -= item.getComponent(Pickable.class).getMaxStack();
            }
        }
        return amount <= 0;
    }


    /***
     * This function is used when you don't care about the destination slot and just want to add an item to the inventory
     * @param entity the entity you want to add. the stack size of the entity could be more than its maximum stack size
     * @return returns successful result if the entity was completely added
     */
    public Result addItem(Entity entity) {
        if (entity == null) {
            return new Result(false, "entity is null");
        }
        Pickable pickable = entity.getComponent(Pickable.class);
        if (pickable == null) {
            throw new RuntimeException("Tried to pick up " + entity.getEntityName() + " which doesnt have a Pickable component");
        }
        boolean entityAdded = false;
        //add to existing stacks of the same item if possible
        for (InventorySlot s : this.slots) {
            Entity entity2 = s.getEntity();
            if (entity2 != null && entity2.isTheSameAs(entity)) {
                Pickable pickable2 = entity2.getComponent(Pickable.class);
                int leftSpace = pickable2.getMaxStack() - pickable2.getStackSize();
                int picked = Math.min(leftSpace, pickable.getStackSize());
                pickable2.changeStackSize(picked);
                pickable.changeStackSize(-picked);
                if (pickable.getStackSize() == 0) {
                    entityAdded = true;
                    break;
                }
            }
        }
        if (entityAdded) {
            return new Result(true, "");
        }
        //add to empty slots in inventory
        if (pickable.getStackSize() != 0) {
            for (InventorySlot s : slots) {
                if (s.getEntity() == null) {
                    if (pickable.getStackSize() > pickable.getMaxStack()) {
                        s.setEntity(pickable.split(pickable.getMaxStack()));
                    } else {
                        s.setEntity(entity);
                        entityAdded = true;
                        break;
                    }
                }

            }
        }
        if (entityAdded) {
            return new Result(true, "");
        } else {
            return new Result(false, "v");
        }
    }

    /***
     * transfer from one the source slot to the destination slot
     */
    public Result transferToSlot(InventorySlot source, InventorySlot destination) {
        Entity sourceEntity = source.getEntity();
        Entity destEntity = destination.getEntity();

        if (sourceEntity == null) {
            throw new RuntimeException("Why would you transfer an entity which is null?");
        }

        if (destEntity == null) {
            destination.setEntity(sourceEntity);
            source.setEntity(null);
            return new Result(true, "");
        }

        if (sourceEntity.isTheSameAs(destEntity)) {
            return new Result(false, "");
        }

        Pickable sourcePickable = sourceEntity.getComponent(Pickable.class);
        Pickable destPickable = destEntity.getComponent(Pickable.class);

        int leftSpace = destPickable.getMaxStack() - destPickable.getStackSize();
        int picked = Math.min(leftSpace, sourcePickable.getStackSize());
        sourcePickable.changeStackSize(-picked);
        destPickable.changeStackSize(picked);

        if (sourcePickable.getStackSize() == 0) {
            source.setEntity(null);
            sourceEntity.delete();
            return new Result(true, "");
        }

        return new Result(false, "");
    }

//    public Entity removeItem(Entity entity) {
//        for (InventorySlot s : slots) {
//            if (s.getEntity().equals(entity)) {
//                s.setEntity(null);
//            }
//        }
//        return entity;
//    }

//    public Entity removeItem(String entityName, int amount) {
//        // TODO: remove amount number of entity with entityName and return it
//        Entity entity = null;
//        for (InventorySlot s : slots) {
//            if (s.getEntity()!= null && s.getEntity().getName().equals(entityName)) {
//                s.setEntity(null);
//            }
//        }
//        return entity;
//    }

    public ArrayList<InventorySlot> getSlots() {
        return this.slots;
    }

    public ArrayList<Entity> getEntities() {
        ArrayList<Entity> out = new ArrayList<>(slots.size());
        for (InventorySlot s : slots) {
            if (s.getEntity() != null) {
                out.add(s.getEntity());
            }
        }
        return out;
    }

    public ArrayList<Entity> getItemsByTag(EntityTag tag) {
        ArrayList<Entity> out = new ArrayList<>(slots.size());
        for (InventorySlot s : slots) {
            if (s.getEntity() != null && s.getEntity().hasTag(tag)) {
                out.add(s.getEntity());
            }
        }
        return out;
    }

    public boolean doesItemWithTagExist(EntityTag tag) {
        return !this.getItemsByTag(tag).isEmpty();
    }


    public Entity getItem(String name) {
        ArrayList<Entity> out = new ArrayList<>(slots.size());
        for (InventorySlot s : slots) {
            if (s.getEntity() != null && s.getEntity().getEntityName().equals(name)) {
                return s.getEntity();
            }
        }
        return null;
    }

    public Entity getItem(Entity entity) {
        ArrayList<Entity> out = new ArrayList<>(slots.size());
        for (InventorySlot s : slots) {
            if (s.getEntity() != null && s.getEntity().isTheSameAs(entity)) {
                return s.getEntity();
            }
        }
        return null;
    }

    public InventorySlot getSlot(String name) {
        for (InventorySlot s : slots) {
            if (s.getEntity() != null && s.getEntity().getEntityName().equals(name)) {
                return s;
            }
        }
        return null;
    }

    public InventorySlot getSlot(Entity e) {
        return getSlot(e.getEntityName());
    }


    public Entity takeFromInventory(Entity entity, int amount){
        if(!this.doesHaveItem(entity)) return null;
        Entity takenEntity = entity.clone();
        Pickable pickable = takenEntity.getComponent(Pickable.class);
        pickable.setStackSize(0);

        for (InventorySlot s : slots) {
            Entity e = s.getEntity();
            if(e == null) continue;
            if(e.isTheSameAs(entity)){
                int stackSize = e.getComponent(Pickable.class).getStackSize();
                if(stackSize < amount){
                    e.getComponent(Pickable.class).changeStackSize(-e.getComponent(Pickable.class).getStackSize());
                    amount -= stackSize;
                    pickable.changeStackSize(stackSize);
                }else{
                    e.getComponent(Pickable.class).changeStackSize(-amount);
                    pickable.changeStackSize(amount);
                    amount = 0;
                    break;
                }

            }
        }

        return takenEntity;
    }

    public Entity takeFromInventory(String entityName, int amount) {
        Entity takenEntity = App.entityRegistry.makeEntity(entityName);
        Pickable pickable = takenEntity.getComponent(Pickable.class);
        pickable.setStackSize(0);

        for (InventorySlot slot : slots) {
            Entity e = slot.getEntity();
            if(e != null && e.getEntityName().equalsIgnoreCase(entityName)) {
                int stackSize = e.getComponent(Pickable.class).getStackSize();
                if(stackSize < amount) {
                    e.getComponent(Pickable.class).setStackSize(0);
                    amount -= stackSize;
                    pickable.changeStackSize(stackSize);
                } else {
                    e.getComponent(Pickable.class).changeStackSize(-amount);
                    pickable.changeStackSize(amount);
                    amount = 0;
                    break;
                }
            }
        }
        if(amount > 0)
            return null;
        return takenEntity;
    }

    public boolean doesHaveItem(String name, int amount) {
        Entity item = this.getItem(name);
        if (item == null) {
            return false;
        }
        if (item.getComponent(Pickable.class).getStackSize() > amount) {
            return false;
        }

        return true;
    }
    public boolean doesHaveItem(String name) {
        return this.getItem(name) != null;
    }
    public boolean doesHaveItem(Entity entity){
        return this.getItem(entity) != null;
    }

    public int getItemCount(String name){
        int count = 0;
        for(InventorySlot s : slots){
            if(s.getEntity() != null){
                if(s.getEntity().getEntityName().equals(name)){
                    count += s.getEntity().getComponent(Pickable.class).getStackSize();
                }
            }
        }
        return count;
    }

    public int getItemCount(Entity entity){
        return getItemCount(entity.getEntityName());
    }

    @Override
    public EntityComponent clone() {
        return new Inventory(this);
    }
}
