package ee.taltech.iti0202.pokemon;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PokemonDuel {

    public static Pokemon duel(Pokemon firstPokemon1, Pokemon secondPokemon2) throws FileNotFoundException {
        int turnCounter = 1;

        while (turnCounter <= 100) {
            for (int i = 0; i < 2; i++) {
                Pokemon attacker = (i == 0) ? firstPokemon1 : secondPokemon2;
                Pokemon defender = (i == 0) ? secondPokemon2 : firstPokemon1;

                int attack = (turnCounter % 3 != 0) ? attacker.getAttack() : attacker.getSpecialAttack();
                int defense = (turnCounter % 2 != 0) ? defender.getDefense() : defender.getSpecialDefense();
                defense /= 2;

                Double bestEffectiveness = 0.0;
                if (attacker.getTypes().size() > 1 && defender.getTypes().size() > 1) {
                    for (Object type : attacker.getTypes()) {
                        Double currentEffectiveness = 1.0;
                        for (Object def : defender.getTypes()) {
                            currentEffectiveness *= TypeChart.getEffectiveness(type.toString(), def.toString());
                        }
                        if (currentEffectiveness > bestEffectiveness) {
                            bestEffectiveness = currentEffectiveness;
                        }
                    }
                } else if (attacker.getTypes().size() == 1 && defender.getTypes().size() == 1) {
                    bestEffectiveness = TypeChart.getEffectiveness(attacker.getTypes().getFirst().toString(), defender.getTypes().getFirst().toString());
                } else if (attacker.getTypes().size() == 1 && defender.getTypes().size() > 1) {
                    double currentEffectiveness = 1.0;
                    for (Object type : defender.getTypes()) {
                        currentEffectiveness *= TypeChart.getEffectiveness(attacker.getTypes().getFirst().toString(), type.toString());
                    }
                    bestEffectiveness = currentEffectiveness;
                } else if (attacker.getTypes().size() > 1 && defender.getTypes().size() == 1) {
                    double currentEffectiveness = 0.0;
                    for (Object type : attacker.getTypes()) {
                        currentEffectiveness = TypeChart.getEffectiveness(type.toString(), defender.getTypes().getFirst().toString());
                        if (currentEffectiveness > bestEffectiveness) {
                            bestEffectiveness = currentEffectiveness;
                        }
                    }
                }


                double totalAttack = attack * bestEffectiveness - defense;

                if (totalAttack > 0) {
                    defender.setHp(defender.getHp() - (int) totalAttack);
                    if (defender.getHp() <= 0) {
                        return attacker;
                    }
                }

                turnCounter++;
                if (turnCounter > 100) {
                    return null;
                }
            }
        }
        return null;
    }

    public static Map<String, Integer> tournament(List<Pokemon> pokemons) throws FileNotFoundException {
        Map<String, Integer> results = new HashMap<>();
        for (Pokemon pokemon : pokemons) {
            results.put(pokemon.getName(), 0);
        }

        for (int i = 0; i < pokemons.size(); i++) {
            for (int j = i + 1; j < pokemons.size(); j++) {
                Pokemon p1 = pokemons.get(i);
                Pokemon p2 = pokemons.get(j);
                int hp1 = p1.getHp();
                int hp2 = p2.getHp();
                Pokemon firstAttacker = determineTurnOrder(p1, p2)[0];
                Pokemon secondAttacker = determineTurnOrder(p1, p2)[1];
                Pokemon winner = duel(firstAttacker, secondAttacker);
                if (winner != null) {
                    p1.setHp(hp1);
                    p2.setHp(hp2);
                    results.put(winner.getName(), results.get(winner.getName()) + 1);
                } else {
                    p1.setHp(hp1);
                    p2.setHp(hp2);
                }
            }
        }
        writeResultsToFile(results, "results.txt");
        return results;
    }

    public static Pokemon readPokemonFromFile(String filename) {
        try (FileReader reader = new FileReader(filename)) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(reader, Pokemon.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeResultsToFile(Map<String, Integer> results, String filename) {
        List<Map.Entry<String, Integer>> sortedResults = new ArrayList<>(results.entrySet());
        sortedResults.sort((e1, e2) -> {
            int compare = Integer.compare(e2.getValue(), e1.getValue());
            if (compare == 0) {
                return e1.getKey().compareTo(e2.getKey());
            }
            return compare;
        });

        try (FileWriter writer = new FileWriter(filename)) {
            for (Map.Entry<String, Integer> entry : sortedResults) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Pokemon[] determineTurnOrder(Pokemon p1, Pokemon p2) {
        if (p1.getSpeed() != p2.getSpeed()) {
            return (p1.getSpeed() > p2.getSpeed()) ? new Pokemon[]{p1, p2} : new Pokemon[]{p2, p1};
        }
        if (p1.getWeight() != p2.getWeight()) {
            return (p1.getWeight() < p2.getWeight()) ? new Pokemon[]{p1, p2} : new Pokemon[]{p2, p1};
        }
        if (p1.getHeight() != p2.getHeight()) {
            return (p1.getHeight() < p2.getHeight()) ? new Pokemon[]{p1, p2} : new Pokemon[]{p2, p1};
        }
        if (p1.getAbilities().size() != p2.getAbilities().size()) {
            return (p1.getAbilities().size() > p2.getAbilities().size()) ? new Pokemon[]{p1, p2} : new Pokemon[]{p2, p1};
        }
        if (p1.getMoves().size() != p2.getMoves().size()) {
            return (p1.getMoves().size() > p2.getMoves().size()) ? new Pokemon[]{p1, p2} : new Pokemon[]{p2, p1};
        }
        if (p1.getBase_experience() != p2.getBase_experience()) {
            return (p1.getBase_experience() > p2.getBase_experience()) ? new Pokemon[]{p1, p2} : new Pokemon[]{p2, p1};
        }
        return new Pokemon[]{null, null};
    }
}
