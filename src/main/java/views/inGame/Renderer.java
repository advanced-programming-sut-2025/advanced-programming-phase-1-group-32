package views.inGame;

public class Renderer {
    private final String resetCode    = "\u001B[0m";
    private final String fgColorCode  = "\u001B[38;2;%d;%d;%dm";
    private final String bgColorCode  = "\u001B[48;2;%d;%d;%dm";
    private final String positionCode = "\u001B[%d;%dH";

    public void clear(){
        System.out.print("\u001B[2J"); // Clear screen
        System.out.print("\u001B[H");  // Move cursor to top-left
        System.out.flush();
    }
    public void mvAddch(int x, int y, int character){
        System.out.printf(positionCode + character + resetCode, y, x);
    }
    public void mvAddchColored(int x, int y, int character, Color color){
        double[] fg = color.getFg();
        double[] bg = color.getBg();
        System.out.printf(positionCode + fgColorCode + character + resetCode, y, x,
                         (int) (fg[0] * 256), (int) (fg[1] * 256), (int) (fg[2] * 256));
    }
}
