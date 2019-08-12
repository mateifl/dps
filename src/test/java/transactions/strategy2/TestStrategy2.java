package transactions.strategy2;

import java.sql.Connection;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbaccess.beans.Customer;


public class TestStrategy2 {

    private static Logger logger = LoggerFactory.getLogger(TestStrategy2.class);
	
    @Test
    public void testInsertCustomer() {
        logger.info("Test strategy 2: using mappers");
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("My name is Test Customer 3");
        customer.setAddress("test strategy 3 transaction");
        
        TransactionExecution<Connection> transactionExecution = new TransactionExecution<Connection>(new JdbcTransaction());
        transactionExecution.execute(new InsertCustomer(customer));
    }

    @Test
    public void testInsertOrderUpdateCustomer() {
        logger.info("Test strategy 2: using mappers");
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("My name is Test Customer 3");
        customer.setAddress("test strategy 3 transaction");
        
        TransactionExecution<Connection> transactionExecution = new TransactionExecution<Connection>(new JdbcTransaction());
        transactionExecution.execute(new InsertCustomer(customer));
    }
    
}
