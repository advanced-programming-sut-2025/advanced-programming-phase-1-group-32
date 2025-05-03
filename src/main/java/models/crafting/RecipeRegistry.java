package models.crafting;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import models.entities.Entity;
import models.entities.components.EntityComponent;
import models.enums.EntityTag;
import records.Result;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

public class RecipeRegistry {
    private final Map<String, Recipe> registry = new HashMap<>();

    public void loadRecipes(String pathStr) {
        Path root = Paths.get(pathStr);

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        try (Stream<Path> files = Files.walk(root)) {
            files.filter(p -> p.toString().endsWith(".json")).forEach(path -> {
                String regAddress = root.relativize(path).toString().replace(File.separator, ":");
                regAddress = regAddress.substring(0, regAddress.lastIndexOf(":"));

                try {
                    JsonNode jsonRoot = mapper.readTree(path.toFile());

                    Recipe[] recipes = mapper.treeToValue(jsonRoot.get("recipes"), Recipe[].class);
                    if(recipes == null){
                        throw new RuntimeException("The structure of recipe data file is invalid! (" + path.toString() + ")");
                    }
                    for(Recipe e : recipes){

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
}
