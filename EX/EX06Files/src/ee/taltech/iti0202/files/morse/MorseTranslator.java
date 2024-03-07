package ee.taltech.iti0202.files.morse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MorseTranslator {
    Map<String, String> morseCodeMap = new HashMap<>();

    public Map<String, String> addMorseCodes(List<String> lines) {
        for (String letter : lines) {
            String[] half = letter.split(" ");
            morseCodeMap.put(half[0].toLowerCase(), half[1].toLowerCase());
        }
        return morseCodeMap;
    }

    public List<String> translateLinesToMorse(List<String> lines) {
        List<String> translatedList = new ArrayList<>();
        for (String line : lines) {
            translatedList.add(translateLineToMorse(line));
        }
        return translatedList;
    }

    public List<String> translateLinesFromMorse(List<String> lines) {
        List<String> translatedList = new ArrayList<>();
        for (String line : lines) {
            translatedList.add(translateLineFromMorse(line));
        }
        return translatedList;
    }

    private String translateLineToMorse(String line) {
        StringBuilder translated = new StringBuilder(new String());
        String[] parts = line.split(" ");
        for (String word : parts) {
            if (!translated.isEmpty()) {
                translated.append("\t");
            }
            for (char c : word.toCharArray()) {
                if (morseCodeMap.containsKey(c)) {
                    translated.append(morseCodeMap.get(c));
                    translated.append(" ");
                }
            }
        }
        return translated.toString();
    }

    private String translateLineFromMorse(String line) {
        StringBuilder translated = new StringBuilder(new String());
        String[] parts = line.split("\t");
        for (String word : parts) {
            if (!translated.isEmpty()) {
                translated.append(" ");
            }
            for (char c : word.toCharArray()) {
                for (Map.Entry<String, String> entry : morseCodeMap.entrySet()) {
                    translated.append(entry.getKey());
                }
            }
        }
        return translated.toString();
    }
}