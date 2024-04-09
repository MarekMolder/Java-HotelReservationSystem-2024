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
        if (text != null) {
            if (strategy != null) {
                texts.add(strategy.format(text));
            } else {
                texts.add(text);
            }
            if (undone.isEmpty()) {
                history.add(texts.getLast());
            } else {
                history.pop();
                history.add(texts.getLast());
                undone.clear();
            }
        }
    }

    public void addText(String text, TextType type) {
        setStrategy(type);
        addText(text);
    }

    public String getCurrentText() {
        StringBuilder result = new StringBuilder();
        for (String sentence : texts) {
            if (sentence.endsWith("\n") || sentence.endsWith(" ") || texts.getLast().equals(sentence)) {
                result.append(sentence);
            } else {
                result.append(sentence + " ");
            }
        }
        return result.toString();
    }

    public String undo() {
        if (!history.isEmpty()) {
            undone.push(texts.remove(texts.size() - 1));
        }
        return getCurrentText();
    }

    public String redo() {
        if (!undone.isEmpty()) {
            texts.add(undone.pop());
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
