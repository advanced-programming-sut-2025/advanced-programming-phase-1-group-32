package models;

public class Position extends Vec2{
    Vec2 coordinate;

    public Position(int row, int col) {
        this.coordinate = new Vec2(row, col);
    }
    public Position(double row, double col) {
        this.coordinate = new Vec2(row, col);
    }

    public int getCol() {
        return coordinate.getCol();
    }

    public int getRow() {
        return coordinate.getRow();
    }

    public Position change(int x, int y){
        this.coordinate.add(x, y);
        return this;
    }
    public Position change(Position position){
        this.coordinate.add(position.getCol(), position.getRow());
        return this;
    }
    public Position multiply(double d){
        coordinate.multiply(d);
        return this;
    }

    @Override
    public String toString() {
        return "<" + getCol() + ", " + getRow() + ">";
    }

    public Position changeByDirection(String direction){
        return switch (direction) {
            case "left" -> {
                coordinate.add(-1, 0);
                yield this;
            }
            case "right" -> {
                coordinate.add(1, 0);
                yield this;
            }
            case "up" -> {
                coordinate.add(0, -1);
                yield this;
            }
            case "down" -> {
                coordinate.add(0, 1);
                yield this;
            }
            case "upleft" -> {
                coordinate.add(-1, -1);
                yield this;
            }
            case "upright" -> {
                coordinate.add(1, -1);
                yield this;
            }
            case "downleft" -> {
                coordinate.add(-1, 1);
                yield this;
            }
            case "downright" -> {
                coordinate.add(1, 1);
                yield this;
            }
            default -> null;
        };
    }
}
