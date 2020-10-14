import org.junit.jupiter.api.Test;
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


        assertEquals("Alhambra Aromes", customerListTest.get(0).getName());
        assertEquals("7603021234", customerListTest.get(0).getSocialSecurityNumber());
        assertEquals("2019-07-01", customerListTest.get(0).getLastPay());

        assertEquals("Bear Belle", customerListTest.get(1).getName());
        assertEquals("8104021234", customerListTest.get(1).getSocialSecurityNumber());
        assertEquals("2018-12-02", customerListTest.get(1).getLastPay());

    }

    @Test
    public final void checkIfCustomerIsNotNullTest(){
        customerListTest = bestGymEver.getCustomerList();
        for (Customer customer : customerListTest) {
            assertNotNull(customer);
        }
    }

    @Test
    public final void searchCustomerTest() {
        bestGymEver.test = true;
        bestGymEver.getDataFromFileAndPutInList(path);
        customerListTest = bestGymEver.getCustomerList();

        String input = "Diamanda Djedi";

        Customer customer = bestGymEver.searchCustomer(input);
        assertEquals(input, customer.getName());

    }

    @Test
    public final void searchForCustomerThatDoesntExist(){
        bestGymEver.test = true;
        bestGymEver.getDataFromFileAndPutInList(path);
        customerListTest = bestGymEver.getCustomerList();
        String input = "Liliana Pitra";
        assertNull(bestGymEver.searchCustomer(input));
    }

    @Test
    public final void didCustomerPayInTheLastYearTest(){
        bestGymEver.test = true;
        Customer customer = new Customer("", "", LocalDate.now().toString());
        boolean checkDate = bestGymEver.didCustomerPay(customer);
        assertTrue(checkDate);
    }

    @Test
    public final void didCustomerPayExactlyOneYearAgoTest(){
        bestGymEver.test = true;
        Customer customer = new Customer("", "", LocalDate.now().minusYears(1).toString());
        boolean checkDate = bestGymEver.didCustomerPay(customer);
        assertTrue(checkDate);
    }

    @Test
    public final void didCustomerPayMoreThanOneYearAgoTest(){
        bestGymEver.test = true;
        Customer customer = new Customer("", "", LocalDate.now().minusYears(3).toString());
        boolean checkDate = bestGymEver.didCustomerPay(customer);
        assertFalse(checkDate);
    }

    @Test
    public final void getInputFromUserTest(){
        bestGymEver.test = true;
        String input = "Hej";

        assertEquals(input, bestGymEver.getInputFromUser(input) );
        assertNotEquals("då", bestGymEver.getInputFromUser(input));
    }

    @Test
    public final void getNullInputFromUserTest(){
        bestGymEver.test = true;
        String input = null;

        assertNull(input);
        assertEquals(null, bestGymEver.getInputFromUser(input));
    }
}

