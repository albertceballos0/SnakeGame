package src.models;

import java.util.LinkedList;

public class SnakePlayer {
    private LinkedList<int[]> snake;
    private int[] direction;
    private String name;

    public SnakePlayer(String name) {
        this.snake = new LinkedList<>();
        this.snake.add(new int[]{5, 5}); // Poner la serpiente en una posición inicial
        this.direction = new int[]{0, 1}; // Direccion inicial derecha
        this.name = name;
    }

    public int[] getFirstSnake() {
        return snake.getFirst();
    }

    public int[] getDirection() {
        return direction;
    }

    public void setDirection(int[] newDirection) {
        // Evitar que la serpiente vaya en la dirección opuesta directamente
        if ((newDirection[0] != -direction[0] || newDirection[1] != -direction[1])) {
            this.direction = newDirection;
        }
    }

    public boolean snakeContains(int[] pos) {
        return snake.stream().anyMatch(part -> part[0] == pos[0] && part[1] == pos[1]);
    }

    public void addFirstSnake(int[] pos) {
        snake.addFirst(pos);
    }

    public void removeLastSnake() {
        snake.removeLast();
    }

    public LinkedList<int[]> getSnakeBody() {
        return snake;
    }
}
