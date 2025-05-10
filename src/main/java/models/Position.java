package models;

import java.io.Serializable;

public class Position implements Serializable {
//    private float col, row;
    private int col, row; //changed it to integer


    public Position() {

    }

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public Position change(int x, int y){
        this.col += x;
        this.row += y;
        return this;
    }
    public Position change(Position position){
        this.change(position.col, position.row);
        return this;
    }
    public Position multiply(double d){
        col = (int) (d * col);
        row = (int) (d * row);
        return this;
    }

    @Override
    public String toString() {
        return "<" + col + ", " + row + ">";
    }

    public Position changeByDirection(String direction){
        Position position = new Position(row, col);
        switch(direction){
            case "left":
                position.col -= 1;
                return position;

            case "right":
                position.col += 1;
                return position;

            case "up":
                position.row -= 1;
                return position;

            case "down":
                position.row += 1;
                return position;

            case "upleft":
                position.col -= 1;
                position.row -= 1;
                return position;

            case "upright":
                position.col += 1;
                position.row -= 1;
                return position;

            case "downleft":
                position.col -= 1;
                position.row += 1;
                return position;

            case "downright":
                position.col += 1;
                position.row += 1;
                return position;
        }
        return null;
    }
}
