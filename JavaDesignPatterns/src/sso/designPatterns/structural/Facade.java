package sso.designPatterns.structural;

//Televizyon sınıfı
class Television {
	public void turnOn() {
		System.out.println("Turning on the TV");
	}

	public void turnOff() {
		System.out.println("Turning off the TV");
	}
}

//DVD oynatıcı sınıfı
class DvdPlayer {
	public void turnOn() {
		System.out.println("Turning on the DVD player");
	}

	public void turnOff() {
		System.out.println("Turning off the DVD player");
	}

	public void play() {
		System.out.println("Playing DVD");
	}

	public void stop() {
		System.out.println("Stopping DVD");
	}
}

//Ses sistemi sınıfı
class SoundSystem {
	public void turnOn() {
		System.out.println("Turning on the sound system");
	}

	public void turnOff() {
		System.out.println("Turning off the sound system");
	}
}

class HomeTheaterFacade {
	private Television television;
	private DvdPlayer dvdPlayer;
	private SoundSystem soundSystem;

	public HomeTheaterFacade(Television television, DvdPlayer dvdPlayer, SoundSystem soundSystem) {
		this.television = television;
		this.dvdPlayer = dvdPlayer;
		this.soundSystem = soundSystem;
	}

	// Film izlemeye başlamak için gereken adımlar
	public void watchMovie() {
		System.out.println("Get ready to watch a movie...");
		television.turnOn();
		soundSystem.turnOn();
		dvdPlayer.turnOn();
		dvdPlayer.play();
	}

	// Film izlemeyi bitirip her şeyi kapatma
	public void endMovie() {
		System.out.println("Shutting down the movie...");
		dvdPlayer.stop();
		dvdPlayer.turnOff();
		soundSystem.turnOff();
		television.turnOff();
	}
}

public class Facade {
	public static void main(String[] args) {
		// Alt sistemlerin oluşturulması
		Television tv = new Television();
		DvdPlayer dvd = new DvdPlayer();
		SoundSystem sound = new SoundSystem();

		// Facade sınıfı oluşturuluyor
		HomeTheaterFacade homeTheater = new HomeTheaterFacade(tv, dvd, sound);

		// Film izlemeye başlama
		homeTheater.watchMovie();

		// Film izlemeyi bitirme
		homeTheater.endMovie();
	}
}
