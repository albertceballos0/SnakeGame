import controllers.SnakeController;

public class Main {
    public static void main(String[] args) {
        SnakeController controller = new SnakeController(30, 30);
        controller.startGame();
    }
}