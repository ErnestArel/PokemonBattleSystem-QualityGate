package trainer;

import moves.MoveType;
import org.junit.jupiter.api.Test;
import pokemon.Pokemon;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TrainerTest {

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
    void debeAgregarPokemonAlEquipo() {
        Trainer trainer = new Trainer("Ash");

        Pokemon pikachu = new TestPokemon(
                25,
                "Pikachu",
                MoveType.ELECTRIC,
                null,
                100,
                50,
                30,
                50,
                30,
                90
        );

        trainer.addPokemon(pikachu);

        assertEquals(1, trainer.getTeam().size());
    }

    @Test
    void noDebeAgregarMasDeSeisPokemones() {
        Trainer trainer = new Trainer("Ash");

        for (int i = 0; i < 7; i++) {
            trainer.addPokemon(
                    new TestPokemon(
                            i,
                            "Pokemon" + i,
                            MoveType.NORMAL,
                            null,
                            100,
                            50,
                            30,
                            50,
                            30,
                            90
                    )
            );
        }

        assertEquals(6, trainer.getTeam().size());
    }

    @Test
    void debeRemoverPokemonCorrectamente() {
        Trainer trainer = new Trainer("Ash");

        Pokemon pikachu = new TestPokemon(
                25,
                "Pikachu",
                MoveType.ELECTRIC,
                null,
                100,
                50,
                30,
                50,
                30,
                90
        );

        trainer.addPokemon(pikachu);

        trainer.removePokemon(0);

        assertEquals(0, trainer.getTeam().size());
    }

    @Test
    void getFirstAlivePokemonDebeRetornarPokemonVivo() {
        Trainer trainer = new Trainer("Ash");

        Pokemon fainted = new TestPokemon(
                1,
                "Dead",
                MoveType.NORMAL,
                null,
                10,
                10,
                10,
                10,
                10,
                10
        );

        fainted.receiveDamage(999);

        Pokemon alive = new TestPokemon(
                2,
                "Alive",
                MoveType.FIRE,
                null,
                100,
                50,
                30,
                50,
                30,
                90
        );

        trainer.addPokemon(fainted);
        trainer.addPokemon(alive);

        assertEquals(alive, trainer.getFirstAlivePokemon());
    }

    @Test
    void choosePokemonDebeSeleccionarPokemonCorrecto() {
        Trainer trainer = new Trainer("Ash");

        Pokemon pikachu = new TestPokemon(
                25,
                "Pikachu",
                MoveType.ELECTRIC,
                null,
                100,
                50,
                30,
                50,
                30,
                90
        );

        trainer.addPokemon(pikachu);

        String input = "0\n";

        Scanner scanner = new Scanner(
                new ByteArrayInputStream(input.getBytes())
        );

        Pokemon selected = trainer.choosePokemon(scanner);

        assertEquals(pikachu, selected);
    }

    @Test
    void hasAlivePokemonsDebeRetornarFalseSiTodosEstanDerrotados() {
        Trainer trainer = new Trainer("Ash");

        Pokemon fainted = new TestPokemon(
                1,
                "Dead",
                MoveType.NORMAL,
                null,
                10,
                10,
                10,
                10,
                10,
                10
        );

        fainted.receiveDamage(999);

        trainer.addPokemon(fainted);

        assertFalse(trainer.hasAlivePokemons());
    }
}
