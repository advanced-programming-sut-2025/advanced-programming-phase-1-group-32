package models.gameMap;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.entities.Registry;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;

public class MapRegistry extends Registry<MapData> implements Serializable {
    private static class MapNode{
        @JsonProperty("name")
        public String name;
        @JsonProperty("path")
        public String path;

        public MapNode() {
        }
    }
    @Override
    public void loadJson(JsonNode jsonRoot, ObjectMapper mapper, Path path) throws IOException {
        String name = path.toString().substring(path.toString().lastIndexOf("/") + 1);
        name = name.substring(0, name.lastIndexOf("."));
        this.registry.putIfAbsent(name , MapData.parse(path.toString()));
    }

    public MapData getData(String name){
        MapData data = this.registry.get(name);
        if(data == null){
            throw new RuntimeException("no map with name " + name + " exists");
        }
        return data;
    }
}
