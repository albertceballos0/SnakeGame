package models;

import java.util.List;
import java.util.Random;

public final class SnakeGame {
    private final SnakeBoard board;
    private final SnakePlayer player;
    private boolean isGameOver;
    private int score;
    private final String playerName;
    private final int difficulty;

    public SnakeGame(String playerName, int width, int height, int difficulty) {
    
        this.board = new SnakeBoard(width, height);
        this.player = new SnakePlayer(width / 2, height / 2);
        this.isGameOver = false;
        this.score = 0;
        this.playerName = playerName;
        this.difficulty = difficulty;
        setObstacles(difficulty);
    }

    public void restart() {
        player.restart(board.getWidth() / 2, board.getHeight() / 2);
        board.clearObstacles();
        setObstacles(difficulty);
        placeFood();
        isGameOver = false;
        score = 0;
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
        if (board.isObstacle(player.getX(), player.getY())) {
            isGameOver = true;
        }
    }
    private void checkFood() {
        if (board.isFood(player.getX(), player.getY())) {
            player.grow();
            score += 10*difficulty;
            placeFood();
        }
    }

    private void placeFood() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(board.getWidth());
            y = rand.nextInt(board.getHeight());
        } while (player.occupies(x, y) || board.isObstacle(x, y) || board.isFood(x, y));
        board.setFood(x, y);
    }

    private void generateObstacle() {
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(board.getWidth());
            y = rand.nextInt(board.getHeight());
        } while (player.occupies(x, y) || board.isObstacle(x, y) || board.isFood(x, y));
        board.setObstacle(x, y);
    }

    public void setObstacles(int numObstacles) {
        for (int i = 0; i < numObstacles; i++) {
            generateObstacle();
        }
    }


    // Getters and setters
    public SnakeBoard getBoard() { return board; }
    public SnakePlayer getPlayer() { return player; }
    public SnakeFood getFood() { return board.getFood(); }
    public List<SnakeObstacle> getObstacles() { return board.getObstacles(); }
    public boolean isGameOver() { return isGameOver; }
    public int getScore() { return score; }
    public String getPlayerName() { return playerName; }

}