package moves;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MoveTest {

    @Test
    void debeCrearMovimientoConSusDatosCorrectos() {
        Move move = new Move(
                "Flamethrower",
                MoveType.FIRE,
                MoveCategory.SPECIAL,
                90
        );

        assertEquals("Flamethrower", move.getName());
        assertEquals(MoveType.FIRE, move.getType());
        assertEquals(MoveCategory.SPECIAL, move.getCategory());
        assertEquals(90, move.getPower());
    }

    @Test
    void movimientoDeDefensaDebeTenerPotenciaCero() {
        Move move = new Move(
                "Defend",
                MoveType.NORMAL,
                MoveCategory.DEFENSE,
                0
        );

        assertEquals(MoveCategory.DEFENSE, move.getCategory());
        assertEquals(0, move.getPower());
    }
}

