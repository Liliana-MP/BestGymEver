import javax.swing.*;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BestGymEver {

    public List<Customer> getDataFromFileAndPutInList (String fileName){
        File file = new File("src/" + fileName);
        Scanner scanner = null;
        try {
            scanner  = new Scanner(file);
        }
        catch (FileNotFoundException f) {
            System.out.println("Hittar ingen fil");
        }
        catch (Exception e){
            System.out.println("Error");
        }

        List<Customer> customerList = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String socialSecurityNumber = scanner.next().replace(",", "");
            String name = scanner.nextLine().trim();
            String lastPay = scanner.nextLine().trim();
            customerList.add(new Customer(name, socialSecurityNumber, lastPay));
        }
        return customerList;
    }

    public Customer searchCustomer (List<Customer> customerList){

        String input = JOptionPane.showInputDialog("Skriv kundens personnummer eller namn");
        input = input.trim();

        for (Customer customer : customerList) {

            if (input.equals(customer.getSocialSecurityNumber()) || input.equalsIgnoreCase(customer.getName())) {
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

    public void saveGymVisit(Customer customer) throws IOException {

        try (BufferedWriter bufferedWriter = new BufferedWriter
                (new FileWriter("CustomerLog/" + customer.getSocialSecurityNumber() + ".txt", true))) {

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
        List<Customer> customerList = getDataFromFileAndPutInList(customerFilePath);
        Customer customer = searchCustomer(customerList);
        if (customer != null){
            try {
                saveGymVisit(customer);
            }
            catch (IOException e){
                System.out.println("Error");
            }

        }
    }
}
