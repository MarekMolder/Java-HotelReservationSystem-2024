package ee.taltech.iti0202.pokemon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class PokemonStats {

    public static void main(String[] args) {
        Set<String> pokemonNames = new HashSet<>();
        pokemonNames.add("bulbasaur");
        pokemonNames.add("ivysaur");

        String filename = "LX/LX03Pokemon/src/ee/taltech/iti0202/pokemon/PokemonUrl.json";
        processPokemonData(filename, pokemonNames);
    }

    public static void processPokemonData(String filename, Set<String> pokemonNames) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line);
            }
            JsonObject jsonObject = JsonParser.parseString(jsonContent.toString()).getAsJsonObject();
            JsonArray results = jsonObject.getAsJsonArray("results");

            for (int i = 0; i < results.size(); i++) {
                JsonObject pokemon = results.get(i).getAsJsonObject();
                String name = pokemon.get("name").getAsString();

                if (pokemonNames.contains(name)) {
                    String pokeUrl = pokemon.get("url").getAsString();
                    Pokemon pokemonData = readPokemonData(pokeUrl);

                    if (pokemonData != null) {
                        String outputFilename = "pokemon_" + name + ".json";
                        if (!fileExists(outputFilename)) {
                            writePokemonToJsonFile(pokemonData, outputFilename);
                            System.out.println("Wrote data for " + name + " to " + outputFilename);
                        } else {
                            System.out.println("File for " + name + " already exists. Skipping...");
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean fileExists(String filename) {
        File file = new File(filename);
        return file.exists();
    }

    public static Pokemon readPokemonData(String pokeUrl) {
        StringBuilder pokeString = new StringBuilder();
        try {
            URL url = new URL(pokeUrl);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    pokeString.append(line);
                }
            }
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(Pokemon.class, new PokemonDeserializer());
            Gson gson = builder.create();
            return gson.fromJson(pokeString.toString(), Pokemon.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writePokemonToJsonFile(Pokemon pokemon, String filename) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filename)) {
            gson.toJson(pokemon, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
