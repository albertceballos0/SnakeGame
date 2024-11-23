package tests;

import models.SnakeBoard;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestSnakeBoard {

    @Test
    public void testValidDimensions() {
        //Creem objecte amb dimensions valides
        SnakeBoard board = new SnakeBoard(20, 15);

        // Verifiquem getters
        assertEquals(20, board.getWidth(), "Width should match the value passed to the constructor.");
        assertEquals(15, board.getHeight(), "Height should match the value passed to the constructor.");
    }

    @Test
    public void testZeroDimensions() {
        //Al inicialitzar amb 0, esperem una excepció
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(0, 10));
        assertEquals("Width and height must be positive.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(10, 0));
        assertEquals("Width and height must be positive.", exception.getMessage());
    }

    @Test
    public void testNegativeDimensions() {
        //Al inicialitzar amb dimensions negatives, esperem una excepció
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(-1, 10));
        assertEquals("Width and height must be positive.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(10, -1));
        assertEquals("Width and height must be positive.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new SnakeBoard(-5, -5));
        assertEquals("Width and height must be positive.", exception.getMessage());
    }

    @Test
    public void testLargeDimensions() {
        //Provem amb valors molt alts per assegurar que no provoca overflow
        SnakeBoard board = new SnakeBoard(1000, 1000);

        assertEquals(1000, board.getWidth(), "Width should match the value passed to the constructor.");
        assertEquals(1000, board.getHeight(), "Height should match the value passed to the constructor.");
    }
}
