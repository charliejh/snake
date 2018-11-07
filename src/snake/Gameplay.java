package snake;

import snake.gameobjects.Apple;
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
    private boolean gamePaused, gameOver;
    private Timer gameplayTimer;

    public void start() {
        snake = new Snake();
        apple = new Apple();
        score = 0;
        gameplayTimer = new Timer(200, e -> flowOfGameplay());
        gameplayTimer.start();
    }

    private void flowOfGameplay() {
        if (!gamePaused && !gameOver) {
            snake.move();
            gameOver = snakeSelfCollision();
            if(isAppleEaten()) {
                moveApple();
                snake.extendSnake();
                score++;
            }
            repaint();
        }
    }

    private boolean isAppleEaten() {
        return snake.eatApple(apple);
    }

    private void moveApple() {
        Random random = new Random();
        int x = random.nextInt(20);
        int y = random.nextInt(20);
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
            drawPauseScreen(graphics, "Paused!", 290);
        }
        if (gameOver) {
            drawPauseScreen(graphics, "Game Over!", 240);
        }
    }

    private void drawBackground(Graphics graphics) {
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < 840; i += 40) graphics.drawLine(0, i, 800, i);
            for (int i = 0; i < 840; i += 40) graphics.drawLine(i, 0, i, 800);
    }

    private void drawScoreBoard(Graphics graphics) {
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Verdana", Font.PLAIN, 25));
        graphics.drawString("snake", 910, 40);
        graphics.drawString("By Charlie Harris", 840, 80);
        graphics.drawString("Score: " + score, 900, 140);
        graphics.setFont(new Font("Verdana", Font.PLAIN, 20));
        graphics.drawString("P to Pause Game", 863, 350);
        graphics.drawString("R to Resume Game", 850, 400);
    }

    private void drawPauseScreen(Graphics graphics, String text, int x) {
        graphics.setColor(new Color(0,0,0, 120));
        graphics.fillRect(0,0,20 * 40, 20 * 40);
        graphics.setColor(Color.white);
        graphics.setFont(new Font("Verdana", Font.BOLD, 50));
        graphics.drawString(text, x, 400);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (!gamePaused && !gameOver) {
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
            repaint();
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_P && !gameOver) {
            gamePaused = !gamePaused;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { }
    @Override
    public void keyTyped(KeyEvent e) { }

}
