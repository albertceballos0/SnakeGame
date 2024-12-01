package views;

import controllers.SnakeController;
import java.awt.*;
import javax.swing.*;
import models.SnakeGame;
import models.SnakeObstacle;

public class SnakeView extends JFrame {
    private  final SnakeController controller;
    private final int cellSize = 20;
    private final int width;
    private final int height;
    private final GamePanel gamePanel;

    // Constructor de la vista
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

    // Método para actualizar la interfaz con el puntaje y el estado del juego
    public void updateView() {
        gamePanel.repaint();
    }

    // Método para limpiar la vista
    public void clearView() {
        gamePanel.clear();
    }

    private class GamePanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            SnakeGame game = controller.getGame();

            // Limpiar el tablero si el juego fue reiniciado
            g.clearRect(0, 0, getWidth(), getHeight());

            // Dibujar la serpiente
            drawSnake(g, game);

            // Dibujar la comida
            drawFood(g, game);

            // Dibujar los obstaculos
            drawObstacles(g, game);

            // Dibujar el puntaje
            drawScore(g, game);

            // Mostrar el mensaje de Game Over si el juego terminó
            if (game.isGameOver()) {
                drawGameOver(g);
            }

            // Dibujar el borde alrededor del área de juego
            drawBorder(g);
        }

        // Método para limpiar el panel del juego
        public void clear() {
            Graphics g = getGraphics();
            if (g != null) {
                g.clearRect(0, 0, getWidth(), getHeight());
                g.dispose();
            }
        }

        // Método para dibujar la serpiente
        private void drawSnake(Graphics g, SnakeGame game) {
            g.setColor(Color.GREEN);
            for (int[] segment : game.getPlayer().getBody()) {
                if (!(segment[0] >= game.getBoard().getWidth() || segment[1] >= game.getBoard().getHeight() || segment[0] < 0 || segment[1] < 0))
                    g.fillRect(segment[0] * cellSize, segment[1] * cellSize, cellSize, cellSize);
            }
        }

        // Método para dibujar la comida
        private void drawFood(Graphics g, SnakeGame game) {
            g.setColor(Color.RED);
            g.fillRect(game.getFood().getX() * cellSize, game.getFood().getY() * cellSize, cellSize, cellSize);
        }

        // Método para dibujar los obstaculos 
        private void drawObstacles(Graphics g, SnakeGame game) {
            g.setColor(Color.BLACK);
            for (SnakeObstacle obstacle : game.getObstacles()) {
                g.fillRect(obstacle.getX() * cellSize, obstacle.getY() * cellSize, cellSize, cellSize);
            }
        }

        // Método para dibujar el puntaje
        private void drawScore(Graphics g, SnakeGame game) {
            g.setColor(Color.BLACK);
            g.drawString("Score: " + game.getScore(), 10, 20);
        }

        // Método para dibujar el mensaje de Game Over
        private void drawGameOver(Graphics g) {
            // Fondo semi-transparente para destacar el mensaje
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0, 0, 0, 150)); // Fondo negro con transparencia
            g2d.fillRect(0, 0, getWidth(), getHeight());

            // Configurar fuente para el título
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 50));
            String gameOverMessage = "GAME OVER";

            // Centrar el título
            FontMetrics titleMetrics = g.getFontMetrics();
            int titleX = (getWidth() - titleMetrics.stringWidth(gameOverMessage)) / 2;
            int titleY = height * cellSize / 2 - 40; // Posición superior para dejar espacio

            // Dibujar el título
            g.drawString(gameOverMessage, titleX, titleY);

            // Configurar fuente para las instrucciones
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            String instructions = "Press ESC to return to menu, TAB to restart";

            // Centrar las instrucciones
            FontMetrics instructionsMetrics = g.getFontMetrics();
            int instructionsX = (getWidth() - instructionsMetrics.stringWidth(instructions)) / 2;
            int instructionsY = height * cellSize / 2 + 20; // Debajo del título

            // Dibujar las instrucciones
            g.drawString(instructions, instructionsX, instructionsY);

            // dibujar el puntaje final
            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            String scoreMessage = "Final Score: " + controller.getGame().getScore();
            int scoreY = titleY + 40; // Debajo del título
            g.drawString(scoreMessage, titleX, scoreY);
        }

        // Método para dibujar el borde del área de juego
        private void drawBorder(Graphics g) {
            g.setColor(Color.BLACK);
            g.drawRect(0, 0, width * cellSize - 1, height * cellSize - 1); // -1 para que el borde encaje
        }
    }
}
