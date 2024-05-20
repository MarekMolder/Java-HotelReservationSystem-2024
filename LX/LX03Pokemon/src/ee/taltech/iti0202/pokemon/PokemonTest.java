package ee.taltech.iti0202.pokemon;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PokemonTest {

    public static Pokemon readPokemonFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(reader, Pokemon.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String filePath = "LX/LX03Pokemon/src/ee/taltech/iti0202/pokemon/PokemonUrl.json";

        Set<String> pokemonNames = new HashSet<>();
        List<Pokemon> pokemons = new ArrayList<>();
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            JsonObject jsonObject = JsonParser.parseString(content).getAsJsonObject();

            JsonArray resultsArray = jsonObject.getAsJsonArray("results");

            for (int i = 69; i < 100 && i < resultsArray.size(); i++) {
                JsonObject pokemonObject = resultsArray.get(i).getAsJsonObject();
                String name = pokemonObject.get("name").getAsString();
                pokemonNames.add(name);
            }

            System.out.println(pokemonNames);
        } catch (IOException e) {
            e.printStackTrace();
        }

        PokemonStats.processPokemonData(pokemonNames);
        for (String pokemonName : pokemonNames) {
            pokemons.add(readPokemonFromFile("pokemon_" + pokemonName + ".json"));
        }

        System.out.println(PokemonDuel.tournament(pokemons));
    }
}
