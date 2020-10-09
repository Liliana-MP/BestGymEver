import java.io.File;
import java.io.FileNotFoundException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BestGymEver {

    public Scanner getFile() {
        File file = new File("src/customers.txt");
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
        return scanner;
    }

    public List<Customer> getAllCustomers (Scanner scanner){
        Customer customer = new Customer();
        List<Customer> customerList = new ArrayList<>();
        while (scanner.hasNext()) {
            customer.setSocialSecurityNumber(scanner.next().replace(",", ""));
            customer.setName(scanner.nextLine().trim());
            customer.setLastPay(scanner.nextLine().trim());
            customerList.add(customer);
        }
        return customerList;
    }
}
