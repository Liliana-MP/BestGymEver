import javax.swing.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// TODO Skapa testmetoder för att kolla ifall kund betalat under ett år
// TODO Fixa testmetoder

public class BestGymEver {

    private List<Customer> customerList = new ArrayList<>();

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void getDataFromFileAndPutInList (String fileName){
        File file = new File("src/" + fileName);
        Scanner scanner = null;
        try {
            scanner  = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            System.out.println("Hittar ingen fil");
        }
        catch (Exception e){
            System.out.println("Error");
        }

        while (scanner.hasNextLine()) {
            String socialSecurityNumber = scanner.next().replace(",", "");
            String name = scanner.nextLine().trim();
            String lastPay = scanner.nextLine().trim();
            customerList.add(new Customer(name, socialSecurityNumber, lastPay));
        }

    }

    public String getInputFromUser(){
        String input = JOptionPane.showInputDialog("Skriv kundens personnummer eller namn");
        if (input == null){
            JOptionPane.showMessageDialog(null, "Programmet avslutas");
            System.exit(0);
        }

        input = input.trim();
        return input;
    }

    public Customer searchCustomer (String input){

        for (Customer customer : customerList) {

            if (input.equals(customer.getSocialSecurityNumber()) || input.equalsIgnoreCase(customer.getName())) {
                // Parsar datum
                LocalDate customersLastPayment = LocalDate.parse(customer.getLastPay());
                if (dateOneYearAgo().isBefore(customersLastPayment)){
                    JOptionPane.showMessageDialog(null, customer.getName() + " är kund");
                    return customer;
                }
                else if (dateOneYearAgo().isAfter(customersLastPayment)){
                    JOptionPane.showMessageDialog(null, customer.getName() +
                            " har inte betalat på mer än 1 år");
                }
            }

        }
        return null;
    }

    public void saveGymVisit(Customer customer) {
        //Try with resources
        //Skapar ny fil för varje kund
        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new FileWriter("CustomerLog/" + customer.getSocialSecurityNumber() + ".txt", true))) {

            //Skriver till fil
           bufferedWriter.write("Customer: " + customer.getName() + " " + customer.getSocialSecurityNumber() +  "\n"
                   + LocalDate.now() + "\n" + "\n");
        }
        catch (FileAlreadyExistsException e){
            System.out.println("Filen finns redan");
        }
        catch (Exception e){
            System.out.println("Error");
        }

    }

    public LocalDate dateOneYearAgo(){
        LocalDate dateNow = LocalDate.now();
        return dateNow.minusYears(1);
    }

    public void boot() {
        String customerFilePath = "/customers.txt";
        String input = getInputFromUser();

        getDataFromFileAndPutInList(customerFilePath);
        Customer customer = searchCustomer(input);
        if (customer != null){
            saveGymVisit(customer);
        }
        else {
            ImageIcon imageIcon = new ImageIcon(BestGymEver.class.getResource("/giphy.gif"));
            JOptionPane.showMessageDialog(null, "Finns inte i registret", "Ghost",
                    JOptionPane.INFORMATION_MESSAGE, imageIcon);
        }
    }
}
