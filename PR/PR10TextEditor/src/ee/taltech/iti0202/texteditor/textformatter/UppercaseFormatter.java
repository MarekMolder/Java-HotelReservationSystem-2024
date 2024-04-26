package ee.taltech.iti0202.texteditor.textformatter;

public class UppercaseFormatter implements TextFormatter {

    @Override
    public String format(String text) {
        if (text == null || text.isEmpty()) return "";
        return text.toUpperCase();
    }
}
