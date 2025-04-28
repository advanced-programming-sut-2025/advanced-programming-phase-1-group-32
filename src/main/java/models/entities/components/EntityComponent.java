package models.entities.components;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/***
 * this helps jackson find child classes
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Edible.class, name = "Edible"),
        @JsonSubTypes.Type(value = Useable.class, name = "Useable"),
        @JsonSubTypes.Type(value = Harvestable.class, name = "Harvestable"),
        @JsonSubTypes.Type(value = Growable.class, name = "Growable"),
        @JsonSubTypes.Type(value = Inventory.class, name = "Inventory"),
        @JsonSubTypes.Type(value = Placeable.class, name = "Placeable"),
        @JsonSubTypes.Type(value = Processor.class, name = "Processor"),
        @JsonSubTypes.Type(value = Sellable.class, name = "Sellable"),
        @JsonSubTypes.Type(value = Upgradable.class, name = "Upgradable"),
        @JsonSubTypes.Type(value = Useable.class, name = "Useable"),
        @JsonSubTypes.Type(value = Pickable.class, name = "Pickable"),
        @JsonSubTypes.Type(value = Renderable.class, name = "Renderable"),
})
abstract public class EntityComponent {
}