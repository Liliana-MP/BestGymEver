import javax.swing.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BestGymEver {

    private List<Customer> customerList = new ArrayList<>();
    public boolean test = false;
    private ImageIcon imageIcon;

    public List<Customer> getCustomerList() {

        return customerList;
    }

    public void getDataFromFileAndPutInList(Path customerFilePath) {
        File file = new File(String.valueOf(customerFilePath));

        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                String socialSecurityNumber = scanner.next().replace(",", "");
                String name = scanner.nextLine().trim();
                String lastPay = scanner.nextLine().trim();
                customerList.add(new Customer(name, socialSecurityNumber, lastPay));
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Hittar ingen fil");
        }
        catch (Exception e) {
            System.out.println("Error");
        }
    }

    public String getInputFromUser(String testParameter) {
        String input = "";
        if (test) {
            input = testParameter;
        }
        else {
            input = JOptionPane.showInputDialog("Skriv kundens personnummer (tio siffror) eller namn");
            if (input == null) {
                JOptionPane.showMessageDialog(null, "Programmet avslutas");
                System.exit(0);
            }
            input = input.trim();

        }
        return input;
    }

    public Customer searchCustomer(String input) {

        for (Customer customer : customerList) {
            if (input.equals(customer.getSocialSecurityNumber()) || input.equalsIgnoreCase(customer.getName())) {
                return customer;
            }
        }
        if (!test){
            imageIcon = new ImageIcon(BestGymEver.class.getResource("/giphy.gif"));
            JOptionPane.showMessageDialog(null, "Finns inte i registret", "Error",
                    JOptionPane.INFORMATION_MESSAGE, imageIcon);
        }
        return null;
    }

    public boolean didCustomerPay(Customer customer) {

        LocalDate customersLastPayment = LocalDate.parse(customer.getLastPay());
        if (dateOneYearAgo().isBefore(customersLastPayment) || dateOneYearAgo().equals(customersLastPayment)) {
            if (!test) {
                imageIcon = new ImageIcon(BestGymEver.class.getResource("/gym.gif"));

                JOptionPane.showMessageDialog(null, customer.getName() + " är kund",
                        "Kund", JOptionPane.PLAIN_MESSAGE, imageIcon);

                saveGymVisit(customer);
                System.out.println("Sparade " + customer.getName() + "s" + " träningspass");
            }
            return true;

        } else {
            if (!test) {
                imageIcon = new ImageIcon(BestGymEver.class.getResource("/money.gif"));

                JOptionPane.showMessageDialog(null, customer.getName() +
                        " har inte betalat på mer än 1 år", "Error", JOptionPane.INFORMATION_MESSAGE, imageIcon);
            }
            return false;
        }
    }

    public boolean saveGymVisit(Customer customer) {

        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new FileWriter("CustomerLog/" + customer.getSocialSecurityNumber() + ".txt", true))) {

            bufferedWriter.write("Customer: " + customer.getName() + " " +
                    customer.getSocialSecurityNumber() + "\n" + LocalDate.now() + "\n" + "\n");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public LocalDate dateOneYearAgo() {
        LocalDate dateNow = LocalDate.now();
        return dateNow.minusYears(1);
    }

    public void boot() {
        Path customerFilePath = Paths.get("src/customers.txt");
        String input = getInputFromUser("");

        getDataFromFileAndPutInList(customerFilePath);
        Customer customer = searchCustomer(input);
        if (customer != null){
            didCustomerPay(customer);
        }
    }
}
