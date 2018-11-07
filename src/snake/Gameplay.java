package snake;

import snake.gameobjects.Apple;
import snake.gameobjects.Block;
import snake.gameobjects.Snake;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Gameplay extends JComponent implements KeyListener {

    private Snake snake;
    private Apple apple;
    private int score;
    private boolean gamePaused, gameOver, nextMoveChosen;
    private Timer gameplayTimer;

    public void start() {
        snake = new Snake();
        apple = new Apple();
        score = 0;
        gameplayTimer = new Timer(180, e -> flowOfGameplay());
        gameplayTimer.start();
    }

    private void flowOfGameplay() {
        if (gameOver || gamePaused) {
            gameplayTimer.stop();
        }
        if (!gamePaused && !gameOver) {
            nextMoveChosen = false;
            snake.move();
            gameOver = snakeSelfCollision();
            snakePositionCheck();
            if(isAppleEaten()) {
                moveApple();
                snake.extendSnake();
                score++;
            }
            repaint();
        }
    }

    private void snakePositionCheck() {
        Block snakeHead = snake.getSnakeHead();
        if (snakeHead.getX() < 0) {
            snake.moveSnakeHead(Constants.FRAME_SIZE - 1, snakeHead.getY());
        }
        else if (snakeHead.getX() > Constants.FRAME_SIZE - 1) {
            snake.moveSnakeHead(0, snakeHead.getY());
        }
        else if (snakeHead.getY() < 0) {
            snake.moveSnakeHead(snakeHead.getX(), Constants.FRAME_SIZE - 1);
        }
        else if (snakeHead.getY() > Constants.FRAME_SIZE - 1) {
            snake.moveSnakeHead(snakeHead.getX(), 0);
        }
    }

    private boolean isAppleEaten() {
        return snake.eatApple(apple);
    }

    private void moveApple() {
        Random random = new Random();
        int x = random.nextInt(Constants.FRAME_SIZE);
        int y = random.nextInt(Constants.FRAME_SIZE);
        apple.move(x, y);
        if(isAppleEaten()) {
            moveApple();
        }
    }

    private boolean snakeSelfCollision() {
        return snake.selfCollision();
    }

    public void paintComponent(Graphics graphics) {
        drawBackground(graphics);
        drawScoreBoard(graphics);
        apple.draw(graphics);
        snake.draw(graphics);
        if (gamePaused) {
            drawPauseScreen(graphics, "Paused!", 190);
        }
        if (gameOver) {
            drawPauseScreen(graphics, "Game Over!", 140);
        }
    }

    private void drawBackground(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        for (int i = 0; i < Constants.FRAME_SIZE * Constants.BLOCK_SIZE + Constants.BLOCK_SIZE; i += Constants.BLOCK_SIZE) {
            graphics.drawLine(0, i, Constants.FRAME_SIZE * Constants.BLOCK_SIZE, i);
        }
        for (int i = 0; i < Constants.FRAME_SIZE * Constants.BLOCK_SIZE + Constants.BLOCK_SIZE; i += Constants.BLOCK_SIZE) {
            graphics.drawLine(i, 0, i, Constants.FRAME_SIZE * Constants.BLOCK_SIZE);
        }
    }

    private void drawScoreBoard(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Verdana", Font.PLAIN, 25));
        graphics.drawString("snake", Constants.FRAME_SIZE * Constants.BLOCK_SIZE + 110, 40);
        graphics.drawString("By Charlie Harris", Constants.FRAME_SIZE * Constants.BLOCK_SIZE + 40, 80);
        graphics.drawString("Score: " + score, Constants.FRAME_SIZE * Constants.BLOCK_SIZE + 100, 140);
        graphics.setFont(new Font("Verdana", Font.PLAIN, 20));
        graphics.drawString("P to Pause Game", Constants.FRAME_SIZE * Constants.BLOCK_SIZE + 63, 350);
    }

    private void drawPauseScreen(Graphics graphics, String text, int x) {
        graphics.setColor(new Color(0,0,0, 120));
        graphics.fillRect(0,0,Constants.FRAME_SIZE * Constants.BLOCK_SIZE, Constants.FRAME_SIZE * Constants.BLOCK_SIZE);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Verdana", Font.BOLD, 50));
        graphics.drawString(text, x, Constants.FRAME_SIZE * Constants.BLOCK_SIZE / 2);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (!gamePaused && !gameOver && !nextMoveChosen) {
            switch (keyEvent.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if(!snake.getFacedDirection().equals(Direction.DOWN)) {
                        snake.changeFacedDirection(Direction.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(!snake.getFacedDirection().equals(Direction.UP)) {
                        snake.changeFacedDirection(Direction.DOWN);
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(!snake.getFacedDirection().equals(Direction.RIGHT)) {
                        snake.changeFacedDirection(Direction.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(!snake.getFacedDirection().equals(Direction.LEFT)) {
                        snake.changeFacedDirection(Direction.RIGHT);
                    }
                    break;
            }
            nextMoveChosen = true;
            repaint();
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_P && !gameOver) {
            gameplayTimer.start();
            gamePaused = !gamePaused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }

}
