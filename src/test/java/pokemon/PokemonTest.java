package pokemon;

import moves.MoveType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {

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
    void elDanioDebeReducirLaVidaDelPokemon() {
        Pokemon atacante = new TestPokemon(1, "Charmander", MoveType.FIRE, null, 100, 80, 20, 50, 20, 65);
        Pokemon defensor = new TestPokemon(2, "Bulbasaur", MoveType.GRASS, null, 100, 40, 10, 40, 10, 45);

        atacante.useMove(1, defensor);

        assertTrue(defensor.getHp() < 100);
    }

    @Test
    void laVidaNoDebeQuedarEnNegativo() {
        Pokemon defensor = new TestPokemon(1, "Bulbasaur", MoveType.GRASS, null, 30, 40, 10, 40, 10, 45);

        defensor.receiveDamage(999);

        assertEquals(0, defensor.getHp());
        assertTrue(defensor.isFainted());
    }

    @Test
    void ataqueEfectivoDebeCausarMasDanio() {
        Pokemon atacante = new TestPokemon(1, "Charmander", MoveType.FIRE, null, 500, 80, 10, 50, 10, 65);

        Pokemon defensorEfectivo = new TestPokemon(2, "Bulbasaur", MoveType.GRASS, null, 500, 40, 10, 40, 10, 45);
        Pokemon defensorNeutro = new TestPokemon(3, "Pikachu", MoveType.ELECTRIC, null, 500, 40, 10, 40, 10, 45);

        atacante.useMove(1, defensorEfectivo);
        atacante.useMove(1, defensorNeutro);

        int danioEfectivo = 500 - defensorEfectivo.getHp();
        int danioNeutro = 500 - defensorNeutro.getHp();

        assertTrue(danioEfectivo > danioNeutro);
    }

    @Test
    void ataquePocoEfectivoDebeCausarMenosDanio() {
        Pokemon atacante = new TestPokemon(1, "Charmander", MoveType.FIRE, null, 100, 80, 10, 50, 10, 65);

        Pokemon defensorPocoEfectivo = new TestPokemon(2, "Squirtle", MoveType.WATER, null, 100, 40, 10, 40, 10, 45);
        Pokemon defensorNeutro = new TestPokemon(3, "Pikachu", MoveType.ELECTRIC, null, 100, 40, 10, 40, 10, 45);

        atacante.useMove(1, defensorPocoEfectivo);
        atacante.useMove(1, defensorNeutro);

        int danioPocoEfectivo = 100 - defensorPocoEfectivo.getHp();
        int danioNeutro = 100 - defensorNeutro.getHp();

        assertTrue(danioPocoEfectivo < danioNeutro);
    }

    @Test
    void soloDebeCambiarElHpDelPokemonAtacado() {
        Pokemon atacante = new TestPokemon(1, "Charmander", MoveType.FIRE, null, 100, 80, 20, 50, 20, 65);
        Pokemon defensor = new TestPokemon(2, "Bulbasaur", MoveType.GRASS, null, 100, 40, 10, 40, 10, 45);

        String nombreInicial = defensor.getName();
        MoveType tipoInicial = defensor.getType1();
        int ataqueInicial = defensor.getAttack();
        int defensaInicial = defensor.getDefense();
        int ataqueEspecialInicial = defensor.getSpAttack();
        int defensaEspecialInicial = defensor.getSpDefense();
        int velocidadInicial = defensor.getSpeed();

        atacante.useMove(1, defensor);

        assertEquals(nombreInicial, defensor.getName());
        assertEquals(tipoInicial, defensor.getType1());
        assertEquals(ataqueInicial, defensor.getAttack());
        assertEquals(defensaInicial, defensor.getDefense());
        assertEquals(ataqueEspecialInicial, defensor.getSpAttack());
        assertEquals(defensaEspecialInicial, defensor.getSpDefense());
        assertEquals(velocidadInicial, defensor.getSpeed());
        assertTrue(defensor.getHp() < 100);
    }

    @Test
    void pokemonConVidaMayorACeroDebeEstarVivo() {
        Pokemon pokemon = new TestPokemon(
                1,
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

        assertTrue(pokemon.isAlive());
        assertFalse(pokemon.isFainted());
    }

    @Test
    void pokemonConVidaCeroDebeEstarDerrotado() {
        Pokemon pokemon = new TestPokemon(
                1,
                "Charmander",
                MoveType.FIRE,
                null,
                50,
                50,
                10,
                50,
                10,
                65
        );

        pokemon.receiveDamage(999);

        assertEquals(0, pokemon.getHp());
        assertTrue(pokemon.isFainted());
        assertFalse(pokemon.isAlive());
    }

    @Test
    void healFullDebeRestaurarTodaLaVida() {
        Pokemon pokemon = new TestPokemon(
                1,
                "Bulbasaur",
                MoveType.GRASS,
                null,
                100,
                50,
                20,
                50,
                20,
                45
        );

        pokemon.receiveDamage(50);

        assertTrue(pokemon.getHp() < 100);

        pokemon.healFull();

        assertEquals(100, pokemon.getHp());
    }
}

