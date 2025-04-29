package models;

public class Position {
//    private float col, row;
    private int col, row; //changed it to integer

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

    public void change(int x, int y){
        this.col += x;
        this.row += y;
    }

    @Override
    public String toString() {
        return "<" + col + ", " + row + ">";
    }
}
