package sso.designPatterns.structural;

//Banka altyapısı arayüzü
interface Banka {
	void odemeYap(double miktar);
}

//Ziraat Bankası sınıfı
class ZiraatBankasi implements Banka {
	@Override
	public void odemeYap(double miktar) {
		System.out.println("Ziraat Bankası ile ödeme yapıldı: " + miktar + "TL");
	}
}

//İş Bankası sınıfı
class IsBankasi implements Banka {
	@Override
	public void odemeYap(double miktar) {
		System.out.println("İş Bankası ile ödeme yapıldı: " + miktar + " TL");
	}
}

//Ödeme türü soyut sınıfı (Bridge görevi görecek)
abstract class Odeme {
	protected Banka banka;

	public Odeme(Banka banka) {
		this.banka = banka;
	}

	public abstract void odemeYap(double miktar);
}

//Kredi Kartı ile ödeme sınıfı
class KrediKartiOdeme extends Odeme {
	public KrediKartiOdeme(Banka banka) {
		super(banka);
	}

	@Override
	public void odemeYap(double miktar) {
		System.out.println("Kredi Kartı ile ödeme işlemi başlatıldı.");
		banka.odemeYap(miktar);
	}
}

//Banka Havalesi ile ödeme sınıfı
class BankaHavalesiOdeme extends Odeme {
	public BankaHavalesiOdeme(Banka banka) {
		super(banka);
	}

	@Override
	public void odemeYap(double miktar) {
		System.out.println("Banka Havalesi ile ödeme işlemi başlatıldı.");
		banka.odemeYap(miktar);
	}
}

//Main sınıfı, farklı ödeme türlerini ve banka altyapılarını kullanarak ödemeleri gerçekleştirir
public class Bridge {
	public static void main(String[] args) {
//Ziraat Bankası ile kredi kartı ödemesi
		Odeme krediKartiOdemeZiraat = new KrediKartiOdeme(new ZiraatBankasi());
		krediKartiOdemeZiraat.odemeYap(150.0);
//İş Bankası ile kredi kartı ödemesi
		Odeme krediKartiOdemeIs = new KrediKartiOdeme(new IsBankasi());
		krediKartiOdemeIs.odemeYap(200.0);
//Ziraat Bankası ile banka havalesi ödemesi
		Odeme havaleOdemeZiraat = new BankaHavalesiOdeme(new ZiraatBankasi());
		havaleOdemeZiraat.odemeYap(300.0);
//İş Bankası ile banka havalesi ödemesi
		Odeme havaleOdemeIs = new BankaHavalesiOdeme(new IsBankasi());
		havaleOdemeIs.odemeYap(400.0);
	}
}
