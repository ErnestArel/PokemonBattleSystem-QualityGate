package battle;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class BattleMenuTest {

    @Test
    void menuDebeSalirCorrectamente() {
        InputStream originalIn = System.in;

        System.setIn(new ByteArrayInputStream("0\n".getBytes()));

        BattleMenu menu = new BattleMenu();

        assertDoesNotThrow(menu::start);

        System.setIn(originalIn);
    }

    @Test
    void menuDebeMostrarEquipoVacioYLuegoSalir() {
        InputStream originalIn = System.in;

        System.setIn(new ByteArrayInputStream("3\n0\n".getBytes()));

        BattleMenu menu = new BattleMenu();

        assertDoesNotThrow(menu::start);

        System.setIn(originalIn);
    }

    @Test
    void menuNoDebePermitirBatallaSinEquipo() {
        InputStream originalIn = System.in;

        System.setIn(new ByteArrayInputStream("2\n0\n".getBytes()));

        BattleMenu menu = new BattleMenu();

        assertDoesNotThrow(menu::start);

        System.setIn(originalIn);
    }

    @Test
    void menuDebeProcesarOpcionInvalidaYLuegoSalir() {
        InputStream originalIn = System.in;

        System.setIn(new ByteArrayInputStream("abc\n9\n0\n".getBytes()));

        BattleMenu menu = new BattleMenu();

        assertDoesNotThrow(menu::start);

        System.setIn(originalIn);
    }

    @Test
    void menuDebeConstruirEquipoYLuegoSalir() {
        InputStream originalIn = System.in;

        String input =
                "1\n" +
                        "1\n" +
                        "2\n" +
                        "3\n" +
                        "4\n" +
                        "5\n" +
                        "6\n" +
                        "0\n";

        System.setIn(new ByteArrayInputStream(input.getBytes()));

        BattleMenu menu = new BattleMenu();

        assertDoesNotThrow(menu::start);

        System.setIn(originalIn);
    }
}
