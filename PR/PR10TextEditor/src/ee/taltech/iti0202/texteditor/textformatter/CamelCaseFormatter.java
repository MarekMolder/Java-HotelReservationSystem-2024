package ee.taltech.iti0202.texteditor.textformatter;

public class CamelCaseFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        if (text == null || text.isEmpty()) return "";

        String[] first = text.split(" ");
        if (first.length == 0) return "";

        String lastWord = first[first.length - 1];
        if (lastWord.endsWith("\n")) {
            if (!Character.isLetter(lastWord.charAt(lastWord.length() - 2)) && !Character.isLetter(lastWord.charAt(lastWord.length() - 3))) {
                lastWord = lastWord.substring(0, lastWord.length() - 2);
            } else {
                if (!Character.isLetter(lastWord.charAt(lastWord.length() - 1)) && !Character.isLetter(lastWord.charAt(lastWord.length() - 2))) {
                    lastWord = lastWord.substring(0, lastWord.length() - 1);
                }
            }
        }

        String[] words = text.split("[^a-zA-Z0-9]");
        if (words.length == 0) return "";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length - 1; i++) {
            String word = words[i];
            if (word.isEmpty()) continue;
            if (i == 0) {
                result.append(word.toLowerCase());
            } else {
                result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
            }
        }
        result.append(lastWord);
        return result.toString();
    }
}
