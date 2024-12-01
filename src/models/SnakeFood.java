package models;

public class SnakeFood {
    private int x;
    private int y;

    public SnakeFood(int x, int y) {
        // Design by contract
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordenades de la Food no poden ser negatives");
        }
        this.x = x;
        this.y = y;
    }

    // Getters
    public int getX() { return x; }
    public int getY() { return y; }
}
