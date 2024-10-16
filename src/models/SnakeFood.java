package src.models;
import java.util.Random;

public class SnakeFood {

    //Board widht and height
    private int boardWidth, boardHeight;

    //random para conseeguir nuevas posiciones
    private Random random;

    //posicion de la comida
    private int width, height;


    private void getRandomPosition() {
        width = random.nextInt(boardWidth);
        height =  random.nextInt(boardHeight);
    }
}
