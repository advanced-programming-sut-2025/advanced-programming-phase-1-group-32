package models.entities.components;
import com.fasterxml.jackson.annotation.*;
import models.entities.Entity;

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
    @JsonIgnore()
    protected Entity entity;

    // It's useful for components to have a reference to their owning entity,
    // since they may need to access other components on the same entity.
    // Avoid assigning the entity reference in the constructor, as it can
    // complicate serialization (e.g., when saving/loading from JSON).
    public void setEntity(Entity entity){
        this.entity = entity;
    }
}