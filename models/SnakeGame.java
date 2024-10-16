package models;

import java.util.Random;

public class SnakeGame {
    private SnakeBoard board;
    private SnakePlayer player;
    private SnakeFood food;
    private boolean isGameOver;
    private int score;
    private String playerName;

    public SnakeGame(String playerName, int width, int height) {
    
        this.board = new SnakeBoard(width, height);
        this.player = new SnakePlayer(width / 2, height / 2);
        this.food = new SnakeFood(0, 0);
        this.isGameOver = false;
        this.score = 0;
        placeFood();
        this.playerName = playerName;


    }

    public void restart() {
        this.player = new SnakePlayer(board.getWidth() / 2, board.getHeight() / 2);
        this.food = new SnakeFood(0, 0);
        this.isGameOver = false;
        this.score = 0;
        placeFood();
    }

    public void update() {
        if (!isGameOver) {
            player.move();
            checkCollision();
            checkFood();
        }
    }

    private void checkCollision() {
        if (player.collidesWithWall(board) || player.collidesWithSelf()) {
            isGameOver = true;
        }
    }

    private void checkFood() {
        if (player.getX() == food.getX() && player.getY() == food.getY()) {
            player.grow();
            score++;
            placeFood();
        }
    }

    private void placeFood() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(board.getWidth());
            y = rand.nextInt(board.getHeight());
        } while (player.occupies(x, y));
        food.setPosition(x, y);
    }

    // Getters and setters
    public SnakeBoard getBoard() { return board; }
    public SnakePlayer getPlayer() { return player; }
    public SnakeFood getFood() { return food; }
    public boolean isGameOver() { return isGameOver; }
    public int getScore() { return score; }
    public String getPlayerName() { return playerName; }

}