package ee.taltech.iti0202.texteditor;

import ee.taltech.iti0202.texteditor.textformatter.*;

import java.util.*;

public class TextEditor {
    private List<String> texts;
    private Stack<String> history;
    private Stack<String> undone;

    private TextFormatter strategy;

    public TextEditor() {
        this.texts = new ArrayList<>();
        this.history = new Stack<>();
        this.undone = new Stack<>();
    }

    public void addText(String text) {
        if (text == null) {
            texts.add("");
            history.add("");
        }
        String formattedText = (strategy != null) ? strategy.format(text) : text;
        this.texts.add(formattedText);
        history.add(formattedText);
        undone.clear();
    }

    public void addText(String text, TextType type) {
        setStrategy(type);
        addText(text);
    }

    public String getCurrentText() {
        StringBuilder result = new StringBuilder();
        for (String sentence: texts) {
            if (sentence.endsWith("\n") || sentence.endsWith(" ")) {
                result.append(sentence);
            } else {
                result.append(sentence + " ");
            }
        }
        return result.toString();
    }

    public String undo() {
        if (!history.isEmpty()) {
            String removed = history.removeLast();
            texts.remove(removed);
            undone.push(removed);
        }
        return getCurrentText();
    }

    public String redo() {
        if (!undone.isEmpty()) {
            String added = undone.pop();
            texts.add(added);
            history.push(added);
        }
        return getCurrentText();
    }

    public void clear() {
        texts.clear();
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
