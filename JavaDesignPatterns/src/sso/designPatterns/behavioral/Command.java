package sso.designPatterns.behavioral;

interface CommandInterface {
    void execute();  // Komutun yürütülmesi
    void undo();     // Komutun geri alınması (isteğe bağlı)
}

class Light {
    public void turnOn() {
        System.out.println("Light is ON");
    }

    public void turnOff() {
        System.out.println("Light is OFF");
    }
}

 class Speaker {
    public void increaseVolume() {
        System.out.println("Volume increased");
    }

    public void decreaseVolume() {
        System.out.println("Volume decreased");
    }
}
 
class LightOnCommand implements CommandInterface {
    private Light light;  // Komutun çalıştığı alıcı

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();  // Işıkları açma işlemi
    }

    @Override
    public void undo() {
        light.turnOff();  // Işıkları kapama işlemi
    }
}

class LightOffCommand implements CommandInterface {
    private Light light;  // Komutun çalıştığı alıcı

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();  // Işıkları kapama işlemi
    }

    @Override
    public void undo() {
        light.turnOn();  // Işıkları açma işlemi
    }
}

class VolumeUpCommand implements CommandInterface {
    private Speaker speaker;  // Komutun çalıştığı alıcı

    public VolumeUpCommand(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public void execute() {
        speaker.increaseVolume();  // Ses açma işlemi
    }

    @Override
    public void undo() {
        speaker.decreaseVolume();  // Ses kısma işlemi
    }
}

class RemoteControl {
    private CommandInterface command;

    public void setCommand(CommandInterface command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();  // Komutu uygula
    }

    public void pressUndoButton() {
        command.undo();  // Komutun geri alınması
    }
}
public class Command {
	public static void main(String[] args) {
        // Alıcılar oluşturuluyor
        Light light = new Light();
        Speaker speaker = new Speaker();

        // Komutlar oluşturuluyor
        CommandInterface lightOn = new LightOnCommand(light);
        CommandInterface lightOff = new LightOffCommand(light);
        CommandInterface volumeUp = new VolumeUpCommand(speaker);

        // Uzaktan kumanda oluşturuluyor
        RemoteControl remote = new RemoteControl();

        // Komutları kumandaya atıyoruz
        remote.setCommand(lightOn);
        remote.pressButton();  // Işığı aç

        remote.setCommand(volumeUp);
        remote.pressButton();  // Sesi aç

        remote.setCommand(lightOff);
        remote.pressButton();  // Işığı kapa

        remote.pressUndoButton();  // Son komut geri alındı: ışıkları aç
    }
}
