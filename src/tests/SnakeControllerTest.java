package tests;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import controllers.SnakeController;
import models.SnakeGame;
import views.SnakeView;

import java.awt.event.KeyEvent;

public class SnakeControllerTest {

    private SnakeController controller;
    private SnakeGame mockGame;
    private SnakeView mockView;

    @BeforeEach
    public void setUp() {
        controller = new SnakeController();

        // Mock objects creats amb Mockito
        mockGame = mock(SnakeGame.class);
        mockView = mock(SnakeView.class);

        // Injectem Mock objects
        controller.setAttributes("TestPlayer", 20, 20, 2);
        controller.startGame();
    }

    @Test
    public void testSetAttributes() {
        assertNotNull(controller.getGame(), "Game should be initialized");
        assertEquals("TestPlayer", controller.getPlayerName(), "Player name should be correctly set");
    }

    @Test
    public void testKeyPressed_ArrowKeys() {
        // Testejem tecla Up
        KeyEvent upKey = new KeyEvent(mockView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_UP, ' ');
        controller.keyPressed(upKey);
        verify(mockGame.getPlayer()).setDirection(0);

        // Testejem tecla Right
        KeyEvent rightKey = new KeyEvent(mockView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_RIGHT, ' ');
        controller.keyPressed(rightKey);
        verify(mockGame.getPlayer()).setDirection(1);

        // Testejem tecla Down
        KeyEvent downKey = new KeyEvent(mockView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_DOWN, ' ');
        controller.keyPressed(downKey);
        verify(mockGame.getPlayer()).setDirection(2);

        // Testejem tecla Left
        KeyEvent leftKey = new KeyEvent(mockView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_LEFT, ' ');
        controller.keyPressed(leftKey);
        verify(mockGame.getPlayer()).setDirection(3);
    }

    @Test
    public void testKeyPressed_SpaceKey() {
        when(mockGame.isGameOver()).thenReturn(true);

        // Testejem tecla Space per resetejar joc
        KeyEvent spaceKey = new KeyEvent(mockView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE, ' ');
        controller.keyPressed(spaceKey);

        verify(mockGame).restart();
        verify(mockView).restart();
    }

    @Test
    public void testKeyPressed_EscapeKey() {
        when(mockGame.isGameOver()).thenReturn(true);

        // Testejem tecla ESC per sortir del joc i tornar al menú
        KeyEvent escapeKey = new KeyEvent(mockView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, ' ');
        controller.keyPressed(escapeKey);

        verify(mockView).dispose();
    }

    @Test
    public void testKeyPressed_CtrlC() {
        // Testejem CTRL+C per sortir del joc
        KeyEvent ctrlCKey = new KeyEvent(mockView, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), KeyEvent.CTRL_DOWN_MASK, KeyEvent.VK_C, ' ');
        
        // Ho posem dintre d'un try catch perque no es tanqui la instancia de test
        try {
            controller.keyPressed(ctrlCKey);
        } catch (SecurityException ignored) {
            // No fem res
        }
    }

    @AfterEach
    public void tearDown() {
        controller = null;
    }
}
