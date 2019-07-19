package transactions.strategy;

import dbaccess.beans.Category;
import dbaccess.beans.Customer;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseBuilder;

public class TestSimpleStrategy {

    private static Logger logger = LoggerFactory.getLogger(TestSimpleStrategy.class);

    @BeforeClass
    public static void beforeTests() {
        DatabaseBuilder.createDatabase();
    }

    @Test
    public void test() {
        logger.info("Test simple strategy: insert customer and category");
        TransactionExecution transactionExecution = new TransactionExecution();

        Customer customer = new Customer();
        customer.setName("Test insert");
        customer.setAddress("Test insert address");
        customer.setPhone("+102323323");
        Transaction transaction = new CustomerInsert(customer);
        transactionExecution.execute(transaction);

        Category category = new Category();
        category.setName("Test category name");
        transaction = new CategoryInsert(category);
        transactionExecution.execute(transaction);

    }
}
