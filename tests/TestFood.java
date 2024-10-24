package tests;

import models.SnakeGame;
import models.SnakeFood;
import models.SnakePlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

public class TestFood {

    private SnakeGame game;
    private SnakeFood mockFood;
    private SnakePlayer mockPlayer;

    @BeforeEach
    public void setup() {
        mockFood = mock(SnakeFood.class);
        mockPlayer = mock(SnakePlayer.class);
        game = new SnakeGame("Player1", 20, 20);
    }

    @Test
    public void testPlaceFoodWithMock() {
        //Simulo que el jugador ocupa una posicio no especifica
        when(mockPlayer.occupies(anyInt(), anyInt())).thenReturn(false);
        
        //Verifico que es posi el menjar en qualsevol posicio
        game.update();
        verify(mockFood).setPosition(anyInt(), anyInt());
    }

    @Test
    public void testPlayerEatsFood() {
        //Simulem que el jugador esta a la mateixa posicio que el menjar
        when(mockPlayer.getX()).thenReturn(5);
        when(mockPlayer.getY()).thenReturn(5);
        when(mockFood.getX()).thenReturn(5);
        when(mockFood.getY()).thenReturn(5);

        //Cridem a update per verificar que el jugador agafa el menjar
        game.update();
        verify(mockPlayer).grow(); //Verifiquem que el jugador creix
        verify(mockFood).setPosition(anyInt(), anyInt()); //Verifiquem que es posa un nou menjar.
    }
}
