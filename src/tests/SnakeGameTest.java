package tests;

import models.SnakeGame;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeGameTest {

    @Test
    void testInitialization() {
        SnakeGame game = new SnakeGame("Player1", 10, 10, 3);
        assertEquals(10, game.getBoard().getWidth(), "Expected width: 10");
        assertEquals(10, game.getBoard().getHeight(), "Expected height: 10");
        assertEquals(5, game.getPlayer().getX(), "Expected player.x: 5");
        assertEquals(5, game.getPlayer().getY(), "Expected player.y: 5");
        assertEquals(0, game.getScore(), "Expected score: 0");
        assertFalse(game.isGameOver(), "GameOver flag should be false");
        assertEquals(3, game.getObstacles().size(), "Expected obstacles: 3");
    }

    @Test
    void testRestart() {
        SnakeGame game = new SnakeGame("Player1", 10, 10, 3);
        game.update(); // Simulate game progress
        game.restart();
        assertEquals(5, game.getPlayer().getX(), "Player X should reset to 5");
        assertEquals(5, game.getPlayer().getY(), "Player Y should reset to 5");
        assertEquals(0, game.getScore(), "Score should reset to 0");
        assertFalse(game.isGameOver(), "GameOver flag should reset to false");
        assertEquals(3, game.getObstacles().size(), "Obstacles should reset to 3");
    }

    @Test
    void testUpdateWithoutCollision() {
        SnakeGame game = new SnakeGame("Player1", 10, 10, 0);
        game.getPlayer().setDirection(1); // Move right
        game.update();
        assertEquals(6, game.getPlayer().getX(), "Player X should update to 6");
        assertFalse(game.isGameOver(), "GameOver flag should be false");
    }

    @Test
    void testUpdateWithCollision() {
        SnakeGame game = new SnakeGame("Player1", 10, 10, 0);
        game.getPlayer().setDirection(0); // Move up
        game.update();
        game.update();
        game.update();
        game.update();
        game.update();
        game.update(); // Snake goes out of bounds -> collision
        assertTrue(game.isGameOver(), "GameOver flag should be true");
    }

    @Test
    void testCheckFood() {
        SnakeGame game = new SnakeGame("Player1", 10, 10, 1);
        game.getBoard().setFood(6, 5); // Place food directly in front of the player
        game.getPlayer().setDirection(1); // Move right
        game.update();
        assertEquals(10, game.getScore(), "Score should increment by 10");
        assertFalse(6 == game.getFood().getX() && 5 == game.getFood().getY(), "Food s'hauria de recolocar");
    }

    @Test
    void testSetObstacles() {
        SnakeGame game = new SnakeGame("Player1", 10, 10, 5);
        assertEquals(5, game.getObstacles().size(), "Expected 5 obstacles");
    }

    @Test
    void testValorsFrontera() {
        // Invalid difficulty
        assertThrows(IllegalArgumentException.class, () -> new SnakeGame("Player1", 10, 10, -1), "Expected exception for negative difficulty");
        assertThrows(IllegalArgumentException.class, () -> new SnakeGame("Player1", 10, 10, 6), "Expected exception for difficulty > 5");

        // Valid difficulties
        assertDoesNotThrow(() -> new SnakeGame("Player1", 10, 10, 1));
        assertDoesNotThrow(() -> new SnakeGame("Player1", 10, 10, 3));
        assertDoesNotThrow(() -> new SnakeGame("Player1", 10, 10, 5));
        assertDoesNotThrow(() -> new SnakeGame("Player1", 10, 10, 0));

        // Invalid numObstacles
        SnakeGame game = new SnakeGame("Player1", 10, 10, 1);
        assertThrows(IllegalArgumentException.class, () -> game.setObstacles(-1), "Expected exception for negative obstacles");
        assertThrows(IllegalArgumentException.class, () -> game.setObstacles(10 * 10), "Expected exception for obstacles equal to board tiles");

        // Valid numObstacles
        SnakeGame game1 = new SnakeGame("Player1", 10, 10, 0);
        assertDoesNotThrow(() -> game1.setObstacles(5));
        assertEquals(5, game1.getBoard().getNumObstacles(), "Expected 5 obstacles");
        
        SnakeGame game2 = new SnakeGame("Player1", 10, 10, 0);
        assertDoesNotThrow(() -> game2.setObstacles((10 * 10) - 2));
        assertEquals((10 * 10) - 2, game2.getBoard().getNumObstacles(), "Expected (board.height * board.width) - 2 obstacles");
        
        SnakeGame game3 = new SnakeGame("Player1", 10, 10, 0);
        assertDoesNotThrow(() -> game3.setObstacles(0));
        assertEquals(0, game3.getBoard().getNumObstacles(), "Expected 0 obstacles");
    }
}

