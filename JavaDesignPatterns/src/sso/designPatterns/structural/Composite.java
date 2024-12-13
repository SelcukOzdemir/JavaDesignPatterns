package sso.designPatterns.structural;

import java.util.ArrayList;
import java.util.List;

interface FileSystemComponent {
	void showDetails(); // Her bileşenin detaylarını gösteren metod
}

class File implements FileSystemComponent {
	private String name;
	private int size; // Dosya boyutu (MB cinsinden)

	public File(String name, int size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public void showDetails() {
		System.out.println("File: " + name + ", Size: " + size + "MB");
	}
}

class Folder implements FileSystemComponent {
	private String name;
	private List<FileSystemComponent> components = new ArrayList<>();

	public Folder(String name) {
		this.name = name;
	}

	// Klasöre dosya veya başka bir klasör eklemek
	public void addComponent(FileSystemComponent component) {
		components.add(component);
	}

	// Klasörden dosya veya klasör çıkarmak
	public void removeComponent(FileSystemComponent component) {
		components.remove(component);
	}

	@Override
	public void showDetails() {
		System.out.println("Folder: " + name);
		// Klasörün içindeki her bileşenin detaylarını göster
		for (FileSystemComponent component : components) {
			component.showDetails();
		}
	}
}

public class Composite {
	public static void main(String[] args) {
		// Dosya nesneleri oluşturuluyor
		File file1 = new File("File1.txt", 10);
		File file2 = new File("File2.jpg", 15);
		File file3 = new File("File3.mp3", 20);

		// Klasör nesneleri oluşturuluyor
		Folder folder1 = new Folder("Folder1");
		Folder folder2 = new Folder("Folder2");

		// Dosyaları klasörlere ekliyoruz
		folder1.addComponent(file1);
		folder1.addComponent(file2);
		folder2.addComponent(file3);

		// Ana klasör, her iki alt klasörü içerecek
		Folder mainFolder = new Folder("MainFolder");
		mainFolder.addComponent(folder1);
		mainFolder.addComponent(folder2);

		// Detayları yazdıralım
		mainFolder.showDetails();
	}
}
