package views.inGame;

public class Color {
    private int[] bg = {255, 255, 255};
    private int[] fg = {255, 255, 255};

    public int[] getBg() {
        return bg;
    }

    public void setBg(int[] bg) {
        this.bg = bg;
    }

    public int[] getFg() {
        return fg;
    }

    public void setFg(int[] fg) {
        this.fg = fg;
    }

    public Color(int[] fg){
        this.fg = fg;
    }
    public Color(int r, int g, int b){
        this.fg[0] = r;
        this.fg[1] = g;
        this.fg[2] = b;
    }

    public boolean equals(Color c2) {
        if(c2 == null) return false;
        return (this.fg[0] == c2.fg[0]) && (this.fg[1] == c2.fg[1]) && (this.fg[2] == c2.fg[2]);
    }
}
