package transactions.strategy3;

import java.sql.Connection;
import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbaccess.SQLStatements;
import dbaccess.beans.Customer;
import dbaccess.beans.Order;
import transactions.strategy3.mappers.CustomerUpdateParameterMapper;
import transactions.strategy3.mappers.OrderInsertParameterMapper;

public class TestStrategy3 {

    private static Logger logger = LoggerFactory.getLogger(TestStrategy3.class);

    @Test
    public void test() {
        logger.info("Test strategy 3: chained transactions");
        Customer customer = new Customer();
        customer.setId(1);
        customer.setAddress("test strategy 3 transaction");

        Order order = new Order();
        order.setCustomerId(1);
        order.setId(1);
        order.setProductId(1);
        order.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));

        DatabaseWork<Connection> updateCustomerAddress = new PreparedStatementWork<Customer>(customer,
                new CustomerUpdateParameterMapper(),
                SQLStatements.CUSTOMER_UPDATE,
                null);

        DatabaseWork<Connection> insertOrderAndUpdateCustomer = new PreparedStatementWork<Order>(order,
                new OrderInsertParameterMapper(),
                SQLStatements.INSERT_ORDER,
                updateCustomerAddress);

        Transaction transaction = new JdbcTransaction();
        TransactionExecution transactionExecution = new TransactionExecution(transaction);
        transactionExecution.execute(insertOrderAndUpdateCustomer);
    }

}
