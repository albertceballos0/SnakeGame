package views;

import controllers.SnakeController;
import java.awt.*;
import javax.swing.*;
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
        // Tamaño fijo de la ventana: ancho y alto basados en las dimensiones del tablero
        setSize(width * cellSize, height * cellSize + 30); // Añadido para bordes y espacio
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setResizable(false); // Evita el redimensionado

        gamePanel = new GamePanel();
        add(gamePanel, BorderLayout.CENTER); // Añadir el panel del juego

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
                //comprueba si sale fuera del tablero para 
                if (!(segment[0] >= game.getBoard().getWidth() || segment[1] >= game.getBoard().getHeight() || segment[0] < 0 || segment[1] < 0)) 
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
                String gameOverMessage = "Game Over!";
                FontMetrics metrics = g.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(gameOverMessage)) / 2; // Centrando el mensaje
                int y = height * cellSize / 2; // Ajustando verticalmente
                g.drawString(gameOverMessage, x, y);
            }

            // Dibujar el borde alrededor del área de juego
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, width * cellSize - 1, height * cellSize - 1); // -1 para que el borde encaje
        }
    }
}
