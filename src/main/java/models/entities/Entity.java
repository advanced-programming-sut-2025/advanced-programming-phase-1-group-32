package models.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import models.entities.components.EntityComponent;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

/***
 * ignoreUnknown is for fields which are in json but are not in the class. this tells jackson to ignore them
 */
@JsonDeserialize(builder = Entity.Builder.class)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property  = "id")
public class Entity implements Cloneable{
    private static int entityCounter = 1;

    //jsonProperty tells jackson to serialize and deserialize according to these names
    @JsonProperty("id")
    private final int id;
    @JsonProperty("name")
    private final String name;
    @JsonProperty("components")
    private ArrayList<EntityComponent> components = new ArrayList<>();

    public Entity(String name, ArrayList<EntityComponent> components, int id){
        this.name = name;
        this.components.addAll(components);

        if(id == 0){
            this.id = entityCounter;
            entityCounter++;
        }else{
            this.id = id;
        }
    }
    public Entity(String name, String address, EntityComponent... components){
        this(name, new ArrayList<>(Arrays.asList(components)), 0);
    }
    private Entity(Builder b) {
        this(b.name, b.components, b.id);
    }

    public String getName() {
        return name;
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

    @Override
    public String toString() {
        String out = "";
        out += "name: " + name;
        out += "\nid: " + id;
        out += "\n  components:";
        for(EntityComponent c : components){
            out += "\n    " + c;
        }
        return out;
    }

    /***
     * The builder. @jsonPojoBuilder tells jackson this is a builder (i guess so). the withPrefix tells jackson that
     * the name of setter functions in the builder have no prefix (they are basically the same as the field names)
     */
    @JsonPOJOBuilder(withPrefix = "")
    public static class Builder{
        String name;
        String address;
        ArrayList<EntityComponent> components;
        int id;

        public Builder name(String n)            { this.name = n; return this; }
        public Builder id(int n)                 { this.id = n; return this; }
        public Builder components(ArrayList<EntityComponent> c) { this.components = c; return this; }

        public Entity build(){
            Entity entity = new Entity(this);
            this.id = 0;
            return entity;
        }
    }
    public Entity clone(){
        return new Entity(this.name, this.components, 0);
    }
}
