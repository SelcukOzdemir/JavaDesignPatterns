package sso.designPatterns.creational;

public class Singleton {
	// volatile ile işaretlenir ki tüm threadler için tutarlı olsun
	private static volatile Singleton instance;

	public Singleton() {
		System.out.println("Singelton oluşturuldu.");
	}
	
	public static Singleton getInstance() {
		// İlk kontrol, synchronized olmadan yapılır
		if (instance == null) {
			synchronized (Singleton.class) {
				// İkinci kontrol synchronized içinde yapılır
				if (instance == null) {
				instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
	public void bilgiVer() {
		System.out.println("Bu nesne Singleton bilgisini temsil eder.");
		}
	

}

 class SingeltonMain {
	public static void main(String[] args) {
	// Singleton örneğine erişim
		Singleton sn = Singleton.getInstance();
	sn.bilgiVer(); // Çıktı: "Bu nesne Singleton bilgisini temsil eder."
	}
	}
