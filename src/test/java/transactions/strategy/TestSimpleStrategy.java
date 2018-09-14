package transactions.strategy;

import dbaccess.beans.Category;
import dbaccess.beans.Customer;
import org.junit.BeforeClass;
import org.junit.Test;
import utils.DatabaseBuilder;

public class TestSimpleStrategy {

    @BeforeClass
    public static void beforeTests() {
        DatabaseBuilder.createDatabase();
    }

    @Test
    public void test() {
        TransactionExecution transactionExecution = new TransactionExecution();

        Customer customer = new Customer();
        customer.setName("Test insert");
        customer.setAddress("Test insert address");
        customer.setPhone("+102323323");
        customer.setId(6655);
        Transaction transaction = new CustomerInsert(customer);
        transactionExecution.execute(transaction);

        Category category = new Category();
        category.setId(343);
        category.setName("Test category name");
        transaction = new CategoryInsert(category);
        transactionExecution.execute(transaction);

    }
}
