package sso.designPatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

//Observer Arayüzü
interface Observer1 {
	void update(String message); // Durum değişikliği olduğunda çağrılır
}

//Subject Arayüzü
interface Subject {
	void addObserver(Observer1 observer);

	void removeObserver(Observer1 observer);

	void notifyObservers(); // Observer'ları bilgilendir
}

//Somut Subject
class NewsAgency implements Subject {
	private List<Observer1> observers = new ArrayList<>();
	private String news;

	@Override
	public void addObserver(Observer1 observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(Observer1 observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (Observer1 observer : observers) {
			observer.update(news);
		}
	}

	// Haber güncellemesi
	public void setNews(String news) {
		this.news = news;
		notifyObservers(); // Observer'ları bilgilendir
	}
}

//Somut Observer
class NewsChannel implements Observer1 {
	private String name;

	public NewsChannel(String name) {
		this.name = name;
	}

	@Override
	public void update(String news) {
		System.out.println(name + " received news update: " + news);
	}
}

public class Observer {
	public static void main(String[] args) {
		NewsAgency newsAgency = new NewsAgency();

		// Observer'ları ekleyelim
		NewsChannel cnn = new NewsChannel("CNN");
		NewsChannel bbc = new NewsChannel("BBC");

		newsAgency.addObserver(cnn);
		newsAgency.addObserver(bbc);

		// Haber güncelleniyor
		newsAgency.setNews("Breaking News: BTC ath!");
		newsAgency.setNews("Breaking News: Major earthquake in Japan.");
	}
}
