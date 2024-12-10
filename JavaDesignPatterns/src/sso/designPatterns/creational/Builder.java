package sso.designPatterns.creational;
class Employee {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String phone;
    private final String address;

    // Private constructor, builder tarafından kullanılacak
    private Employee(EmployeeBuilder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.phone = builder.phone;
        this.address = builder.address;
    }

    // Getter metodları
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    // Builder sınıfı
    public static class EmployeeBuilder {
        private final String firstName;  // Zorunlu alan
        private final String lastName;   // Zorunlu alan
        private int age;
        private String phone;
        private String address;

        // Builder constructor'ı zorunlu alanları alacak şekilde
        public EmployeeBuilder(String firstName, String lastName) {
            if (firstName == null || firstName.isEmpty()) {
                throw new IllegalArgumentException("First name cannot be null or empty");
            }
            if (lastName == null || lastName.isEmpty()) {
                throw new IllegalArgumentException("Last name cannot be null or empty");
            }
            this.firstName = firstName;
            this.lastName = lastName;
        }

        // Opsiyonel alanlar için setter metodları
        public EmployeeBuilder setAge(int age) {
            this.age = age;
            return this;
        }

        public EmployeeBuilder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public EmployeeBuilder setAddress(String address) {
            this.address = address;
            return this;
        }

        // Employee nesnesini oluşturma
        public Employee build() {
            return new Employee(this);
        }
    }
    public class Main {
        public static void main(String[] args) {
            try {
                // İlk nesne oluşturuluyor
                Employee employee = new Employee.EmployeeBuilder("Ali", "Yılmaz")
                        .setAge(30)
                        .setPhone("123-456-7890")
                        .setAddress("Adress")
                        .build();

                // Employee nesnesi yazdırılıyor
                System.out.println(employee);

                // İkinci nesne, firstName veya lastName olmadan oluşturulamaz
                Employee invalidEmployee = new Employee.EmployeeBuilder("", "Ahmet")
                        .build();  // Bu satırda hata alacağız
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
