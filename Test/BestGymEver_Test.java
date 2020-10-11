import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class BestGymEver_Test {
    BestGymEver bestGymEver = new BestGymEver();
    List<Customer> customerListTest = new ArrayList<>();

    @Test
    public final void settersAndGetters(){
    }

   /* @Test
    public final void getFile() {
        assertTrue(bestGymEver.getFile("/s") != null);
        assertFalse(bestGymEver.getFile() == null);
    }*/

    @Test
    public final void getDataFromFileAndPutInListTest()  {
        bestGymEver.getDataFromFileAndPutInList("/customers.txt");
        customerListTest = bestGymEver.getCustomerList();

        for (Customer customer: customerListTest) {
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
    public final void searchCustomerTest(){
        String input = "Diamanda Djedi";
        bestGymEver.searchCustomer(input);

            input = "Liliana Pitra";
            assertNull(bestGymEver.searchCustomer(input));
        }

    }



   /* @Test
    public final void didCustomerPay(){
        LocalDate testDate = LocalDate.of(2020,10,9);
        Customer customer = new Customer("", "", "2019-07-01");

        assertEquals(customer.getLastPay(), bestGymEver.dateOneYearAgo());

    }*/

