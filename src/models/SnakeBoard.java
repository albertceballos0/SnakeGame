package src.models;
import java.util.LinkedList;

public class SnakeBoard {

    private int width, height;
    private SnakeFood food;   

    public SnakeBoard(int width, int height) {
        this.width = width;
        this.height = height;
        this.food = new SnakeFood();

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SnakeFood getFood() {
        return food;
    }   

    public void setFood(SnakeFood food) {
        this.food = food;
    }
}
