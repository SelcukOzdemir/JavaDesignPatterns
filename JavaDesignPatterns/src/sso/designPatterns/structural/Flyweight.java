package sso.designPatterns.structural;

import java.util.HashMap;
import java.util.Map;

interface Character {
    void display(int x, int y); // Karakteri ekranda göster
}
class ConcreteCharacter implements Character {
    private String characterType;  // Ortak özellik (Örneğin: 'A', 'B', 'C' gibi harfler)

    public ConcreteCharacter(String characterType) {
        this.characterType = characterType;
    }

    @Override
    public void display(int x, int y) {
        System.out.println("Displaying character " + characterType + " at (" + x + ", " + y + ")");
    }
}

class FlyweightFactory {
    private Map<String, Character> characters = new HashMap<>();

    // Belirli bir karakteri döndürür, eğer zaten varsa mevcut karakteri geri döndürür
    public Character getCharacter(String characterType) {
        Character character = characters.get(characterType);

        if (character == null) {
            character = new ConcreteCharacter(characterType);
            characters.put(characterType, character);
        }

        return character;
    }
}
public class Flyweight {
	 public static void main(String[] args) {
	        FlyweightFactory factory = new FlyweightFactory();

	        // Aynı 'A' karakteri için yalnızca bir nesne oluşturulur
	        Character characterA = factory.getCharacter("A");
	        characterA.display(10, 20);

	        // Aynı 'B' karakteri için yalnızca bir nesne oluşturulur
	        Character characterB = factory.getCharacter("B");
	        characterB.display(30, 40);

	        // 'A' karakteri zaten var, yeni bir nesne oluşturulmaz
	        Character characterA2 = factory.getCharacter("A");
	        characterA2.display(50, 60);

	        // Aynı 'A' karakteri için sadece bir nesne vardır.
	    }
}
