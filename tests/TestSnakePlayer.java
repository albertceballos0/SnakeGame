package tests;

import models.SnakePlayer;
import models.SnakeBoard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestSnakePlayer {
    private SnakePlayer snakePlayer;

    @BeforeEach
    public void setUp() {
        //Inicialitzem SnakePlayer a posicio (5, 5)
        snakePlayer = new SnakePlayer(5, 5);
    }

    @Test
    public void testInitialPosition() {
        //Verifiquem constructor + getters
        assertEquals(5, snakePlayer.getX(), "Initial X-coordinate of the head should be 5.");
        assertEquals(5, snakePlayer.getY(), "Initial Y-coordinate of the head should be 5.");
        assertEquals(1, snakePlayer.getBody().size(), "Initial snake body should have one segment.");
    }

    @Test
    public void testMoveRight() {
        //Verifiquem que les coordenades canviïn al moure serp cap a la dreta
        snakePlayer.move();
        assertEquals(6, snakePlayer.getX(), "After moving right, X-coordinate should increase by 1.");
        assertEquals(5, snakePlayer.getY(), "After moving right, Y-coordinate should remain the same.");
    }

    @Test
    public void testMoveUp() {
        //Verifiquem que les coordenades canviïn al moure serp cap a dalt
        snakePlayer.setDirection(0);
        snakePlayer.move();
        assertEquals(5, snakePlayer.getX(), "After moving up, X-coordinate should remain the same.");
        assertEquals(4, snakePlayer.getY(), "After moving up, Y-coordinate should decrease by 1.");
    }

    @Test
    public void testMoveDown() {
        //Verifiquem que les coordenades canviïn al moure serp cap avall
        snakePlayer.setDirection(2);
        snakePlayer.move();
        assertEquals(5, snakePlayer.getX(), "After moving down, X-coordinate should remain the same.");
        assertEquals(6, snakePlayer.getY(), "After moving down, Y-coordinate should increase by 1.");
    }

    @Test
    public void testMoveLeft() {
        //Verifiquem que les coordenades canviïn al moure serp cap a l'esquerra
        snakePlayer.setDirection(3);
        snakePlayer.move();
        assertEquals(4, snakePlayer.getX(), "After moving left, X-coordinate should decrease by 1.");
        assertEquals(5, snakePlayer.getY(), "After moving left, Y-coordinate should remain the same.");
    }

    @Test
    public void testGrow() {
        //Verifiquem funcionament de la funcio grow
        snakePlayer.grow();
        assertEquals(2, snakePlayer.getBody().size(), "After growing, snake body size should increase by 1.");
        List<int[]> body = snakePlayer.getBody();
        assertArrayEquals(new int[]{5, 5}, body.get(1), "New segment should have the same position as the tail.");
    }

    @Test
    public void testCollidesWithWall() {
        SnakeBoard board = new SnakeBoard(10, 10);

        //Testejem col·lisio amb paret superior
        snakePlayer.setDirection(0);
        for (int i = 0; i < 6; i++) {
            snakePlayer.move();
        }
        assertTrue(snakePlayer.collidesWithWall(board), "Snake should collide with the top wall.");

        //Testejem col·lisio amb paret dreta
        snakePlayer = new SnakePlayer(9, 5);
        snakePlayer.setDirection(1);
        snakePlayer.move();
        assertTrue(snakePlayer.collidesWithWall(board), "Snake should collide with the right wall.");
    }

    @Test
    public void testCollidesWithSelf() {
        snakePlayer.grow();
        snakePlayer.grow();
        snakePlayer.setDirection(0); //Es mou cap a dalt
        snakePlayer.move();
        snakePlayer.setDirection(3); //Es mou a l'esquerra
        snakePlayer.move();
        snakePlayer.setDirection(2); //Es mou cap avall
        snakePlayer.move();
        snakePlayer.setDirection(1); //Es cap a la dreta (Xoca amb si mateixa)
        snakePlayer.move();

        assertTrue(snakePlayer.collidesWithSelf(), "Snake should collide with itself.");
    }

    @Test
    public void testDoesNotCollideWithSelf() {
        //Testejem que no xoqui amb si mateixa en una situacio normal
        snakePlayer.grow();
        snakePlayer.move();
        assertFalse(snakePlayer.collidesWithSelf(), "Snake should not collide with itself after a valid move.");
    }

    @Test
    public void testOccupies() {
        //Testejem funció occupies
        assertTrue(snakePlayer.occupies(5, 5), "Snake should occupy its initial position.");
        assertFalse(snakePlayer.occupies(6, 6), "Snake should not occupy a position it hasn't moved to.");
    }

    @Test
    public void testSetDirection() {
        //Testejem que la direcció de la serp sigui la correcta
        snakePlayer.setDirection(2);
        assertEquals(2, snakePlayer.getDirection(), "Direction should be set to down.");
        snakePlayer.setDirection(3);
        assertEquals(3, snakePlayer.getDirection(), "Direction should be set to left.");
    }
}