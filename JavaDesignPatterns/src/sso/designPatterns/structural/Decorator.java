package sso.designPatterns.structural;

interface Coffee {
    double cost();  // Kahvenin maliyetini döndüren metot
}

class SimpleCoffee implements Coffee {
    @Override
    public double cost() {
        return 5.00;  // Basit bir kahvenin maliyeti
    }
}
abstract class CoffeeDecorator implements Coffee {
    protected Coffee decoratedCoffee;

    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost();  // Dekoratöre eklenen işlevsellik
    }
}
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost() + 1.50;  // Süt eklemek için maliyet ekle
    }
}

 class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    @Override
    public double cost() {
        return decoratedCoffee.cost() + 0.50;  // Şeker eklemek için maliyet ekle
    }
}
public class Decorator {

	
	public static void main(String[] args) {
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("Basit Kahve Maliyeti: " + simpleCoffee.cost());

        Coffee milkCoffee = new MilkDecorator(new SimpleCoffee());
        System.out.println("Sütlü Kahve Maliyeti: " + milkCoffee.cost());

        Coffee milkSugarCoffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println("Sütlü ve Şekerli Kahve Maliyeti: " + milkSugarCoffee.cost());
    }
}
