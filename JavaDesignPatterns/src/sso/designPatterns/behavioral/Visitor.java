package sso.designPatterns.behavioral;

import java.util.ArrayList;
import java.util.List;

interface CustomerVisitor {
    void visit(IndividualCustomer individualCustomer);
    void visit(CorporateCustomer corporateCustomer);
}

interface Customer {
    void accept(CustomerVisitor visitor);
}


class IndividualCustomer implements Customer {
    private String name;
    private int age;
    private boolean hasAccidents;

    public IndividualCustomer(String name, int age, boolean hasAccidents) {
        this.name = name;
        this.age = age;
        this.hasAccidents = hasAccidents;
    }

    public int getAge() {
        return age;
    }

    public boolean hasAccidents() {
        return hasAccidents;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(CustomerVisitor visitor) {
        visitor.visit(this); // Kendini ziyaretçiye gönderir.
    }
}

class CorporateCustomer implements Customer {
    private String companyName;
    private int employeeCount;
    private int fleetSize;

    public CorporateCustomer(String companyName, int employeeCount, int fleetSize) {
        this.companyName = companyName;
        this.employeeCount = employeeCount;
        this.fleetSize = fleetSize;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public int getFleetSize() {
        return fleetSize;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public void accept(CustomerVisitor visitor) {
        visitor.visit(this); // Kendini ziyaretçiye gönderir.
    }
}

class InsurancePremiumCalculator implements CustomerVisitor {
    @Override
    public void visit(IndividualCustomer individualCustomer) {
        double premium = 500;
        if (individualCustomer.getAge() < 25) {
            premium += 200; // Genç sürücüler için ekstra ücret
        }
        if (individualCustomer.hasAccidents()) {
            premium += 300; // Kazalı geçmiş için ekstra ücret
        }
        System.out.println("Bireysel Müşteri: " + individualCustomer.getName() +
                           " için prim: " + premium + " TL");
    }

    @Override
    public void visit(CorporateCustomer corporateCustomer) {
        double premium = 1000;
        premium += corporateCustomer.getEmployeeCount() * 50; // Çalışan başına ücret
        premium += corporateCustomer.getFleetSize() * 100; // Araç başına ücret
        System.out.println("Kurumsal Müşteri: " + corporateCustomer.getCompanyName() +
                           " için prim: " + premium + " TL");
    }
}

public class Visitor {
	 public static void main(String[] args) {
	        // Müşteri listesi
	        List<Customer> customers = new ArrayList<>();
	        customers.add(new IndividualCustomer("Ahmet", 23, true));
	        customers.add(new IndividualCustomer("Ayşe", 35, false));
	        customers.add(new CorporateCustomer("XYZ Ltd.", 50, 20));
	        customers.add(new CorporateCustomer("ABC A.Ş.", 200, 100));

	        // Ziyaretçi: Prim hesaplama
	        CustomerVisitor premiumCalculator = new InsurancePremiumCalculator();

	        System.out.println("Sigorta Prim Hesaplamaları:");
	        for (Customer customer : customers) {
	            customer.accept(premiumCalculator);
	        }
	    }
}
