import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BestGymEver_Test {
    BestGymEver bestGymEver = new BestGymEver();
    List<Customer> customerListTest = new ArrayList<>();
    Path path = Paths.get("src/customers.txt");


    @Test
    public final void getDataFromFileAndPutInListTest() {
        bestGymEver.getDataFromFileAndPutInList(path);
        customerListTest = bestGymEver.getCustomerList();

        for (Customer customer : customerListTest) {
            assertNotNull(customer);
        }

        assertEquals("Alhambra Aromes", customerListTest.get(0).getName());
        assertEquals("7603021234", customerListTest.get(0).getSocialSecurityNumber());
        assertEquals("2019-07-01", customerListTest.get(0).getLastPay());

        assertEquals("Bear Belle", customerListTest.get(1).getName());
        assertEquals("8104021234", customerListTest.get(1).getSocialSecurityNumber());
        assertEquals("2018-12-02", customerListTest.get(1).getLastPay());

    }

    @Test
    public final void searchCustomerTest() {
        bestGymEver.test = true;
        bestGymEver.getDataFromFileAndPutInList(path);
        customerListTest = bestGymEver.getCustomerList();

        String input = "Diamanda Djedi";

        Customer customer = bestGymEver.searchCustomer(input);
        assertEquals(input, customer.getName());

        input = "Liliana Pitra";
        assertNull(bestGymEver.searchCustomer(input));
    }

    @Test
    public final void didCustomerPayTest(){
        bestGymEver.test = true;
        LocalDate testDate = LocalDate.of(2020,10,9);
        Customer customer = new Customer("", "", "2019-07-01");
        LocalDate customersLastPayment = LocalDate.parse(customer.getLastPay());
        if (testDate.isBefore(customersLastPayment)) {
            assertEquals(testDate, customersLastPayment);
            JOptionPane.showMessageDialog(null, customer.getName() + " är kund");

        } else if (testDate.isAfter(customersLastPayment)) {
            assertNotEquals(testDate, customersLastPayment);
        }
    }
}

