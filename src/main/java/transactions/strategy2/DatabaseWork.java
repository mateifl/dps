package transactions.strategy2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dbaccess.SQLStatements;
import dbaccess.beans.Customer;
import dbaccess.beans.Order;
import utils.DatabaseException;

/** General contract for a database unit of work that has to be executed in a transaction. */
public interface DatabaseWork<T> {
    void doInTransaction(T object) throws DatabaseException;
}


abstract class AbstractDatabaseWork {
	
	protected <T> void executePreparedStatement(Connection connection, String sql, ParameterMapper<T> mapper, T bean) 
			throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		mapper.mapParameters(ps, bean);
		ps.execute();
		ps.close();
	}

	// TODO do I need this method? Probably not in the first. 
	protected boolean executeStatement(Connection connection, String sql)
            throws SQLException {
        Statement st = connection.createStatement();
        boolean result = st.execute(sql);
        st.close();
        return result;
    }
}

/**
 * Business role: inserts a customer's data in the database.
 */

class InsertCustomer extends AbstractDatabaseWork implements DatabaseWork<Connection> {

	private ParameterMapper<Customer> customerMapper = new CustomerInsertParameterMapper();
	private Customer customer;
	
	public InsertCustomer(Customer customer) {
		this.customer = customer;
	}
	
    public void doInTransaction(Connection connection) throws DatabaseException {
        try {
        	executePreparedStatement(connection, SQLStatements.INSERT_CUSTOMER, customerMapper, customer);
        }
        catch(SQLException e) {
            new DatabaseException("Error inserting customer!", e);
        }
    }
}

/**
 * Business role: this class is intended to be used when the customer has changed his data
 * and the update must be done with the same transaction as the order.
 */
class InsertOrderWithCustomerUpdate extends AbstractDatabaseWork implements DatabaseWork<Connection> {

	private ParameterMapper<Customer> customerMapper = new CustomerInsertParameterMapper();
	private Customer customer;
	private ParameterMapper<Order> orderMapper;
	private Order order;
	
	public InsertOrderWithCustomerUpdate(Customer customer, Order order) {
		this.customer = customer;
		this.order = order;
	}
	
	public void doInTransaction(Connection connection) {
        try {
        	executePreparedStatement(connection, SQLStatements.CUSTOMER_UPDATE, customerMapper, customer);
        	executePreparedStatement(connection, SQLStatements.INSERT_ORDER, orderMapper, order);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
	} 
}