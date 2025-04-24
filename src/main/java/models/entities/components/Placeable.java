package models.entities.components;

public class Placeable extends EntityComponent{
    private final boolean isACollider;

    public Placeable(boolean isACollider) {
        this.isACollider = isACollider;
    }
    public Placeable(){
        this(false);
    }
}
