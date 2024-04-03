package ee.taltech.iti0202.texteditor.textformatter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

public class TextEditor {

    private TextFormatter strategy;
    private StringBuilder text = new StringBuilder();
    private Stack<String> history = new Stack<>();
    private Stack<String> undone = new Stack<>();


    public void addText(String text) {
        String formattedText = (strategy != null) ? strategy.format(text) : text;
        this.text.append(formattedText);
        history.push(formattedText);
        undone.clear();
    }

    public void addText(String text, TextType type) {
        setStrategy(type);
        addText(text);
    }

    public String getCurrentText() {
        return text.toString().replaceAll("(?m)\\s+$", "").replaceAll("(?m)^\\s+", "").replaceAll("\\s+", " ");
    }

    public String undo() {
        if (!history.isEmpty()) {
            String removed = history.pop();
            undone.push(removed);
            text.setLength(0);
            for (String str : history) {
                text.append(str);
            }
        }
        return getCurrentText();
    }

    public String redo() {
        if (!undone.isEmpty()) {
            String added = undone.pop();
            history.push(added);
            text.setLength(0);
            for (String str : history) {
                text.append(str);
            }
        }
        return getCurrentText();
    }

    public void clear() {
        text.setLength(0);
        history.clear();
        undone.clear();
    }

    public void setStrategy(TextType type) {
        switch (type) {
            case PLAIN:
                strategy = null;
                break;
            case SCREAMING:
                strategy = new UppercaseFormatter();
                break;
            case TITLE:
                strategy = new TitleCaseFormatter();
                break;
            case CAMELCASE:
                strategy = new CamelCaseFormatter();
                break;
            case BINARY:
                strategy = new BinaryFormatter();
                break;
        }
    }

    public Collection<String> getHistory() {
        return new ArrayList<>(history);
    }

    public TextFormatter getStrategy() {
        return strategy;
    }

}
