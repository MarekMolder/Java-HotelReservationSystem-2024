package ee.taltech.iti0202.pokemon;
import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
public class ReadMainPage {

    private static final String POKE_API_URL = "https://pokeapi.co/api/v2/pokemon?offset=0&limit=100000";
    private static final String OUTPUT_FILE_PATH = "LX/LX03Pokemon/src/ee/taltech/iti0202/pokemon/PokemonUrl.json";

    public static JsonArray fetchPokemonData(Integer offset, Integer limit) {
        StringBuilder pokeString = new StringBuilder();
        String apiUrl = "https://pokeapi.co/api/v2/pokemon?offset=" + offset + "&limit=" + limit;

        try {
            URL url = new URL(apiUrl);
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    pokeString.append(line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error fetching Pokemon data", e);
        }

        JsonObject jsonObject = JsonParser.parseString(pokeString.toString()).getAsJsonObject();
        JsonArray resultsArray = jsonObject.getAsJsonArray("results");

        JsonArray pokemonList = new JsonArray();
        for (JsonElement element : resultsArray) {
            JsonObject pokemon = element.getAsJsonObject();
            String name = pokemon.get("name").getAsString();
            String pokemonUrl = pokemon.get("url").getAsString();

            JsonObject pokemonObject = new JsonObject();
            pokemonObject.add("name", new JsonPrimitive(name));
            pokemonObject.add("url", new JsonPrimitive(pokemonUrl));

            pokemonList.add(pokemonObject);
        }
        writeToJsonFile(pokemonList, OUTPUT_FILE_PATH);
        return pokemonList;
    }

    private static void writeToJsonFile(JsonArray pokemonList, String outputFilePath) {
        JsonObject outputObject = new JsonObject();
        outputObject.add("results", pokemonList);

        try {
            Files.write(Paths.get(outputFilePath), outputObject.toString().getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to output file", e);
        }
    }
}

