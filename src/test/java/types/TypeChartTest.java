package types;

import moves.MoveType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TypeChartTest {

    @Test
    void ataqueFuegoContraPlantaDebeSerSuperEfectivo() {
        double multiplier = TypeChart.getMultiplier(MoveType.FIRE, MoveType.GRASS);

        assertEquals(2.0, multiplier);
    }

    @Test
    void ataqueFuegoContraAguaDebeSerPocoEfectivo() {
        double multiplier = TypeChart.getMultiplier(MoveType.FIRE, MoveType.WATER);

        assertEquals(0.5, multiplier);
    }

    @Test
    void ataqueNormalContraFantasmaNoDebeAfectar() {
        double multiplier = TypeChart.getMultiplier(MoveType.NORMAL, MoveType.GHOST);

        assertEquals(0.0, multiplier);
    }

    @Test
    void ataqueSinRelacionDefinidaDebeSerNeutro() {
        double multiplier = TypeChart.getMultiplier(MoveType.FIRE, MoveType.ELECTRIC);

        assertEquals(1.0, multiplier);
    }
}
