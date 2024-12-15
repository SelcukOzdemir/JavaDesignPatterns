package sso.designPatterns.behavioral;


//Strategy Interface
interface MathOperation {
 int calculate(int num1, int num2);
}


//Concrete Strategy: Toplama
class Addition implements MathOperation {
 @Override
 public int calculate(int num1, int num2) {
     return num1 + num2;
 }
}

//Concrete Strategy: Çıkarma
class Subtraction implements MathOperation {
 @Override
 public int calculate(int num1, int num2) {
     return num1 - num2;
 }
}

//Concrete Strategy: Çarpma
class Multiplication implements MathOperation {
 @Override
 public int calculate(int num1, int num2) {
     return num1 * num2;
 }
}

//Concrete Strategy: Bölme
class Division implements MathOperation {
 @Override
 public int calculate(int num1, int num2) {
     if (num2 == 0) {
         throw new ArithmeticException("Bölme işlemi için ikinci sayı 0 olamaz.");
     }
     return num1 / num2;
 }
}

//Context Class
class Calculator {
 private MathOperation mathOperation;

 // Runtime'da işlem seç
 public void setMathOperation(MathOperation mathOperation) {
     this.mathOperation = mathOperation;
 }

 public int executeOperation(int num1, int num2) {
     if (mathOperation == null) {
         throw new IllegalStateException("Matematiksel işlem seçilmedi.");
     }
     return mathOperation.calculate(num1, num2);
 }
}
public class Strategy {
	 public static void main(String[] args) {
	        Calculator calculator = new Calculator();

	        // Toplama işlemi
	        calculator.setMathOperation(new Addition());
	        System.out.println("Toplama: " + calculator.executeOperation(10, 5)); // Çıktı: 15

	        // Çıkarma işlemi
	        calculator.setMathOperation(new Subtraction());
	        System.out.println("Çıkarma: " + calculator.executeOperation(10, 5)); // Çıktı: 5

	        // Çarpma işlemi
	        calculator.setMathOperation(new Multiplication());
	        System.out.println("Çarpma: " + calculator.executeOperation(10, 5)); // Çıktı: 50

	        // Bölme işlemi
	        calculator.setMathOperation(new Division());
	        System.out.println("Bölme: " + calculator.executeOperation(10, 5)); // Çıktı: 2
	    }
}
