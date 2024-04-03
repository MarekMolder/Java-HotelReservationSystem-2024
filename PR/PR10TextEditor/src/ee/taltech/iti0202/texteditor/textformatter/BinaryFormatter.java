package ee.taltech.iti0202.texteditor.textformatter;

public class BinaryFormatter implements TextFormatter {
    @Override
    public String format(String text) {
        if (text == null || text.isEmpty()) return "";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            String binary = String.format("%8s", Integer.toBinaryString(c & 0xFF)).replace(' ', '0');
            result.append(binary);
        }

        return result.toString();
    }
}
