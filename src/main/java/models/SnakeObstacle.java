package models;

public class SnakeObstacle {
    //private int width, height;

    private int x = 0;
    private int y = 0;
    
    public SnakeObstacle(int x, int y) {
        // Design by contract
        if (x < 0 || y < 0) {
            throw new IllegalArgumentException("Coordenades de l'Obstacle no poden ser negatives");
        }
        this.x = x;
        this.y = y;
    }

    //getters
    public int getX() { return x; }
    public int getY() { return y; }
}
