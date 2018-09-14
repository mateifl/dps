package transactions.strategy3;

import dbaccess.beans.Customer;
import dbaccess.beans.Order;
import org.junit.Test;
import transactions.strategy3.mappers.CustomerUpdateParameterMapper;
import transactions.strategy3.mappers.OrderInsertParameterMapper;

import java.sql.Date;
import java.util.Calendar;

public class TestStrategy3 {

    @Test
    public void test() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setAddress("test strategy 3 transaction");

        Order order = new Order();
        order.setCustomerId(1);
        order.setId(1);
        order.setProductId(1);
        order.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));

        DatabaseWork updateCustomerAddress = new PreparedStatementWork<Customer>(customer,
                new CustomerUpdateParameterMapper(),
                SQLStatements.CUSTOMER_UPDATE,
                null);

        DatabaseWork insertOrderAndUpdateCustomer = new PreparedStatementWork<Order>(order,
                new OrderInsertParameterMapper(),
                SQLStatements.INSERT_ORDER,
                updateCustomerAddress);

        Transaction transaction = new JdbcTransaction();
        TransactionExecution transactionExecution = new TransactionExecution(transaction);
        transactionExecution.execute(insertOrderAndUpdateCustomer);
    }

}
