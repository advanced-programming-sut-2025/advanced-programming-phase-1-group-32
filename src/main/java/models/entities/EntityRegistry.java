package models.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
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

public class EntityRegistry {
    private final Map<String, Entity> registry = new HashMap<>();

    public void loadEntities(String pathStr){
        Path root = Paths.get(pathStr);
        if(root == null){
            throw new RuntimeException("Failed to open the entities data folder! ("+pathStr+")");
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try (Stream<Path> files = Files.walk(root)) {
            files.filter(p -> p.toString().endsWith(".json")).forEach(path -> {
                        String regAddress = root.relativize(path).toString().replace(File.separator, ":");
                        regAddress = regAddress.substring(0, regAddress.lastIndexOf(":"));

                        try {
                            JsonNode jsonRoot = mapper.readTree(path.toFile());

                            ArrayList<Entity> entities = new ArrayList<>();
                            for(JsonNode n : jsonRoot.get("entities")){
                                String name = n.get("name").toString();
                                try {
                                    entities.add(mapper.treeToValue(n, Entity.class));
                                } catch (RuntimeException e) {
                                    System.err.println(name);
                                    throw new RuntimeException(e);
                                }
                            }
                            EntityComponent[] commonComponents = mapper.treeToValue(jsonRoot.get("common components"),
                                                                                                  EntityComponent[].class);
                            String[] requiredComponents = mapper.treeToValue(jsonRoot.get("required components"),
                                                                                                    String[].class);
                            EntityTag[] commonTags = mapper.treeToValue(jsonRoot.get("common tags"),
                                                                                    EntityTag[].class);
                            if(entities == null){
                                throw new RuntimeException("The structure of entity data file is invalid! (" + path.toString() + ")");
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
                        } catch (IOException e) {
                            System.err.println("----------------------------------------------------------------------");
                            System.err.println("Error in reading " + path);
                            System.err.println("----------------------------------------------------------------------\nlogs:");
                            throw new RuntimeException(e);
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public boolean doesEntityExist(String entityName){
        Entity entity = this.getEntityDetails(entityName);
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
