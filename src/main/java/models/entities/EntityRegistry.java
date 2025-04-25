package models.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.entities.components.*;
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

                            Entity[] entities = mapper.treeToValue(jsonRoot.get("entities"), Entity[].class);
                            EntityComponent[] commonComponents = mapper.treeToValue(jsonRoot.get("common components"),
                                                                                                  EntityComponent[].class);
                            EntityComponent[] requiredComponents = mapper.treeToValue(jsonRoot.get("required components"),
                                                                                                    EntityComponent[].class);

                            if(entities == null){
                                throw new RuntimeException("The structure of entity data file is invalid! (" + path.toString() + ")");
                            }
                            for(Entity e : entities){
                                for(EntityComponent c : commonComponents){
                                    e.addComponent(c);
                                }
                                for(EntityComponent c : requiredComponents){
                                    if(e.getComponent(c.getClass()) == null){
                                        throw new RuntimeException("The entity \"" + e.getName() +"\n in the data file " + path + " doesn't have the" +
                                                "required component: " + c.getClass());
                                    }
                                }
                                this.registry.putIfAbsent(e.getName(), e);
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public Entity makeEntity(String name){
        Entity entity = this.registry.get(name);
        if(entity == null){
            throw new RuntimeException("no entity found with the name " + name);
        }
        return entity.clone();
    }
    public void listEntities(){
        for(Map.Entry<String, Entity> e : this.registry.entrySet()){
            System.out.println("--------------\n" + e.getValue());
        }
    }
}
