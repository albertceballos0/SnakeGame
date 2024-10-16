package views;

import javax.swing.*;
import java.awt.*;

import controllers.SnakeController;
import models.SnakeGame;

public class SnakeView extends JFrame {
    private SnakeController controller;
    private int cellSize = 20;
    private int width;
    private int height;
    private GamePanel gamePanel;

    public SnakeView(SnakeController controller, int width, int height) {
        this.controller = controller;
        this.width = width;
        this.height = height;

        setTitle("Snake Game");
        setSize(width * cellSize, height * cellSize);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        gamePanel = new GamePanel();
        add(gamePanel);

        addKeyListener(controller);
        setFocusable(true);
    }

    // Método para reiniciar la vista
    public void restart() {
        // Esto limpiará el tablero llamando a repaint, que actualizará el GamePanel
        gamePanel.repaint();
    }

    private class GamePanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            SnakeGame game = controller.getGame();

            // Limpiar el tablero si el juego fue reiniciado
            g.clearRect(0, 0, getWidth(), getHeight());

            // Dibujar la serpiente
            g.setColor(Color.GREEN);
            for (int[] segment : game.getPlayer().getBody()) {
                g.fillRect(segment[0] * cellSize, segment[1] * cellSize, cellSize, cellSize);
            }

            // Dibujar la comida
            g.setColor(Color.RED);
            g.fillRect(game.getFood().getX() * cellSize, game.getFood().getY() * cellSize, cellSize, cellSize);

            // Dibujar el puntaje
            g.setColor(Color.BLACK);
            g.drawString("Score: " + game.getScore(), 10, 20);

            // Mostrar el mensaje de Game Over si el juego terminó
            if (game.isGameOver()) {
                g.setColor(Color.RED);
                g.setFont(new Font("Arial", Font.BOLD, 30));
                g.drawString("Game Over!", width * cellSize / 2 - 70, height * cellSize / 2);
            }

            // Dibujar el borde alrededor del área de juego
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, width * cellSize - 1, height * cellSize - 1); // -1 para que el borde encaje
        }
    }
}
