package ee.taltech.iti0202.texteditor.textformatter;

public class TitleCaseFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        if (text == null || text.isEmpty()) return "";

        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();

        if (words.length > 0) {
            result.append(words[0].substring(0, 1).toUpperCase()).append(words[0].substring(1)).append(" ");
        }

        for (int i = 1; i < words.length - 1; i++) {
            if (words[i].isEmpty()) continue;

            if (words[i].equalsIgnoreCase("a") || words[i].equalsIgnoreCase("an") ||
                    words[i].equalsIgnoreCase("of") || words[i].equalsIgnoreCase("the") ||
                    words[i].equalsIgnoreCase("and") || words[i].equalsIgnoreCase("or")) {
                result.append(words[i].toLowerCase()).append(" ");
            } else {
                result.append(words[i].substring(0, 1).toUpperCase()).append(words[i].substring(1)).append(" ");
            }
        }
        if (words.length > 0) {
            result.append(words[words.length -1].substring(0, 1).toUpperCase()).append(words[words.length - 1].substring(1)).append(" ");
        }
        return result.toString().trim();
    }
}
