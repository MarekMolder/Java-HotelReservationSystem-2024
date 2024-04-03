package ee.taltech.iti0202.texteditor.textformatter;

public class BinaryFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        if (text == null || text.isEmpty()) return "";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (i == text.length() - 1 || (i == text.length() - 2 && text.charAt(i + 1) == '\n')) {
                result.append(Integer.toBinaryString(c));
            } else {
                result.append(String.format("%8s", Integer.toBinaryString(c)).replaceAll(" ", "0"));
            }
        }
        return result.toString();
    }
}
