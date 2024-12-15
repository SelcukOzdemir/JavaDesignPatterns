package sso.designPatterns.behavioral;


abstract class DataValidator {
    // Template Method: Doğrulama algoritması
    public final boolean validate(String data) {
        if (isEmpty(data)) {
            showError("Veri boş olamaz!");
            return false;
        }
        if (!isValid(data)) { // Özelleştirilmiş adım
            showError(getErrorMessage());
            return false;
        }
        return true; // Doğrulama başarılı
    }

    // Boş olup olmadığını kontrol et
    private boolean isEmpty(String data) {
        return data == null || data.trim().isEmpty();
    }

    // Alt sınıfların uygulaması gereken yöntemler
    protected abstract boolean isValid(String data);
    protected abstract String getErrorMessage();

    // Hata mesajını göster
    private void showError(String message) {
        System.out.println("Hata: " + message);
    }
}

class EmailValidator extends DataValidator {
    @Override
    protected boolean isValid(String data) {
        // Basit bir e-posta kontrolü
        return data.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }

    @Override
    protected String getErrorMessage() {
        return "Geçersiz e-posta formatı!";
    }
}

class PhoneNumberValidator extends DataValidator {
    @Override
    protected boolean isValid(String data) {
        // Basit bir telefon numarası kontrolü (örnek: 10 haneli sayı)
        return data.matches("^\\d{10}$");
    }

    @Override
    protected String getErrorMessage() {
        return "Geçersiz telefon numarası!";
    }
}


class UsernameValidator extends DataValidator {
    @Override
    protected boolean isValid(String data) {
        // Kullanıcı adı yalnızca harf ve rakamlardan oluşmalı, en az 3 karakter
        return data.matches("^[a-zA-Z0-9]{3,}$");
    }

    @Override
    protected String getErrorMessage() {
        return "Kullanıcı adı en az 3 karakter uzunluğunda olmalı ve yalnızca harf/rakam içermeli!";
    }
}

public class TemplateMethod {
	public static void main(String[] args) {
        DataValidator emailValidator = new EmailValidator();
        DataValidator phoneValidator = new PhoneNumberValidator();
        DataValidator usernameValidator = new UsernameValidator();

        System.out.println("E-posta doğrulama:");
        System.out.println("Sonuç: " + emailValidator.validate("example@mail.com")); // Beklenen: true
        System.out.println("Sonuç: " + emailValidator.validate("invalid-email"));    // Beklenen: false

        System.out.println("\nTelefon doğrulama:");
        System.out.println("Sonuç: " + phoneValidator.validate("1234567890")); // Beklenen: true
        System.out.println("Sonuç: " + phoneValidator.validate("12345abc"));   // Beklenen: false

        System.out.println("\nKullanıcı adı doğrulama:");
        System.out.println("Sonuç: " + usernameValidator.validate("user123"));   // Beklenen: true
        System.out.println("Sonuç: " + usernameValidator.validate("u@"));  
    }
}
