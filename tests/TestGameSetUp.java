package tests;

import views.GameSetUp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestGameSetUp {
    @Test
    public void testValidPlayerName() {
        GameSetUp setup = new GameSetUp();
        
        assertTrue(setup.isValidPlayerName("Gerard"));       // Nom Valid
        assertTrue(setup.isValidPlayerName("GerardAsbert123")); // Cas Frontera: 15 caracters
        assertFalse(setup.isValidPlayerName("Ge"));         // Menys de 3 caracters
        assertFalse(setup.isValidPlayerName("GerardAsbert12345")); // Més de 3 caracters
        assertFalse(setup.isValidPlayerName(""));           // Nom buit
    }
}