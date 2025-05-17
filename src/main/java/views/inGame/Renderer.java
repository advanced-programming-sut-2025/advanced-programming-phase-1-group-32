package views.inGame;

import models.App;
import models.Position;
import views.AppView;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;


public class Renderer implements Serializable {
    private final String resetCode = "\u001B[0m";
    private final String fgColorCode = "\u001B[38;2;%d;%d;%dm";
    private final String bgColorCode = "\u001B[48;2;%d;%d;%dm";
    private final String positionCode = "\u001B[%d;%dH";
    private CharacterTexture frameBuffer;
    private final AppView view;
    private int curX = 0, curY = 0;

    public Renderer(AppView view) {
        this.view = view;
        this.frameBuffer = new CharacterTexture(view.getTerminalWidth(), view.getTerminalHeight());
        this.updateSize();
    }

    public CharacterTexture getFrameBuffer() {
        return frameBuffer;
    }

    public void updateSize() {
        this.frameBuffer.updateSize(view.getTerminalWidth(), view.getTerminalHeight());
    }

    public void clear() {
        this.frameBuffer.reset();
    }


    public void mvAddchColored(int x, int y, char character, Color color) {
        this.mvAddchColored(x, y, character, color, view.getTerminalWidth() / 2, view.getTerminalHeight() / 2);
    }

    public void mvAddchColored(int x, int y, char character, Color color, int x0, int y0) {
        x = x - (x0 - (view.getTerminalWidth() / 2));
        y = y - (y0 - (view.getTerminalHeight() / 2));

        if (x < 0 || x >= view.getTerminalWidth() || y < 0 || y >= view.getTerminalHeight()) {
            return;
        }
        int[] fg = color.getFg();
        this.frameBuffer.setCharacter(x, y, character, color);
    }

    /***
     *
     * @param cameraPos The center of the camera with respect to world origin. It'll probably be the position of the player, but it could be anything.
     */
    public void mvAddchColored(int x, int y, char character, Color color, Position cameraPos) {
        x = x - (cameraPos.getCol() - (view.getTerminalWidth() / 2));
        y = y - (cameraPos.getRow() - (view.getTerminalHeight() / 2));

        if (x < 0 || x >= view.getTerminalWidth() || y < 0 || y >= view.getTerminalHeight()) {
            return;
        }
        int[] fg = color.getFg();
        this.frameBuffer.setCharacter(x, y, character, color);
    }

    public void mvPrint(int x, int y, String str, Color color, Position cameraPos) {
        for (int i = 0; i < str.length(); i++) {
            mvAddchColored(x + i, y, str.charAt(i), color, cameraPos);
        }
    }

    private void renderTexture(int x, int y, CharacterTexture texture) {
        int x1, x2, y1, y2;

        x1 = Math.max(0, x);
        x2 = Math.min(frameBuffer.width - 1, x + texture.width);
        y1 = Math.max(0, y);
        y2 = Math.min(frameBuffer.height - 1, y + texture.height);

        if (x1 > x2 || y1 > y2) return;

        int xOffset = x1 - x, yOffset = y1 - y;
        x1 = x1 - x;
        x2 = x2 - x;
        y1 = y1 - y;
        y2 = y2 - y;

        for (int i = x1; i < x2; i++) {
            for (int j = y1; j < y2; j++) {
                this.frameBuffer.data[y + j][x + i] = texture.data[j][i];
            }
        }
    }

    public void moveCurser(int x, int y) {
        System.out.printf(positionCode, x, y);
    }

    public void render() {
        StringBuilder output = new StringBuilder();
        StyledCharacter tmp;
        Color prevColor = null;
        int[] curColor = null;
        output.append("\033[H");
        for (int i = 0; i < frameBuffer.height; i++) {
            if (i != 0) {
                output.append(String.format(resetCode + ""));
            }
            for (int j = 0; j < frameBuffer.width; j++) {
                tmp = frameBuffer.data[i][j];
                if (tmp == null) {
                    output.append(" ");
                } else {
                    if (tmp.color == null) {
                        if (prevColor == null) {
                            output.append(tmp.character);
                        } else {
                            output.append(resetCode + tmp.character);
                            prevColor = null;
                        }
                    } else {
                        curColor = tmp.color.getFg();
                        if ((prevColor != null) && prevColor.equals(tmp.color)) {
                            output.append(String.format(fgColorCode + tmp.character, curColor[0], curColor[1], curColor[2]));
                        } else {
                            prevColor = tmp.color;
                            output.append(String.format(resetCode + fgColorCode + tmp.character, curColor[0], curColor[1], curColor[2]));
                        }
                    }
                }
            }
        }
        System.out.print(output + resetCode);
    }
}
