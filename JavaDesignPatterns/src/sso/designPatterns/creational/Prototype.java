package sso.designPatterns.creational;

interface Copyable<T> {
    T copy();
}

public class Prototype implements Copyable<Prototype>{

	private String field1;
	private String field2;

	public Prototype(String field1, String field2) {
		super();
		this.field1 = field1;
		this.field2 = field2;
	}

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	// Klonlama işlemi
	@Override
	public Prototype copy() {
		return new Prototype(field1, field2);
	}

	
	
	public class PrototypeMain {
		public static void main(String[] args) {
			Prototype prototype = new Prototype("Original value 1", "Original value 2");
			System.out.println("prototype : " + prototype.getField1() + "-" + prototype.getField2());
			
			Prototype prototype2 = prototype.copy();
			prototype2.setField1("Changed value 1");
			prototype2.setField2("Changed value 2");
			
			System.out.println("Change instance " + prototype2.getField1() +" - " + prototype2.getField2());
			System.out.println("Original instance " + prototype.getField1() +" - " + prototype.getField2());
			
		}
	}
}