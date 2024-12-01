package tests;

import models.SnakeBoard;
import models.SnakeFood;
import models.SnakeObstacle;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SnakeBoardTest {

    @Test
    void testInitialization() {
        SnakeBoard board = new SnakeBoard(10, 20);
        assertEquals(10, board.getWidth(), "Inicialitzacio ha fallat a width: esperava 10 pero ha retornat " + board.getWidth());
        assertEquals(20, board.getHeight(), "Inicialitzacio ha fallat a height: esperava 20 pero ha retornat " + board.getHeight());
        assertEquals(0, board.getNumObstacles(), "Inicialitzacio ha fallat a Obstacles: esperava 0 pero ha retornat " + board.getNumObstacles());
        assertEquals(0, board.getFood().getX(), "Inicialitzacio ha fallat a food.x");
        assertEquals(0, board.getFood().getY(), "Inicialitzacio ha fallat a food.y");
    }

    @Test
    void testSetAndGetFood() {
        SnakeBoard board = new SnakeBoard(10, 10);
        board.setFood(5, 7);
        SnakeFood food = board.getFood();
        assertEquals(5, food.getX(), "setFood ha fallat a x");
        assertEquals(7, food.getY(), "setFood ha fallat a y");
    }

    @Test
    void testSetAndGetObstacles() {
        SnakeBoard board = new SnakeBoard(10, 10);
        board.setObstacle(3, 4);
        board.setObstacle(6, 8);
        List<SnakeObstacle> obstacles = board.getObstacles();
        assertEquals(2, obstacles.size(), "setObstacle ha fallat: esperava 2 pero ha retornat " + obstacles.size());
        assertEquals(3, obstacles.get(0).getX(), "Primer Obstacle incorrecte: esperava posicio x = 3");
        assertEquals(4, obstacles.get(0).getY(), "Primer Obstacle incorrecte: esperava posicio y = 4");
        assertEquals(6, obstacles.get(1).getX(), "Segon Obstacle incorrecte: esperava posicio x = 6");
        assertEquals(8, obstacles.get(1).getY(), "Segon Obstacle incorrecte: esperava posicio y = 8");
    }

    @Test
    void testIsFood() {
        SnakeBoard board = new SnakeBoard(10, 10);
        board.setFood(2, 2);
        assertTrue(board.isFood(2, 2), "isFood ha fallat: esperava True pero ha rebut False");
        assertFalse(board.isFood(1, 1), "isFood ha fallat: esperava False pero ha rebut True");
    }

    @Test
    void testIsObstacle() {
        SnakeBoard board = new SnakeBoard(10, 10);
        board.setObstacle(1, 1);
        assertTrue(board.isObstacle(1, 1), "isObstacle ha fallat: esperava True pero ha rebut False");
        assertFalse(board.isObstacle(2, 2), "isObstacle ha fallat: esperava False pero ha rebut True");
    }

    @Test
    void testClearObstacles() {
        SnakeBoard board = new SnakeBoard(10, 10);
        board.setObstacle(3, 4);
        board.setObstacle(5, 6);
        board.clearObstacles();
        assertEquals(0, board.getNumObstacles(), "clearObstacles ha fallat: esperava 0 pero ha rebut " + board.getNumObstacles());
    }

    @Test
    void testValorsFrontera() {
        // Negative dimensions
        assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(-1, 5), 
            "Esperava excepcio per width negativa");
        assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(5, -1), 
            "Esperava excepcio per height negativa");

        // Zero dimensions (boundary value)
        assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(0, 5), 
            "Esperava excepcio per width 0");
        assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(5, 0), 
            "Esperava excepcio per height 0");

        // Dimensions > 500
        assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(501, 5), 
            "Esperava excepcio per width major a 500");
        assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(5, 501), 
            "Esperava excepcio per height major a 500");

        // Valid dimensions
        assertDoesNotThrow(() -> {
            SnakeBoard board = new SnakeBoard(10, 10);
            assertEquals(10, board.getWidth(), "Width incorrecta, esperava 10");
            assertEquals(10, board.getHeight(), "Height incorrecta, esperava 10");
        });

        // Testing setFood with invalid values
        SnakeBoard board = new SnakeBoard(10, 10);
        assertThrows(IllegalArgumentException.class, () -> board.setFood(-1, 5), 
            "Esperava excepcio per x negativa");
        assertThrows(IllegalArgumentException.class, () -> board.setFood(5, -1), 
            "Esperava excepcio per y negativa");

        // Valid food placement
        assertDoesNotThrow(() -> {
            board.setFood(0, 5);
            assertTrue(board.isFood(0, 5), "No hauria de donar error a food.x = 0");
            board.setFood(5, 0);
            assertTrue(board.isFood(5, 0), "No hauria de donar error a food.y = 0");
            board.setFood(5, 5);
            assertTrue(board.isFood(5, 5), "No hauria de donar error a food = (5, 5)");
        });
    }
}
