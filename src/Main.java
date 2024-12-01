
import controllers.SnakeController;


public class Main {
    public static void main(String[] args) {
        System.out.println("Snake Game");
        SnakeController controller = new SnakeController();
        controller.start();
    }
}