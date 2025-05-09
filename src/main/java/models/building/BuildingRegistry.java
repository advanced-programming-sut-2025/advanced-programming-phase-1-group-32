package models.building;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.entities.Registry;

import java.io.IOException;
import java.nio.file.Path;

public class BuildingRegistry extends Registry <BuildingData> {
    @Override
    public void loadJson(JsonNode jsonRoot, ObjectMapper mapper, Path path) throws IOException {
        BuildingData[] buildings = mapper.treeToValue(jsonRoot, BuildingData[].class);
    }
}
