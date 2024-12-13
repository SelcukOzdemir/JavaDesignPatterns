package sso.designPatterns.structural;

import java.util.ArrayList;
import java.util.List;

//İnternet arayüzü
interface Internet {
	void siteyeBaglan(String url) throws Exception;
}

// Gerçek İnternet sınıfı
class GercekInternet implements Internet {
	@Override
	public void siteyeBaglan(String url) {
		System.out.println(url + " sitesine bağlanılıyor...");
	}
}

public class Proxy {

}

//Proxy İnternet sınıfı
class ProxyInternet implements Internet {
	private GercekInternet gercekInternet = new GercekInternet();
	private static List<String> yasakliSiteler;
	static {
		// Yasaklı siteleri tanımlıyoruz
		yasakliSiteler = new ArrayList<>();
		yasakliSiteler.add("facebook.com");
		yasakliSiteler.add("instagram.com");
		yasakliSiteler.add("twitter.com");
	}

	@Override
	public void siteyeBaglan(String url) throws Exception {
		// Yasaklı site kontrolü
		if (yasakliSiteler.contains(url.toLowerCase())) {
			throw new Exception("Erişim engellendi: " + url + " sitesine erişim yasaklı.");
		}
		// Erişim izinliyse gerçek internetten siteye bağlan
		gercekInternet.siteyeBaglan(url);
	}
}

// Main sınıfı, Proxy Internet üzerinden siteye erişim yapar
class Main {
	public static void main(String[] args) {
		Internet internet = new ProxyInternet();
		try {
			internet.siteyeBaglan("google.com");
			internet.siteyeBaglan("facebook.com");
			internet.siteyeBaglan("yahoo.com");
			internet.siteyeBaglan("instagram.com");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
