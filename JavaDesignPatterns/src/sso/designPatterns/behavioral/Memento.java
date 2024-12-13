package sso.designPatterns.behavioral;

import java.util.Stack;

class Memento1 {
    private final String content;

    public Memento1(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class TextEditor {
    private String content;

    public TextEditor(String content) {
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    // Memento oluşturma
    public Memento1 saveToMemento() {
        return new Memento1(content);
    }

    // Memento'dan geri yükleme
    public void restoreFromMemento(Memento1 memento) {
        this.content = memento.getContent();
    }
}

class Caretaker {
    private Stack<Memento1> mementoStack = new Stack<>();

    // Memento saklama
    public void addMemento(Memento1 memento) {
        mementoStack.push(memento);
    }

    // Son mementoyu alma
    public Memento1 getLastMemento() {
        if (!mementoStack.isEmpty()) {
            return mementoStack.pop();
        }
        return null;
    }
}

public class Memento {
	public static void main(String[] args) {
        // TextEditor oluşturuluyor
        TextEditor editor = new TextEditor("Initial text");

        // Caretaker (bakıcı) oluşturuluyor
        Caretaker caretaker = new Caretaker();

        // Metin düzenleniyor ve memento kaydediliyor
        System.out.println("Original Text: " + editor.getContent());
        caretaker.addMemento(editor.saveToMemento());

        editor.setContent("First change");
        System.out.println("Text after first change: " + editor.getContent());

        // Yeni bir memento kaydediliyor
        caretaker.addMemento(editor.saveToMemento());

        editor.setContent("Second change");
        System.out.println("Text after second change: " + editor.getContent());

        // Geri alma işlemi
        editor.restoreFromMemento(caretaker.getLastMemento());
        System.out.println("Text after undo: " + editor.getContent());

        // İlk duruma geri dönme
        editor.restoreFromMemento(caretaker.getLastMemento());
        System.out.println("Text after undo to original: " + editor.getContent());
    }
}
