package snake.gameobjects;

import snake.Constants;
import java.awt.*;

public class Apple {

    private int  x, y;

    public Apple() {
        this.x = Constants.APPLE_X_START_POSITION;
        this.y = Constants.APPLE_Y_START_POSITION;
    }

    public void move(int pX, int pY) {
        this.x = pX;
        this.y = pY;
    }

    public void draw(Graphics pGraphics) {
        pGraphics.setColor(Constants.APPLE_COLOUR);
        pGraphics.fillOval(x * Constants.BLOCK_SIZE, y * Constants.APPLE_SIZE, Constants.APPLE_SIZE, Constants.APPLE_SIZE);
        pGraphics.setColor(Color.BLACK);
        pGraphics.drawOval(x * Constants.APPLE_SIZE, y * Constants.APPLE_SIZE, Constants.APPLE_SIZE, Constants.APPLE_SIZE);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
