package src.models;
import java.util.Random;

public class SnakeFood {

    //random para conseeguir nuevas posiciones
    private Random random;
    //posicion de la comida
    private int width, height;

    public SnakeFood() {
        this.random = new Random();
        this.width = this.random.nextInt(width);
        this.height = this.random.nextInt(height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void generateFood() {
        this.width = this.random.nextInt(width);
        this.height = this.random.nextInt(height);
    }

}
