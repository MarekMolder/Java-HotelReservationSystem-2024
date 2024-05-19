package ee.taltech.iti0202.pokemon;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TypeChart {
    private static double[][] typeChart = new double[18][18];
    private static String[] types = {"normal", "fighting", "flying", "poison", "ground", "rock", "bug", "ghost", "steel", "fire", "water", "grass", "electric", "psychic", "ice", "dragon", "dark", "fairy"};
    private static Map<String, Integer> typeIndexMap = new HashMap<>();

    public static void main(String[] args) {
        try {
            initializeTypeChart();

            String attackType = "fairy";
            String defenseType = "dragon";

            double effectiveness = getEffectiveness(attackType, defenseType);
            System.out.println("Effectiveness of " + attackType + " against " + defenseType + " is " + effectiveness);
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }

    private static void initializeTypeChart() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("LX/LX03Pokemon/src/ee/taltech/iti0202/pokemon/matrix"));

        for (int i = 0; i < types.length; i++) {
            typeIndexMap.put(types[i], i);
        }

        fileScanner.nextLine();

        int rowIndex = 0;
        while (fileScanner.hasNextLine() && rowIndex < 18) {
            String line = fileScanner.nextLine();
            String[] parts = line.trim().split("\\s+");

            for (int colIndex = 1; colIndex < parts.length; colIndex++) {
                typeChart[rowIndex][colIndex - 1] = Double.parseDouble(parts[colIndex]);
            }
            rowIndex++;
        }
        fileScanner.close();
    }

    private static double getEffectiveness(String attackType, String defenseType) {
        Integer attackIndex = typeIndexMap.get(attackType);
        Integer defenseIndex = typeIndexMap.get(defenseType);

        if (attackIndex == null || defenseIndex == null) {
            System.out.println("Invalid type");
            return -1.0;
        }

        return typeChart[attackIndex][defenseIndex];
    }
}

