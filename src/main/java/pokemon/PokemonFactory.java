package pokemon;

import moves.MoveType;

public class PokemonFactory {

    public static Pokemon createPokemon(int index) {

        String data = PokemonDatabase.POKEMONS[index];

        String[] values = data.split(";");

        return new Pokemon(
                Integer.parseInt(values[0]),
                values[1],
                MoveType.valueOf(values[2]),
                values[3].equals("null")
                        ? null
                        : MoveType.valueOf(values[3]),
                Integer.parseInt(values[4]),
                Integer.parseInt(values[5]),
                Integer.parseInt(values[6]),
                Integer.parseInt(values[7]),
                Integer.parseInt(values[8]),
                Integer.parseInt(values[9]),
                Integer.parseInt(values[10])
        );
    }
}
