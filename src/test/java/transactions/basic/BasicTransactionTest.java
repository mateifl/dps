package transactions.basic;

import dbaccess.beans.Customer;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.DatabaseBuilder;

public class BasicTransactionTest {

    @BeforeClass
    public static void beforeTests() {
        DatabaseBuilder.createDatabase();
    }

    @Test
    public void testInsertCustomer() {
        System.out.println("test basic transaction insert customer");
        Customer customer = new Customer();
        customer.setId(100);
        customer.setPhone("11380");
        customer.setCity("Test City");
        customer.setName("Test Name");
        customer.setAddress("Test first transaction");
        new CustomerInsert(customer).doTransaction();
    }

}
