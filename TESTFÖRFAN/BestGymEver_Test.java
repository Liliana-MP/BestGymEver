import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BestGymEver_Test {
    Customer customer = new Customer();
    BestGymEver bestGymEver = new BestGymEver();
    File file = new File("src/customers.txt");


    @Test
    public final void settersAndGetters(){
    }

    @Test
    public final void getFile() {
        assertTrue(bestGymEver.getFile() != null);
        assertFalse(bestGymEver.getFile() == null);
    }

    @Test
    public final void createCustomer() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        Customer customer = bestGymEver.createCustomer(scanner);

        assertNotNull(customer);
        assertEquals("Alhambra Aromes", customer.getName());
        assertEquals("7603021234", customer.getSocialSecurityNumber());
        assertEquals("2019-07-01", customer.getLastPay());

    }
}
