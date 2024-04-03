package ee.taltech.iti0202.texteditor.textformatter;

public class CamelCaseFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        if (text == null || text.isEmpty()) return "";

        String[] first = text.split(" ");

        String lastWord = first[text.length()];

        String[] words = text.split("[^a-zA-Z0-9]");
        if (words.length == 0) return "";

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            String word = words[i];
            if (word.isEmpty()) continue;
            if (i == 0) {
                result.append(word.substring(0, 1).toLowerCase()).append(word.substring(1));
            } else {
                result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
            }
        }
        result.append(lastWord);
        return result.toString();
    }
}
