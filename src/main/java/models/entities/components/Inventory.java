package models.entities.components;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import models.Result;
import models.entities.Entity;
import models.entities.EntityObserver;

import java.util.ArrayList;

/***
 * when you remove an item from inventory, the capacity shouldn't change. So you cant use arrayList.remove. java doesn't have
 * pointers so you cant give the pointer to a slot to remove the item in that slot. so I made this class.
 * it just holds an entity. plus, in the future, slots may hold more information.
 */
class InventorySlot implements EntityObserver{
    @JsonProperty("entity")
    private Entity entity;
    public InventorySlot(Entity entity){
        this.setEntity(entity);
    }
    public InventorySlot(){
        this(null);
    }
    public void setEntity(Entity entity){
        if(this.entity != null){
            this.entity.removeObserveer(this);
        }
        this.entity = entity;
        if(entity != null){
            entity.addObserver(this);
        }
    }
    public Entity getEntity(){
        return entity;
    }

    @Override
    public void onDelete(Entity entity) {
        this.setEntity(null);
    }
}

public class Inventory extends EntityComponent{
    @JsonProperty("slots")
    @JsonIdentityReference(alwaysAsId = true)
    private ArrayList<InventorySlot> slots;
    @JsonProperty("capacity")
    private int capacity;

    //I chose InventorySlot as input because using an Entity list would require placing null values
    // in the save file for empty slots, which would be awkward.
    /*TODO: this feels a bit wrong. the json should be like this
    * "entities" : [12, 5, null, 2, null, 3, 10, null, null]
    * but its like this :
    * "entities" : [{"entity" : 12},{"entity" : 5}{}{"entity" : 2}... */
    public Inventory(int capacity, ArrayList<InventorySlot> slots){
        this.slots = new ArrayList<>(capacity);
        for(int i = 0 ; i < capacity; i++){
            if(slots.get(i) != null){
                this.slots.set(i, slots.get(i));
            }else{
                this.slots.set(i, new InventorySlot());
            }
        }
        this.capacity = capacity;
    }
    public Inventory(int capacity) {
        this(capacity, new ArrayList<>(capacity));
    }
    public Inventory(){
        this(0);
    }

    /***
     * @param entity the entity you want to add
     * @param slot the destination slot
     */
    public Result addItemToSlot(Entity entity, InventorySlot slot){
        Entity entity2 = slot.getEntity();
        if(entity2 == null){
            slot.setEntity(entity);
            return new Result(true, "");
        }

        Pickable pickable = entity.getComponent(Pickable.class);
        if(entity2.getName().equals(entity.getName())){
            Pickable pickable2 = entity2.getComponent(Pickable.class);
            int leftSpace = pickable2.getMaxStack() - pickable2.getStackSize();
            int picked = Math.min(leftSpace, pickable.getStackSize());
            pickable2.changeStackSize(picked);
            pickable.changeStackSize(-picked);
            if(pickable.getStackSize() == 0){
                return new Result(true, "");
            }else{
                return new Result(false, "");
            }
        }

        return new Result(false, "");
    }

    /***
     * @param entity the entity you want to add
     * @param slotIndex the destination slot index
     */
    public Result addItemToSlot(Entity entity, int slotIndex){
        return this.addItemToSlot(entity, this.slots.get(slotIndex));
    }
    /***
     * Adds the specified entity to the inventory. if the entity was completely added, it removes the entity from the source array
     * @param entity the entity you want to pick up
     * @param sourceList The list which contains the entity
     */
    public Result addItem(Entity entity, ArrayList<Entity> sourceList){
        Result result = this.addItem(entity);
        if(result.success()){
            sourceList.remove(entity);
        }
        return result;
    }
    /***
     * adds the item which is in the specified slot to the inventory
     * @param slot source slot
     */
    public Result addItem(InventorySlot slot){
        Result result = this.addItem(slot.getEntity());
        if(slot.getEntity().getComponent(Pickable.class).getStackSize() == 0){
            slot.getEntity().delete();
            slot.setEntity(null);
        }
        return result;
    }
    /***
     * This function is used when you don't care about the destination slot, and just want to add an item to the inventory
     * @param entity the entity you want to add. the stack size of the entity could be more than its maximum stack size
     * @return returns successful result if the entity was completely taken.
     */
    public Result addItem(Entity entity){
        Pickable pickable = entity.getComponent(Pickable.class);
        if(pickable == null){
            throw new RuntimeException("Tried to pick up " + entity.getName() + " which doesnt have a Pickable component");
        }
        boolean entityAdded = false;
        //add to existing stacks of the same item if possible
        for(InventorySlot s : this.slots){
            Entity entity2 = s.getEntity();
            if(entity2.getName().equals(entity.getName())){
                Pickable pickable2 = entity2.getComponent(Pickable.class);
                int leftSpace = pickable2.getMaxStack() - pickable2.getStackSize();
                int picked = Math.min(leftSpace, pickable.getStackSize());
                pickable2.changeStackSize(picked);
                pickable.changeStackSize(-picked);
                if(pickable.getStackSize() == 0){
                    entityAdded = true;
                    break;
                }
            }
        }
        if(entityAdded){
            return new Result(true, "");
        }
        //add to empty slots in inventory
        if(pickable.getStackSize() != 0){
            for(InventorySlot s : slots){
                if(s.getEntity() == null){
                    if(pickable.getStackSize() > pickable.getMaxStack()){
                        s.setEntity(pickable.split(pickable.getMaxStack()));
                        pickable.changeStackSize(pickable.getMaxStack());
                    }else{
                        s.setEntity(entity);
                        entityAdded = true;
                        break;
                    }
                }

            }
        }
        if(entityAdded){
            return new Result(true, "");
        }else{
            return new Result(false, "");
        }
    }

    /***
     * transfer from one the source slot to the destination slot
     */
    public Result transferToSlot(InventorySlot source, InventorySlot destination){
        Entity sourceEntity = source.getEntity();
        Entity destEntity = destination.getEntity();

        if(sourceEntity == null){
            throw new RuntimeException("Why would you transfer an entity which is null?");
        }


        if(destEntity == null){
            destination.setEntity(sourceEntity);
            source.setEntity(null);
            return new Result(true, "");
        }

        if(sourceEntity.getName().equals(destEntity.getName())){
            return new Result(false, "");
        }

        Pickable sourcePickable = sourceEntity.getComponent(Pickable.class);
        Pickable destPickable = destEntity.getComponent(Pickable.class);

        int leftSpace = destPickable.getMaxStack() - destPickable.getStackSize();
        int picked = Math.min(leftSpace, sourcePickable.getStackSize());
        sourcePickable.changeStackSize(-picked);
        destPickable.changeStackSize(picked);

        if(sourcePickable.getStackSize() == 0){
            source.setEntity(null);
            sourceEntity.delete();
            return new Result(true, "");
        }

        return new Result(false, "");
    }

    public Entity removeItem(InventorySlot slot){
        Entity entity = slot.getEntity();
        slot.setEntity(null);
        return entity;
    }

    public Entity removeItem(Entity entity){
        for(InventorySlot s : slots){
            if(s.getEntity().equals(entity)){
                s.setEntity(null);
            }
        }
        return entity;
    }

    public ArrayList<InventorySlot> getSlots(){
        return this.slots;
    }

    public ArrayList<Entity> getEntities(){
        ArrayList<Entity> out = new ArrayList<>(slots.size());
        for(InventorySlot s : slots){
            if(s.getEntity()!=null){
                out.add(s.getEntity());
            }
        }
        return out;
    }
}
