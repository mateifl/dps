package transactions.basic;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbaccess.beans.Category;
import dbaccess.beans.Customer;
import utils.DatabaseBuilder;

public class BasicTransactionTest {

	private static Logger logger = LoggerFactory.getLogger(BasicTransactionTest.class);
	
    @BeforeClass
    public static void beforeTests() {
        DatabaseBuilder.createDatabase();
    }

    @Test
    public void testInsertCustomer() {
        logger.info("test basic transaction insert customer");
        Customer customer = new Customer();
        customer.setId(100);
        customer.setPhone("11380");
        customer.setCity("Test City");
        customer.setName("Test Name");
        customer.setAddress("Test first transaction");
        new CustomerInsert(customer).doTransaction();
    }

    @Test
    public void testInsertCategory() {
        logger.info("test basic transaction insert category");
        Category category = new Category();
        new CategoryInsert(category).doTransaction();
    }
}
