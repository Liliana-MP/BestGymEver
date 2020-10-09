import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BestGymEver_Test {
    Customer customer = new Customer();
    BestGymEver bestGymEver = new BestGymEver();
    File file = new File("src/customers.txt");


    @Test
    public final void settersAndGetters(){
    }

   /* @Test
    public final void getFile() {
        assertTrue(bestGymEver.getFile("/s") != null);
        assertFalse(bestGymEver.getFile() == null);
    }*/

    @Test
    public final void getAllCustomers() throws FileNotFoundException {
        Scanner scanner = new Scanner(file);
        BestGymEver bestGymEver = new BestGymEver();
        List<Customer> customerListTest = bestGymEver.getDataFromFileAndPutInList("customers.txt");

        assertNotNull(customerListTest);
        assertEquals("Alhambra Aromes", customerListTest.get(0).getName());
        assertEquals("7603021234", customerListTest.get(0).getSocialSecurityNumber());
        assertEquals("2019-07-01", customerListTest.get(0).getLastPay());

        assertEquals("Bear Belle", customerListTest.get(1).getName());
        assertEquals("8104021234", customerListTest.get(1).getSocialSecurityNumber());
        assertEquals("2018-12-02", customerListTest.get(1).getLastPay());
    }

    @Test
    public final void searchCustomer(){

    }
}
