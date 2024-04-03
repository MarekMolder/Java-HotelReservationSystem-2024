package ee.taltech.iti0202.texteditor.textformatter;

public class TitleCaseFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        if (text == null || text.isEmpty()) return "";

        String[] words = text.split(" ");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) continue;

            if (word.equalsIgnoreCase("a") || word.equalsIgnoreCase("an") ||
                    word.equalsIgnoreCase("of") || word.equalsIgnoreCase("the") ||
                    word.equalsIgnoreCase("and") || word.equalsIgnoreCase("or")) {
                result.append(word.toLowerCase()).append(" ");
            } else {
                result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1)).append(" ");
            }
        }
        return result.toString().trim() + "\n";
    }
}
