package src.models;

import java.util.LinkedList;

public class SnakeGame {
    private SnakePlayer player;
    private SnakeBoard board;
    private int score;
    private boolean gameOver;

    public SnakeGame(String playerName, int width, int height) {
        this.player = new SnakePlayer(playerName);
        this.board = new SnakeBoard(width, height);
        this.score = 0;
        this.gameOver = false;
    }

    public boolean moveSnake() {
        if (gameOver) return false;

        int[] head = player.getFirstSnake();
        int[] newHead = new int[]{head[0] + player.getDirection()[0], head[1] + player.getDirection()[1]};

        // Verifica si choca con los bordes o consigo misma
        if (newHead[0] < 0 || newHead[0] >= board.getWidth() || newHead[1] < 0 || newHead[1] >= board.getHeight() || player.snakeContains(newHead)) {
            gameOver = true;
            return false;
        }

        player.addFirstSnake(newHead);

        // Verifica si come la comida
        if (newHead[0] == board.getFood().getWidth() && newHead[1] == board.getFood().getHeight()) {
            SnakeFood food = new SnakeFood();
            while (player.snakeContains(new int[]{food.getWidth(), food.getHeight()})) {
                food.generateFood();
            }
            board.setFood(food);
            score += 10;
        } else {
            player.removeLastSnake();
        }

        return true;
    }

    public void changeDirection(int[] newDirection) {
        player.setDirection(newDirection);
    }

    public SnakePlayer getPlayer() {
        return player;
    }

    public SnakeBoard getBoard() {
        return board;
    }

    public int getScore() {
        return score;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public LinkedList<int[]> getSnake() {
        return player.getSnakeBody();
    }

    public int[] getFood() {
        return new int[]{board.getFood().getWidth(), board.getFood().getHeight()};
    }
}
