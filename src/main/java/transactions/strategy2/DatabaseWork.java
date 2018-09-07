package transactions.strategy2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dbaccess.beans.Customer;
import dbaccess.beans.Order;

/** General contract for a database unit of work that has to be executed in a transaction */
public interface DatabaseWork<T> {
    void doInTransaction(T object);
}


abstract class AbstractDatabaseWork {
	
	protected <T> void executePreparedStatement(Connection connection, String sql, ParameterMapper<T> mapper, T bean) 
			throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		mapper.mapParameters(ps, bean);
		ps.execute();
		ps.close();
	}

	protected boolean executeStatement(Connection connection, String sql)
            throws SQLException {
        Statement st = connection.createStatement();
        boolean result = st.execute(sql);
        st.close();
        return result;
    }
}

class InsertCustomer extends AbstractDatabaseWork implements DatabaseWork<Connection> {

	private ParameterMapper<Customer> customerMapper;
	private Customer customer;
	
	public InsertCustomer(ParameterMapper<Customer> customerMapper, Customer customer) {
		this.customerMapper = customerMapper;
		this.customer = customer;
	}
	
    public void doInTransaction(Connection connection) {
        try {
        	executePreparedStatement(connection, SQLStatements.INSERT_CUSTOMER, customerMapper, customer);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}


/* Business role: this class is intended to be used when the customer has changed his data
* and the update must be done with the same transaction as the order. */
class InsertOrderWithCustomerUpdate extends AbstractDatabaseWork implements DatabaseWork<Connection> {

	private ParameterMapper<Customer> customerMapper;
	private Customer customer;
	private ParameterMapper<Order> orderMapper;
	private Order order;
	
	public InsertOrderWithCustomerUpdate(ParameterMapper<Customer> customerMapper, Customer customer,
										 ParameterMapper<Order> orderMapper, Order order) {
		this.customerMapper = customerMapper;
		this.customer = customer;
		this.orderMapper = orderMapper;
		this.order = order;
	}
	
	public void doInTransaction(Connection connection) {
        try {
            // TODO should the SQLs be part of the simple transaction classes ?
        	executePreparedStatement(connection, SQLStatements.CUSTOMER_UPDATE, customerMapper, customer);
        	executePreparedStatement(connection, SQLStatements.INSERT_ORDER, orderMapper, order);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
	} 
}