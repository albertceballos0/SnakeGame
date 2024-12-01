package views;

import controllers.SnakeController;
import java.awt.*;
import javax.swing.*;

public class GameSetUp {
    private String playerName;
    private int boardSize;
    private int difficulty;
    private final SnakeController controller;

    public GameSetUp(SnakeController controller) {
        this.controller = controller;
    }

    public void showSetupDialog() {
        try {
            // Cambia el look and feel a uno más moderno
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Crear el marco principal
        JFrame frame = new JFrame("Snake Game Setup");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);  // Tamaño fijo más amplio
        frame.setResizable(false);  // Evita el redimensionado
        frame.setLayout(new GridBagLayout());  // Usar GridBagLayout para una mejor alineación
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Margen entre componentes

        // Etiqueta y campo para el nombre del jugador
        JLabel nameLabel = new JLabel("Player Name:");
        JTextField nameField = new JTextField(controller.getGame() != null ? controller.getPlayerName() : "", 15);

        // Etiqueta y combo box para el tamaño del tablero
        JLabel sizeLabel = new JLabel("Board Size:");
        String[] sizes = { "20x20", "30x30", "40x40"};
        JComboBox<String> sizeComboBox = new JComboBox<>(sizes);

        // Etiqueta y botones para el nivel de dificultad
        JLabel difficultyLabel = new JLabel("Difficulty Level:");
        JPanel difficultyPanel = new JPanel(new FlowLayout());
        JRadioButton easyButton = new JRadioButton("Easy");
        JRadioButton mediumButton = new JRadioButton("Medium");
        JRadioButton hardButton = new JRadioButton("Hard");
        ButtonGroup difficultyGroup = new ButtonGroup();
        difficultyGroup.add(easyButton);
        difficultyGroup.add(mediumButton);
        difficultyGroup.add(hardButton);
        difficultyPanel.add(easyButton);
        difficultyPanel.add(mediumButton);
        difficultyPanel.add(hardButton);

        // Botón de inicio
        JButton startButton = new JButton("Start Game");
        startButton.setBackground(Color.WHITE);
        startButton.setForeground(Color.BLACK);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 12));

        // Añadir los componentes al layout con posiciones
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(sizeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(sizeComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        frame.add(difficultyLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        frame.add(difficultyPanel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(startButton, gbc);

        // Acción al pulsar el botón de iniciar juego
        startButton.addActionListener(e -> {
            playerName = nameField.getText();
            final String selectedSize = (String) sizeComboBox.getSelectedItem();
            boardSize = Integer.parseInt(selectedSize.split("x")[0]);

            final int selectedDifficulty;
            if (easyButton.isSelected()) {
                selectedDifficulty = 1;
            } else if (mediumButton.isSelected()) {
                selectedDifficulty = 3;
            } else if (hardButton.isSelected()) {
                selectedDifficulty = 5;
            } else {
                selectedDifficulty = -1; // Invalid difficulty
            }

            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a player name.");
            } else if (selectedDifficulty == -1) {
                JOptionPane.showMessageDialog(frame, "Please select a difficulty level.");
            } else {
                this.difficulty = selectedDifficulty;
                frame.dispose();
                startGame();
            }
        });

        // Centrar la ventana en la pantalla
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Initializes and starts the snake game.
     * This method creates a new instance of SnakeController with the specified player name, board size, and difficulty level,
     * and then calls the startGame method on the controller to begin the game.
     */
    private void startGame() {
        System.out.println("Starting game with player: " + playerName + ", board size: " + boardSize + "x" + boardSize + ", difficulty: " + difficulty);
        controller.setAttributes(playerName, boardSize, boardSize, difficulty);
        controller.startGame();
    }
}
