package pokemon;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PokemonFactoryTest {

    @Test
    void debeCrearPokemonCorrectamente() {
        Pokemon pikachu = PokemonFactory.createPokemon(25);

        assertEquals("Pikachu", pikachu.getName());
        assertEquals(25, pikachu.getId());
    }

    @Test
    void pokemonDebeTenerTiposCorrectos() {
        Pokemon charizard = PokemonFactory.createPokemon(6);

        assertNotNull(charizard.getType1());
        assertNotNull(charizard.getType2());
    }

    @Test
    void debeLanzarExcepcionConIdInvalido() {
        assertThrows(
                IllegalArgumentException.class,
                () -> PokemonFactory.createPokemon(9999)
        );
    }
}
