package sso.designPatterns.behavioral;

interface HomeAutomationMediator {
    void controlDevices(String command, Appliance appliance);
}

abstract class Appliance {
    protected HomeAutomationMediator mediator;

    public Appliance(HomeAutomationMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void executeCommand(String command);
}
 
class AirConditioner extends Appliance {

    public AirConditioner(HomeAutomationMediator mediator) {
        super(mediator);
    }

    @Override
    public void executeCommand(String command) {
        if (command.equals("ON")) {
            System.out.println("Air Conditioner is now ON.");
        } else if (command.equals("OFF")) {
            System.out.println("Air Conditioner is now OFF.");
        }
    }
}

class Lights extends Appliance {

    public Lights(HomeAutomationMediator mediator) {
        super(mediator);
    }

    @Override
    public void executeCommand(String command) {
        if (command.equals("ON")) {
            System.out.println("Lights are now ON.");
        } else if (command.equals("OFF")) {
            System.out.println("Lights are now OFF.");
        }
    }
}

class SecuritySystem extends Appliance {

    public SecuritySystem(HomeAutomationMediator mediator) {
        super(mediator);
    }

    @Override
    public void executeCommand(String command) {
        if (command.equals("ARM")) {
            System.out.println("Security System is now ARMED.");
        } else if (command.equals("DISARM")) {
            System.out.println("Security System is now DISARMED.");
        }
    }
}

class HomeAutomationMediatorImpl implements HomeAutomationMediator {
    private Appliance airConditioner;
    private Appliance lights;
    private Appliance securitySystem;

    public void setAirConditioner(Appliance airConditioner) {
        this.airConditioner = airConditioner;
    }

    public void setLights(Appliance lights) {
        this.lights = lights;
    }

    public void setSecuritySystem(Appliance securitySystem) {
        this.securitySystem = securitySystem;
    }

    @Override
    public void controlDevices(String command, Appliance appliance) {
        if (appliance == airConditioner) {
            airConditioner.executeCommand(command);
        } else if (appliance == lights) {
            lights.executeCommand(command);
        } else if (appliance == securitySystem) {
            securitySystem.executeCommand(command);
        }
    }
}

public class Mediator {
	public static void main(String[] args) {
        // Mediator ve cihazlar oluşturuluyor
        HomeAutomationMediator mediator = new HomeAutomationMediatorImpl();

        Appliance airConditioner = new AirConditioner(mediator);
        Appliance lights = new Lights(mediator);
        Appliance securitySystem = new SecuritySystem(mediator);

        // Mediator'a cihazlar ekleniyor
        ((HomeAutomationMediatorImpl) mediator).setAirConditioner(airConditioner);
        ((HomeAutomationMediatorImpl) mediator).setLights(lights);
        ((HomeAutomationMediatorImpl) mediator).setSecuritySystem(securitySystem);

        // Kullanıcı komutlarını gönderiyor
        airConditioner.executeCommand("ON");
        lights.executeCommand("OFF");
        securitySystem.executeCommand("ARM");
    }
}
