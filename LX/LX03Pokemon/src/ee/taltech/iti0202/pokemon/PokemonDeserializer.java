package ee.taltech.iti0202.pokemon;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PokemonDeserializer implements JsonDeserializer<Pokemon> {
    @Override
    public Pokemon deserialize
            (JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {

        int speed = -1;
        int attack = -1;
        int defense = -1;
        int specialAttack = -1;
        int specialDefense = -1;
        int hp = -1;
        int height = -1;
        int weight = -1;
        int baseExperience = -1;

        JsonObject poke = jsonElement.getAsJsonObject();
        String pokemonName = poke.get("name").getAsString();

        JsonArray types = poke.get("types").getAsJsonArray();
        List<String> typeNames = new ArrayList<>();
        for (JsonElement typeElement : types) {
            JsonObject typeObj = typeElement.getAsJsonObject().getAsJsonObject("type");
            String typeName = typeObj.get("name").getAsString();
            typeNames.add(typeName);
        }

        JsonArray abilities = poke.get("abilities").getAsJsonArray();
        List<String> abilitiesNames = new ArrayList<>();
        for (JsonElement abilitiesElement : abilities) {
            JsonObject abilitiesObj = abilitiesElement.getAsJsonObject().getAsJsonObject("ability");
            String abilityName = abilitiesObj.get("name").getAsString();
            abilitiesNames.add(abilityName);
        }

        JsonArray forms = poke.get("forms").getAsJsonArray();
        List<String> formsNames = new ArrayList<>();
        for (JsonElement formsElement : forms) {
            JsonObject formsObj = formsElement.getAsJsonObject();
            String formName = formsObj.get("name").getAsString();
            formsNames.add(formName);
        }

        JsonArray moves = poke.get("moves").getAsJsonArray();
        List<String> movesNames = new ArrayList<>();
        for (JsonElement movesElement : moves) {
            JsonObject movesObj = movesElement.getAsJsonObject().getAsJsonObject("move");
            String moveName = movesObj.get("name").getAsString();
            movesNames.add(moveName);
        }

        height = poke.get("height").getAsInt();
        weight = poke.get("weight").getAsInt();
        baseExperience = poke.get("base_experience").getAsInt();


        JsonArray stats = poke.get("stats").getAsJsonArray();
        for (JsonElement el : stats) {
            JsonObject stat = el.getAsJsonObject();
            if (stat.get("stat").getAsJsonObject().get("name").getAsString().equals("attack")) {
                attack = stat.get("base_stat").getAsInt();
            } else if (stat.get("stat").getAsJsonObject().get("name").getAsString().equals("speed")) {
                speed = stat.get("base_stat").getAsInt();
            } else if (stat.get("stat").getAsJsonObject().get("name").getAsString().equals("defense")) {
                defense = stat.get("base_stat").getAsInt();
            } else if (stat.get("stat").getAsJsonObject().get("name").getAsString().equals("special-attack")) {
                specialAttack = stat.get("base_stat").getAsInt();
            } else if (stat.get("stat").getAsJsonObject().get("name").getAsString().equals("special-defense")) {
                specialDefense = stat.get("base_stat").getAsInt();
            } else if (stat.get("stat").getAsJsonObject().get("name").getAsString().equals("hp")) {
                hp = stat.get("base_stat").getAsInt();
            }
        }

        return new Pokemon(pokemonName, speed, attack, defense, specialAttack, specialDefense, hp
                , typeNames, abilitiesNames, formsNames, movesNames, height, weight, baseExperience);
    }
}

