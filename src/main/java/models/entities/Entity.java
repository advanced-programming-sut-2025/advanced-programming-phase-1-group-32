package models.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import models.entities.components.EntityComponent;
import models.enums.EntityTag;
import models.player.Player;

import java.awt.*;
import java.util.*;



/***
 * ignoreUnknown is for fields which are in json but are not in the class. this tells jackson to ignore them
 */
@JsonDeserialize(builder = Entity.Builder.class)
//@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Player.class, name = "player")
})
public class Entity implements Cloneable{
    private static int entityCounter = 1;

    //jsonProperty tells jackson to serialize and deserialize according to these names
    @JsonProperty("id")
    private final int id;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("components")
    private final ArrayList<EntityComponent> components = new ArrayList<>();
    @JsonProperty("tags")
    private final HashSet<EntityTag> tags = new HashSet<>();
    private final Set<EntityObserver> observers = new HashSet<>();

    public Entity(String name, ArrayList<EntityComponent> components, HashSet<EntityTag> tags, int id){
        if(name == null){
            throw new RuntimeException("The entity name is null");
        }
        this.name = name;

        if(components != null){
            for(EntityComponent c : components) {
                c.setEntity(this);
                this.components.add(c);
            }
        }

        if(tags != null){
            this.tags.addAll(tags);
        }

        if(id == 0){
            this.id = entityCounter;
            entityCounter++;
        }else{
            this.id = id;
        }
    }
    public Entity(String name, HashSet<EntityTag> tags, EntityComponent... components){
        this(name, new ArrayList<>(Arrays.asList(components)), tags, 0);
    }
    public Entity(String name, EntityComponent... components){
        this(name, null, components);
    }
    private Entity(Builder b){
        this(b.name, b.components, b.tags, b.id);
    }
    public ArrayList<EntityComponent> getComponents(){
        return components;
    }
    public <T extends EntityComponent> T getComponent(Class<T> componentClass) {
        for (EntityComponent c : components) {
            if (componentClass.isInstance(c)) {
                return (T) c;
            }
        }
        return null;
    }
    public <T extends EntityComponent> void addComponent(T component) {
        Class<? extends EntityComponent> clazz = component.getClass();
        if(this.getComponent(clazz) != null){
            return;
        }
        this.components.add(component);
    }

    public String getName() {
        return name;
    }

    public void addTag(EntityTag tag){
        tags.add(tag);
    }
    public boolean hasTag(EntityTag tag){
        return tags.contains(tag);
    }
    public Set<EntityTag> getTags(){
        return this.tags;
    }

    @Override
    public String toString() {
        String out = "";
        out += "name: " + name;
        out += "\nid: " + id;
        out += "\n  components:";
        for(EntityComponent c : components){
            out += "\n    " + c;
        }
        out+= "\n tags:";
        for(EntityTag t : tags){
            out += "\n    " + t;
        }
        return out;
    }

    @Override
    public Entity clone() {
        Entity entity = new Builder().name(this.name).id(0).tags(this.tags).build();
        for(EntityComponent c : this.components){
            EntityComponent clonedComponent = c.clone();
            clonedComponent.setEntity(entity);
            entity.addComponent(clonedComponent);
        }
        return entity;
    }

    /***
     * The builder. @jsonPojoBuilder tells jackson this is a builder (i guess so). the withPrefix tells jackson that
     * the name of setter functions in the builder have no prefix (they are basically the same as the field names)
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder{
        private String name;
        private String address;
        private int id;
        private ArrayList<EntityComponent> components = new ArrayList<>();
        private HashSet<EntityTag> tags = new HashSet<>();

        private void reset(){
            this.id = 0;
            this.name = null;
            this.tags.clear();
            this.components.clear();
        }

        public Builder name(String n)            { this.name = n; return this; }
        public Builder id(int n)                 { this.id = n; return this; }
        public Builder components(ArrayList<EntityComponent> c) { this.components.addAll(c); return this; }
        public Builder tags(HashSet<EntityTag> t) {this.tags.addAll(t); return this;}

        public Entity build(){
            Entity entity = new Entity(this);
            reset();
            return entity;
        }
    }

    public void addObserver(EntityObserver observer){
        this.observers.add(observer);
    }
    public void removeObserveer(EntityObserver observer){
        this.observers.remove(observer);
    }

    public void delete(){
        for(EntityObserver observer : observers){
            observer.onDelete(this);
        }
    }

    public boolean isTheSameAs(Entity other){
        if(!this.name.equals(other.name)) return false;

        for(EntityComponent c : this.components){
            EntityComponent c2 = other.getComponent(c.getClass());
            if(c2 == null) return false;

            if(!c2.isTheSame(c2))return false;
        }
        return true;
    }
}
