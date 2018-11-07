package snake.gameobjects;

import snake.Constants;
import snake.Direction;

import java.awt.*;
import java.util.ArrayList;

public class Snake {

    private ArrayList<Block> snakeBodyParts;
    private Direction facedDirection;

    public Snake() {
        snakeBodyParts = new ArrayList<>();
        snakeBodyParts.add(new Block(Constants.SNAKE_X_START_POSITION, Constants.SNAKE_Y_START_POSITION, Constants.SNAKE_HEAD_COLOUR));
        snakeBodyParts.add(new Block(Constants.SNAKE_X_START_POSITION, Constants.SNAKE_Y_START_POSITION + 1, Constants.SNAKE_BODY_COLOUR));
        snakeBodyParts.add(new Block(Constants.SNAKE_X_START_POSITION, Constants.SNAKE_Y_START_POSITION + 2, Constants.SNAKE_BODY_COLOUR));
        facedDirection = Direction.UP;
    }

    public void move() {
        for (int i = snakeBodyParts.size() - 1; i > 0; i--) {
            snakeBodyParts.get(i).moveToOtherBlock(snakeBodyParts.get(i - 1));
        }
        snakeBodyParts.get(0).move(facedDirection);
    }

    public void extendSnake() {
        snakeBodyParts.add(new Block(snakeBodyParts.get(snakeBodyParts.size() - 1).getX(), snakeBodyParts.get(snakeBodyParts.size() - 1).getY(), Constants.SNAKE_BODY_COLOUR));
    }

    public void changeFacedDirection(Direction pDirection) {
        facedDirection = pDirection;
    }

    public boolean selfCollision() {
        for (int i = 0; i < snakeBodyParts.size(); i++) {
            for (int j = i + 1; j < snakeBodyParts.size(); j++) {
                if (snakeBodyParts.get(i).getX() == snakeBodyParts.get(j).getX() &&
                        snakeBodyParts.get(i).getY() == snakeBodyParts.get(j).getY()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean eatApple(Apple pApple) {
        for (Block bodyPart : snakeBodyParts) {
            if(bodyPart.getX() == pApple.getX() && bodyPart.getY() == pApple.getY()) {
                return true;
            }
        }
        return false;
    }

    public void draw(Graphics pGraphics) {
        snakeBodyParts.forEach(bodyPart -> bodyPart.draw(pGraphics));
    }

    public Direction getFacedDirection() {
        return facedDirection;
    }
}
