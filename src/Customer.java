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

    public void setName(String name) {
        this.name = name;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public void setLastPay(String lastPay) {
        this.lastPay = lastPay;
    }
}
