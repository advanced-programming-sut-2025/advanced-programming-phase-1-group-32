package models.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import models.entities.components.*;
import models.enums.EntityTag;
import models.enums.Material;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class EntityRegistry extends Registry<Entity>{

    @Override
    public void loadJson(JsonNode jsonRoot, ObjectMapper mapper, Path path) throws IOException {
        ArrayList<Entity> entities = new ArrayList<>();
        if(jsonRoot.get("entities") == null){
            throw new RuntimeException("The structure of entity data file is invalid! (" + path.toString() + ")");
        }
        for(JsonNode n : jsonRoot.get("entities")){
            if(n.get("name") == null){
                throw new RuntimeException("an entity in the file " + path.toString() + "has no name");
            }
            String name = n.get("name").asText();
            try {
                entities.add(mapper.treeToValue(n, Entity.class));
            }catch (JsonProcessingException e) {
                System.err.println("there was a problem in the entity " + name + ", file " + path.toString() + "\n");
                throw new RuntimeException(e);
            }
        }
        EntityTag[] commonTags              = null;
        String[] requiredComponents         = null;
        EntityComponent[] commonComponents  = null;

        if(jsonRoot.get("common components") != null){
            commonComponents = mapper.treeToValue(jsonRoot.get("common components"),EntityComponent[].class);
        }
        if(jsonRoot.get("required components") != null){
            requiredComponents = mapper.treeToValue(jsonRoot.get("required components"), String[].class);
        }
        if(jsonRoot.get("common tags") != null){
            commonTags = mapper.treeToValue(jsonRoot.get("common tags"), EntityTag[].class);
        }

        for(Entity e : entities){
            if(commonComponents != null){
                for(EntityComponent c : commonComponents){
                    e.addComponent(c);
                }
            }
            if(requiredComponents != null){
                for(String c : requiredComponents){
                    boolean found = false;
                    for(EntityComponent ec : e.getComponents()){
                        if(ec.getClass().getSimpleName().equalsIgnoreCase(c)){
                            found = true;
                            break;
                        }
                    }
                    if(!found){
                        throw new RuntimeException("The entity \"" + e.getName() +"\n in the data file " + path + " doesn't have the" +
                                "required component: " + c.getClass());
                    }
                }
            }
            if(commonTags != null){
                for(EntityTag t : commonTags){
                    e.addTag(t);
                }
            }
            this.registry.putIfAbsent(e.getName().toLowerCase(), e);
        }
    }

    @Override
    public Entity getData(String name) {
        return registry.get(name);
    }

    public boolean doesEntityExist(String entityName){
        Entity entity = this.registry.get(entityName.toLowerCase());
        return entity != null;
    }

    public Entity makeEntity(String name){
        Entity entity = this.registry.get(name.toLowerCase());
        if(entity == null){
            throw new RuntimeException("no entity found with the name " + name);
        }
        return entity.clone();
    }
    public Entity getEntityDetails(String name){
        Entity entity = this.registry.get(name.toLowerCase());
        if(entity == null){
            throw new RuntimeException("no entity found with the name " + name);
        }
        return entity;
    }
    public void listEntities(){
        for(Map.Entry<String, Entity> e : this.registry.entrySet()){
            System.out.println("--------------\n" + e.getValue());
        }
    }
}
