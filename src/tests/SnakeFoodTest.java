package tests;

import models.SnakeFood;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SnakeFoodTest {

    @Test
    void testGetX() {
        SnakeFood food = new SnakeFood(7, 14);
        assertEquals(7, food.getX(), "getX() ha fallat: esperava 7 pero ha retornat " + food.getX());
    }

    @Test
    void testGetY() {
        SnakeFood food = new SnakeFood(7, 14);
        assertEquals(14, food.getY(), "getY() ha fallat: esperava 14 pero ha retornat " + food.getY());
    }

    @Test
    void testInitialization() {
        SnakeFood food = new SnakeFood(10, 20);
        assertEquals(10, food.getX(), "Inicialitzacio ha fallat a x: esperava 10 pero ha retornat " + food.getX());
        assertEquals(20, food.getY(), "Inicialitzacio ha fallat a y: esperava 20 pero ha retornat " + food.getY());
    }

    @Test
    void testValorsFrontera() {
        // Test for negative dimensions
        assertThrows(IllegalArgumentException.class, () -> new SnakeFood(-1, 5), 
            "Esperava excepcio per x negativa, pero no ha llençat excepcio");
        assertThrows(IllegalArgumentException.class, () -> new SnakeFood(5, -1), 
            "Esperava excepcio per y negativa, pero no ha llençat excepcio");

        // Test for valid dimensions (including edge cases like 0)
        assertDoesNotThrow(() -> {
            SnakeFood food = new SnakeFood(0, 5);
            assertEquals(0, food.getX(), "No hauria de donar error a food.x = 0");
            SnakeFood food2 = new SnakeFood(5, 0);
            assertEquals(0, food2.getY(), "No hauria de donar error a food.y = 0");
            SnakeFood food3 = new SnakeFood(5, 5);
            assertEquals(5, food3.getX(), "No hauria de donar error a food.x = 5");
            assertEquals(5, food3.getY(), "No hauria de donar error a food.y = 5");
        }, "Error: Excepcio rebuda amb (x, y) de food valids");
    }
}
