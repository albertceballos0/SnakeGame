package models;
import java.util.ArrayList;
import java.util.List;

public class SnakeBoard {

    private final int width;
    private final int height;

    private SnakeFood food;
    private final List<SnakeObstacle> obstacles;


    public SnakeBoard(int width, int height) {
        // Design by contract
        if (width <= 0 || height <= 0 || width >= 500 || height >= 500) {
            throw new IllegalArgumentException("Dimensions de la Board han de ser positives i menors a 500");
        }
        this.width = width;
        this.height = height;
        this.obstacles = new ArrayList<>();
        this.food = new SnakeFood(0, 0);

    }


    public void clearObstacles() {
        obstacles.clear();
    }

    public boolean  isObstacle(int x, int y) {
        // No es necessari design by contract
        for (SnakeObstacle obstacle : obstacles) {
            if (obstacle.getX() == x && obstacle.getY() == y) {
                return true;
            }
        }
        return false;
    }

    public void setObstacle(int x,int y) {
        // Design by contract
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Coordenades de l'Obstacle han d'estar dintre de la Board");
        }
        obstacles.add(new SnakeObstacle(x, y));   
    }

    public boolean isFood(int x, int y) {
        // No es necessari design by contract
        return food.getX() == x && food.getY() == y;
    }

    public void setFood(int x, int y) {
        // Design by contract
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("Coordenades de la Food han d'estar dintre de la Board");
        }
        food = new SnakeFood(x, y);
    }
    // Getters
    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getNumObstacles() { return obstacles.size(); }

    public SnakeFood getFood() {
        return food;
    }
    public List<SnakeObstacle> getObstacles() {
        return obstacles;
    }
}