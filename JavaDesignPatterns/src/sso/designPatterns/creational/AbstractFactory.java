package sso.designPatterns.creational;

interface Button {
	void render();
}

interface Label {
	void render();
}

interface GUIFactory {
	Button createButton();
	Label createLabel();
}

class WindowsButton implements Button {
	@Override
	public void render() {
		System.out.println("Rendering a Windows button");
	}
}

class MacOSButton implements Button {
	@Override
	public void render() {
		System.out.println("Rendering a macOS button");
	}
}

class WindowsLabel implements Label {
	@Override
	public void render() {
		System.out.println("Rendering a Windows label");
	}
}

class MacOSLabel implements Label {
	@Override
	public void render() {
		System.out.println("Rendering a macOS label");
	}
}

class WindowsFactory implements GUIFactory {
	@Override
	public Button createButton() {
		return new WindowsButton();
	}

	@Override
	public Label createLabel() {
		return new WindowsLabel();
	}
}

class MacOSFactory implements GUIFactory {
	@Override
	public Button createButton() {
		return new MacOSButton();
	}

	@Override
	public Label createLabel() {
		return new MacOSLabel();
	}
}
class Client {
    private Button button;
    private Label label;

    public Client(GUIFactory factory) {
        button = factory.createButton();
        label = factory.createLabel();
    }

    public void renderUI() {
        button.render();
        label.render();
    }

   
}

public class AbstractFactory {
	 public static void main(String[] args) {
	        GUIFactory factory;

	        // Kullanıcı Windows işletim sistemi istiyor
	        factory = new WindowsFactory();
	        Client client = new Client(factory);
	        client.renderUI();  // Çıktı: Rendering a Windows button, Rendering a Windows label

	        // Kullanıcı macOS işletim sistemi istiyor
	        factory = new MacOSFactory();
	        client = new Client(factory);
	        client.renderUI();  // Çıktı: Rendering a macOS button, Rendering a macOS label
	    }
}
