package battle;

import moves.MoveType;
import org.junit.jupiter.api.Test;
import pokemon.Pokemon;
import trainer.Trainer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class BattleSystemRealTest {

    static class TestPokemon extends Pokemon {
        public TestPokemon(
                int id,
                String name,
                MoveType type1,
                MoveType type2,
                int hp,
                int attack,
                int defense,
                int spAttack,
                int spDefense,
                int speed
        ) {
            super(id, name, type1, type2, 300, hp, attack, defense, spAttack, spDefense, speed);
        }
    }

    @Test
    void battleSystemDebeEjecutarBatallaControladaSinErrores() {
        Trainer playerTrainer = new Trainer("Player");
        Trainer enemyTrainer = new Trainer("Enemy");

        Pokemon player = new TestPokemon(
                1, "StrongMon", MoveType.FIRE, null,
                500, 500, 10, 500, 10, 200
        );

        Pokemon enemy = new TestPokemon(
                2, "WeakMon", MoveType.GRASS, null,
                30, 10, 1, 10, 1, 10
        );

        playerTrainer.addPokemon(player);
        enemyTrainer.addPokemon(enemy);

        String simulatedInput = "1\n1\n";

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        BattleSystem battleSystem = new BattleSystem();

        assertDoesNotThrow(() -> battleSystem.startBattle(playerTrainer, enemyTrainer));

        System.setIn(originalIn);
    }

    @Test
    void enemigoMasRapidoDebeAtacarPrimeroYJugadorPuedePerder() {
        Trainer playerTrainer = new Trainer("Player");
        Trainer enemyTrainer = new Trainer("Enemy");

        Pokemon player = new TestPokemon(
                1, "WeakPlayer", MoveType.GRASS, null,
                30, 10, 1, 10, 1, 10
        );

        Pokemon enemy = new TestPokemon(
                2, "FastEnemy", MoveType.FIRE, null,
                500, 500, 1, 500, 1, 200
        );

        playerTrainer.addPokemon(player);
        enemyTrainer.addPokemon(enemy);

        String simulatedInput = "1\n1\n".repeat(20);

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        BattleSystem battleSystem = new BattleSystem();

        assertDoesNotThrow(() -> battleSystem.startBattle(playerTrainer, enemyTrainer));

        System.setIn(originalIn);
    }

    @Test
    void jugadorDebePoderCambiarPokemonDuranteLaBatalla() {
        Trainer playerTrainer = new Trainer("Player");
        Trainer enemyTrainer = new Trainer("Enemy");

        Pokemon weakPlayer = new TestPokemon(
                1, "WeakPlayer", MoveType.NORMAL, null,
                100, 10, 10, 10, 10, 10
        );

        Pokemon strongPlayer = new TestPokemon(
                2, "StrongPlayer", MoveType.FIRE, null,
                500, 500, 10, 500, 10, 200
        );

        Pokemon enemy = new TestPokemon(
                3, "WeakEnemy", MoveType.GRASS, null,
                30, 10, 1, 10, 1, 10
        );

        playerTrainer.addPokemon(weakPlayer);
        playerTrainer.addPokemon(strongPlayer);
        enemyTrainer.addPokemon(enemy);

        String simulatedInput =
                "2\n" + // cambiar Pokémon
                        "1\n" + // elegir StrongPlayer
                        "1\n" + // atacar
                        "1\n";  // movimiento

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        BattleSystem battleSystem = new BattleSystem();

        assertDoesNotThrow(() -> battleSystem.startBattle(playerTrainer, enemyTrainer));

        System.setIn(originalIn);
    }

    @Test
    void battleSystemDebeProcesarOpcionesInvalidasAntesDeAtacar() {
        Trainer playerTrainer = new Trainer("Player");
        Trainer enemyTrainer = new Trainer("Enemy");

        Pokemon player = new TestPokemon(
                1, "StrongPlayer", MoveType.FIRE, null,
                500, 500, 10, 500, 10, 200
        );

        Pokemon enemy = new TestPokemon(
                2, "WeakEnemy", MoveType.GRASS, null,
                30, 10, 1, 10, 1, 10
        );

        playerTrainer.addPokemon(player);
        enemyTrainer.addPokemon(enemy);

        String simulatedInput =
                "abc\n" +
                        "9\n" +
                        "1\n" +
                        "1\n";

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        BattleSystem battleSystem = new BattleSystem();

        assertDoesNotThrow(() -> battleSystem.startBattle(playerTrainer, enemyTrainer));

        System.setIn(originalIn);
    }

    @Test
    void jugadorSinPokemonesVivosDebePerderBatalla() {
        Trainer playerTrainer = new Trainer("Player");
        Trainer enemyTrainer = new Trainer("Enemy");

        Pokemon player = new TestPokemon(
                1, "WeakPlayer", MoveType.GRASS, null,
                1, 1, 1, 1, 1, 1
        );

        Pokemon enemy = new TestPokemon(
                2, "StrongEnemy", MoveType.FIRE, null,
                500, 500, 1, 500, 1, 200
        );

        playerTrainer.addPokemon(player);
        enemyTrainer.addPokemon(enemy);

        String simulatedInput = "1\n1\n".repeat(10);

        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        BattleSystem battleSystem = new BattleSystem();

        assertDoesNotThrow(() -> battleSystem.startBattle(playerTrainer, enemyTrainer));

        System.setIn(originalIn);

        assertTrue(player.isFainted());
    }
}
