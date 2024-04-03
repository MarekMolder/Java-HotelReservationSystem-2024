package ee.taltech.iti0202.texteditor.textformatter;

import ee.taltech.iti0202.texteditor.textformatter.TextFormatter;

public class CamelCaseFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        if (text == null || text.isEmpty()) return "";
        String[] words = text.split("[^a-zA-Z0-9]");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.isEmpty()) continue;
            if (i == 0) {
                result.append(word.substring(0, 1).toLowerCase()).append(word.substring(1));
            } else {
                result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
            }
        }
        if (text.endsWith("\n")) {
            result.append("\n");
        }
        return result.toString();
    }
}
