package controllers;


import models.SnakeGame;


import views.SnakeView;

import java.io.Serial;
import javax.swing.*;
import java.awt.event.*;

public class SnakeController extends KeyAdapter {
    private SnakeGame game;
    private SnakeView view;
    private Timer gameTimer;

    /**
     * SnakeController is responsible for managing the Snake game logic and view.
     * It initializes the game and view components and sets up a timer to update
     * the game state and repaint the view at regular intervals.
     *
     * @param width  the width of the game area
     * @param height the height of the game area
     */
    public SnakeController(String playerName, int width, int height) {

        game = new SnakeGame(playerName, width, height);
        view = new SnakeView(this, width, height);
        gameTimer = new Timer(200, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.update();  // Actualiza el estado del juego
                view.repaint(); // Redibuja la vista
                if (game.isGameOver()) {
                    gameTimer.stop(); // Detener el juego si ha terminado
                }
            }
        });
    }

    /**
     * Starts the game by making the view visible and starting the game timer.
     */
    public void startGame() {
        view.setVisible(true);
        gameTimer.start();
    }



    /**
     * Handles key press events to control the snake game.
     *
     * @param e the KeyEvent triggered by a key press
     *
     * The following key codes are handled:
     * <ul>
     *   <li>KeyEvent.VK_UP: Sets the player's direction to up (0).</li>
     *   <li>KeyEvent.VK_RIGHT: Sets the player's direction to right (1).</li>
     *   <li>KeyEvent.VK_DOWN: Sets the player's direction to down (2).</li>
     *   <li>KeyEvent.VK_LEFT: Sets the player's direction to left (3).</li>
     *   <li>KeyEvent.VK_SPACE: If the game is over, restarts the game and view, and starts the game timer.</li>
     * </ul>
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                game.getPlayer().setDirection(0);
                break;
            case KeyEvent.VK_RIGHT:
                game.getPlayer().setDirection(1);
                break;
            case KeyEvent.VK_DOWN:
                game.getPlayer().setDirection(2);
                break;
            case KeyEvent.VK_LEFT:
                game.getPlayer().setDirection(3);
                break;
            case KeyEvent.VK_SPACE:
                if (game.isGameOver()) {
                    game.restart();
                    view.restart();
                    System.out.println("New game started"+ game.isGameOver());
                    gameTimer.start();
                }
                break;
        }
    }

    // Getter for the game object
    public SnakeGame getGame() { return game; }
}