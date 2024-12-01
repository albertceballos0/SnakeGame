package tests;

import models.SnakePlayer;
import mock_classes.SnakeBoardMock;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class SnakePlayerTest {
		
    @Test
    public void testInitialization() {
        SnakePlayer snake = new SnakePlayer(5, 5);
        assertEquals(5, snake.getX(), "Posicio inicial de x incorrecta");
        assertEquals(5, snake.getY(), "Posicio inicial de y incorrecta");
        assertEquals(1, snake.getBody().size(), "Cos de la serp ha de ser igual a 1 al inicialitzar-se");
    }

    @Test
    public void testMove() {
        SnakePlayer snake = new SnakePlayer(5, 5);
        snake.move();
        assertEquals(6, snake.getX(), "Move dreta ha fallat");
        assertEquals(5, snake.getY(), "Move dreta ha fallat");

        snake.setDirection(0); // up
        snake.move();
        assertEquals(6, snake.getX(), "Move up ha fallat");
        assertEquals(4, snake.getY(), "Move up ha fallat");

        snake.setDirection(3); // left
        snake.move();
        assertEquals(5, snake.getX(), "Move left ha fallat");
        assertEquals(4, snake.getY(), "Move left ha fallat");

        snake.setDirection(2); // down
        snake.move();
        assertEquals(5, snake.getX(), "Move down ha fallat");
        assertEquals(5, snake.getY(), "Move down ha fallat");
    }

    @Test
    public void testChangeDirection() {
        SnakePlayer snake = new SnakePlayer(5, 5);
        snake.setDirection(3); // Attempt to reverse direction (from right to left)
        snake.move();
        assertEquals(6, snake.getX(), "La serp no es pot moure cap a la direccio contraria");
        assertEquals(5, snake.getY(), "La serp no es pot moure cap a la direccio contraria");

        snake.setDirection(0); // Change direction to up
        snake.move();
        assertEquals(6, snake.getX(), "Canviar direccio a up ha fallat");
        assertEquals(4, snake.getY(), "Canviar direccio a up ha fallat");
    }

    @Test
    public void testGrow() {
        SnakePlayer snake = new SnakePlayer(5, 5);
        snake.grow();
        List<int[]> body = snake.getBody();
        assertEquals(2, body.size(), "Despres de creixer, el size hauria de ser igual a 2");
        assertArrayEquals(new int[]{5, 5}, body.get(1), "Valor de la cua incorrecte despres de creixer");
    }

    @Test
    public void testCollisions() {
        // Use a mock SnakeBoard object
        SnakeBoardMock board = new SnakeBoardMock(10, 10);
        SnakePlayer snake = new SnakePlayer(0, 0);

        snake.setDirection(0); // Move up (out of bounds)
        snake.move();
        assertTrue(snake.collidesWithWall(board), "Wall collision no detectada");

        snake.restart(5, 5);
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
	assertFalse(snake.collidesWithSelf(), "No s'hauria de detectar Self-collision");
        snake.grow();
        snake.move();
        snake.grow();
        snake.move();
        snake.setDirection(2); // Down
        snake.move();
        snake.setDirection(3); // Left
        snake.move();
        snake.setDirection(0); // Up
        snake.move();
        assertTrue(snake.collidesWithSelf(), "Self-collision no detectada");
    }

    @Test
    public void testOccupies() {
        SnakePlayer snake = new SnakePlayer(5, 5);
        assertTrue(snake.occupies(5, 5), "Occupies hauria de retornar True");
        snake.move();
        assertFalse(snake.occupies(5, 5), "Occupies hauria de retornar False despres de moure's");
    }

    @Test
    public void testValorsFrontera() {
        // Test initialization with negative dimensions
        assertThrows(IllegalArgumentException.class, () -> new SnakePlayer(-1, 5), 
            "Esperava excepcio per x negativa, pero no ha llençat excepcio");
        assertThrows(IllegalArgumentException.class, () -> new SnakePlayer(5, -1), 
            "Esperava excepcio per y negativa, pero no ha llençat excepcio");

        // Test valid dimensions (normal and boundary values)
        SnakePlayer player = new SnakePlayer(0, 5);
        assertEquals(0, player.getX(), "No hauria de donar error a player.x = 0");

        player = new SnakePlayer(5, 0);
        assertEquals(0, player.getY(), "No hauria de donar error a player.y = 0");

        player = new SnakePlayer(5, 5);
        assertEquals(5, player.getX(), "No hauria de donar error a player = (5, 5)");
        assertEquals(5, player.getY(), "No hauria de donar error a player = (5, 5)");

        // Test setDirection with invalid and valid directions
        SnakePlayer player1 = new SnakePlayer(5, 5);
        assertThrows(IllegalArgumentException.class, () -> player1.setDirection(-1),
            "Esperava excepcio per direccio negativa, pero no ha llençat excepcio");

        SnakePlayer player2 = new SnakePlayer(5, 5);
        assertThrows(IllegalArgumentException.class, () -> player2.setDirection(4),
            "Esperava excepcio per direccio major a 3, pero no ha llençat excepcio");
        
        SnakePlayer player3 = new SnakePlayer(5, 5);
        player3.setDirection(2);
        assertEquals(2, player3.getDirection(), "No hauria de donar error a player.direction = 2");

        player3.setDirection(3);
        assertEquals(3, player3.getDirection(), "No hauria de donar error a player.direction = 3");

        player3.setDirection(0);
        assertEquals(0, player3.getDirection(), "No hauria de donar error a player.direction = 0");
    }
}
