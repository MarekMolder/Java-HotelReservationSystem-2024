package ee.taltech.iti0202.files.morse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseTranslator {
    Map<String, String> morseCodeMapLower = new HashMap<>();
    Map<String, String> morseCodeMapUpper = new HashMap<>();

    /**
     * Method to read morse code into a Map.
     * @param lines
     * @return
     */
    public Map<String, String> addMorseCodes(List<String> lines) {
        for (String letter : lines) {
            String[] half = letter.split(" ");
            morseCodeMapLower.put(half[0].toLowerCase(), half[1].toLowerCase());
            morseCodeMapUpper.put(half[0].toUpperCase(), half[1].toUpperCase());
        }
        return morseCodeMapLower;
    }

    /**
     * Method to translate lines into morse.
     * @param lines
     * @return
     */
    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> translatedList = new ArrayList<>();
        for (String line : lines) {
            translatedList.add(translateLineToMorse(line));
        }
        return translatedList;
    }

    /**
     * Method to translate lines from morse.
     * @param lines
     * @return
     */
    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> translatedList = new ArrayList<>();
        for (String line : lines) {
            translatedList.add(translateLineFromMorse(line));
        }
        return translatedList;
    }

    /**
     * Method to translate line into morse.
     * @param line
     * @return
     */
    public String translateLineToMorse(String line) {
        StringBuilder translated = new StringBuilder(new String());
        String[] parts = line.split(" ");
        for (String word : parts) {
            if (!translated.isEmpty()) {
                translated.deleteCharAt(translated.length() - 1);
                translated.append("\t");
            }
            String[] letter = word.split("");
            for (String c : letter) {
                if (morseCodeMapLower.containsKey(c)) {
                    translated.append(morseCodeMapLower.get(c));
                    translated.append(" ");
                } else {
                    translated.append(morseCodeMapUpper.get(c));
                    translated.append(" ");
                }
            }
        }
        return translated.toString().trim();
    }

    /**
     * Method to translate line from morse.
     * @param line
     * @return
     */
    public String translateLineFromMorse(String line) {
        StringBuilder translated = new StringBuilder(new String());
        String[] parts = line.split("\t");
        for (String word : parts) {
            if (!translated.isEmpty()) {
                translated.append(" ");
            }
            String[] letters = word.split(" ");
            for (String letter : letters) {
                for (Map.Entry<String, String> entry : morseCodeMapLower.entrySet()) {
                    if (entry.getValue().equals(letter)) {
                        translated.append(entry.getKey());
                    }
                }
            }
        }
        return translated.toString();
    }
}
