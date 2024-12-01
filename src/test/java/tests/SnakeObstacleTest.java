package tests;

import models.SnakeObstacle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeObstacleTest {

    @Test
    void testGetX() {
        SnakeObstacle obstacle = new SnakeObstacle(5, 10);
        assertEquals(5, obstacle.getX(), "getX() ha fallat: esperava 5 pero ha retornat " + obstacle.getX());
    }

    @Test
    void testGetY() {
        SnakeObstacle obstacle = new SnakeObstacle(5, 10);
        assertEquals(10, obstacle.getY(), "getY() ha fallat: esperava 10 pero ha retornat " + obstacle.getY());
    }

    @Test
    void testInitialization() {
        SnakeObstacle obstacle = new SnakeObstacle(8, 12);
        assertEquals(8, obstacle.getX(), "Inicialitzacio ha fallat a x: esperava 8 pero ha retornat " + obstacle.getX());
        assertEquals(12, obstacle.getY(), "Inicialitzacio ha fallat a y: esperava 12 pero ha retornat " + obstacle.getY());
    }

    @Test
    void testValorsFrontera() {
        // Test for negative dimensions
        assertThrows(IllegalArgumentException.class, () -> new SnakeObstacle(-1, 5), 
            "Esperava excepcio per x negativa, pero no ha llençat excepcio");
        assertThrows(IllegalArgumentException.class, () -> new SnakeObstacle(5, -1), 
            "Esperava excepcio per y negativa, pero no ha llençat excepcio");

        // Test for valid dimensions (including edge cases like 0)
        assertDoesNotThrow(() -> {
            SnakeObstacle obstacle = new SnakeObstacle(0, 5);
            assertEquals(0, obstacle.getX(), "No hauria de donar error a obstacle.x = 0");
            SnakeObstacle obstacle2 = new SnakeObstacle(5, 0);
            assertEquals(0, obstacle2.getY(), "No hauria de donar error a obstacle.y = 0");
            SnakeObstacle obstacle3 = new SnakeObstacle(5, 5);
            assertEquals(5, obstacle3.getX(), "No hauria de donar error a obstacle.x = 5");
            assertEquals(5, obstacle3.getY(), "No hauria de donar error a obstacle.y = 5");
        }, "Error: Excepcio rebuda amb (x, y) de obstacle valids");
    }
}
