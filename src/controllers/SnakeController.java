package controllers;


import java.awt.event.*;
import javax.swing.*;
import models.SnakeGame;
import views.GameSetUp;
import views.SnakeView;

public class SnakeController extends KeyAdapter {
    private SnakeGame game;
    private SnakeView view;
    private final GameSetUp gameSetUp;
    private Timer gameTimer;

    public SnakeController() {
        gameSetUp = new GameSetUp(this);
    }

    public void start(){
        gameSetUp.showSetupDialog();
    }

    @SuppressWarnings("Convert2Lambda")
    public void setAttributes(String playerName, int height, int width, int difficulty) {

        game = new SnakeGame(playerName, height, width, difficulty);
        view = new SnakeView(this, height, width);
        final int freq = 400 / difficulty;
        gameTimer = new Timer(freq, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.update();
                view.updateView();
                if (game.isGameOver()) {
                    gameTimer.stop();
                }
            }
        });
    }

    public void startGame() {
        view.setVisible(true);
        gameTimer.start();
    }

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
                    gameTimer.start();
                }
                break;
            
            case KeyEvent.VK_ESCAPE:
                if (game.isGameOver()) {
                    view.dispose();
                    gameSetUp.showSetupDialog();
                }                
                break;

            case KeyEvent.VK_C:
                if ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0) {
                    System.exit(0);
                }
                break;
        }
    }

    // Getter for the game object
    public SnakeGame getGame() { return game; }
    public String getPlayerName() { return game.getPlayerName(); }
}