package views.inGame;

public class Color {
    private double[] bg = {1, 1, 1};
    private double[] fg = {1, 1, 1};

    public double[] getBg() {
        return bg;
    }

    public void setBg(double[] bg) {
        this.bg = bg;
    }

    public double[] getFg() {
        return fg;
    }

    public void setFg(double[] fg) {
        this.fg = fg;
    }

    public Color(double[] fg, double[] bg){
        this.fg = fg;
        this.bg = bg;
    }
    public Color(double r, double g, double b){
        this.fg[0] = r;
        this.fg[1] = g;
        this.fg[2] = b;
    }
}
