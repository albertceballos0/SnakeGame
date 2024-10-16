package views;

import javax.swing.*;
import java.awt.*;
import controllers.SnakeController;

public class GameSetUp {
    private String playerName;
    private int boardSize;

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
        frame.setSize(400, 250);  // Tamaño fijo más amplio
        frame.setResizable(false);  // Evita el redimensionado
        frame.setLayout(new GridBagLayout());  // Usar GridBagLayout para una mejor alineación
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // Margen entre componentes

        // Etiqueta y campo para el nombre del jugador
        JLabel nameLabel = new JLabel("Player Name:");
        JTextField nameField = new JTextField(15);

        // Etiqueta y combo box para el tamaño del tablero
        JLabel sizeLabel = new JLabel("Board Size:");
        String[] sizes = {"10x10", "20x20", "30x30", "40x40"};
        JComboBox<String> sizeComboBox = new JComboBox<>(sizes);

        // Botón de inicio
        JButton startButton = new JButton("Start Game");
        startButton.setBackground(new Color(59, 89, 182));
        startButton.setForeground(Color.WHITE);
        startButton.setFocusPainted(false);
        startButton.setFont(new Font("Tahoma", Font.BOLD, 12));

        // Añadir los componentes al layout con posiciones
        gbc.gridx = 0;
        gbc.gridy = 0;
        frame.add(nameLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        frame.add(nameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        frame.add(sizeLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        frame.add(sizeComboBox, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        frame.add(startButton, gbc);

        // Acción al pulsar el botón de iniciar juego
        startButton.addActionListener(e -> {
            playerName = nameField.getText();
            String selectedSize = (String) sizeComboBox.getSelectedItem();
            boardSize = Integer.parseInt(selectedSize.split("x")[0]);

            if (playerName.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter a player name.");
            } else {
                frame.dispose();
                startGame();
            }
        });

        // Centrar la ventana en la pantalla
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void startGame() {
        SnakeController controller = new SnakeController(playerName, boardSize, boardSize);
        controller.startGame();
    }
}
