import java.util.Scanner;

public class Customer {
    private String name;
    private String socialSecurityNumber;
    private String lastPay;


    public Customer(){

    }

    public Customer(String name, String socialSecurityNumber, String lastPay){
        this.name = name;
        this.socialSecurityNumber = socialSecurityNumber;
        this.lastPay = lastPay;
    }

    public String getName() {
        return name;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public String getLastPay() {
        return lastPay;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", socialSecurityNumber='" + socialSecurityNumber + '\'' +
                ", lastPay='" + lastPay + '\'' +
                '}';
    }
}
