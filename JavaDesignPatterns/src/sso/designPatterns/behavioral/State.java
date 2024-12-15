package sso.designPatterns.behavioral;

//State Arayüzü
 interface PhoneState {
 void pressPowerButton();  // Güç düğmesine basıldığında ne olur?
 void swipeScreen();       // Ekranı kaydırma hareketi
 void answerCall();        // Arama cevaplama
}

//Ekran Kapalı Durumu
 class ScreenOffState implements PhoneState {
  private Phone phone;

  public ScreenOffState(Phone phone) {
      this.phone = phone;
  }

  @Override
  public void pressPowerButton() {
      System.out.println("Ekran açıldı.");
      phone.setState(phone.getScreenOnState()); // Durumu değiştir
  }

  @Override
  public void swipeScreen() {
      System.out.println("Ekran kapalı, kaydırma işlemi yapılmaz.");
  }

  @Override
  public void answerCall() {
      System.out.println("Telefon kapalı, çağrı yapılamaz.");
  }
}
 
//Ekran Açık Durumu
 class ScreenOnState implements PhoneState {
  private Phone phone;

  public ScreenOnState(Phone phone) {
      this.phone = phone;
  }

  @Override
  public void pressPowerButton() {
      System.out.println("Ekran kapalı.");
      phone.setState(phone.getScreenOffState()); // Durumu değiştir
  }

  @Override
  public void swipeScreen() {
      System.out.println("Ekran kaydırıldı. Ana ekran açıldı.");
  }

  @Override
  public void answerCall() {
      System.out.println("Çağrı cevaplandı.");
  }
}
 
//Akıllı Telefon Sınıfı (Context)
 class Phone {
  private PhoneState screenOffState;
  private PhoneState screenOnState;
  private PhoneState currentState;

  public Phone() {
      screenOffState = new ScreenOffState(this);
      screenOnState = new ScreenOnState(this);
      currentState = screenOffState;  // Başlangıçta ekran kapalı
  }

  // Durum değiştirme
  public void setState(PhoneState state) {
      this.currentState = state;
  }

  // Durumla ilgili metotlar
  public void pressPowerButton() {
      currentState.pressPowerButton();
  }

  public void swipeScreen() {
      currentState.swipeScreen();
  }

  public void answerCall() {
      currentState.answerCall();
  }

  // Durumlara ait getter'lar
  public PhoneState getScreenOffState() {
      return screenOffState;
  }

  public PhoneState getScreenOnState() {
      return screenOnState;
  }
}
public class State {

	public static void main(String[] args) {
        Phone phone = new Phone();

        // Başlangıçta ekran kapalı
        phone.pressPowerButton(); // Ekran açıldı.
        phone.swipeScreen();      // Ekran kaydırıldı. Ana ekran açıldı.
        
        // Ekran açıkken ekranı kapatmak
        phone.pressPowerButton(); // Ekran kapalı.
        phone.answerCall();       // Telefon kapalı, çağrı yapılamaz.
    }
}
