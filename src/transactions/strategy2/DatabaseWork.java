package transactions.strategy2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}

class InsertCustomer extends AbstractDatabaseWork implements DatabaseWork<Connection> {

	private ParameterMapper<Customer> customerMapper;
	private Customer customer;
	
	public InsertCustomer(ParameterMapper<Customer> customerMapper, Customer customer) {
		this.customerMapper = customerMapper;
		this.customer = customer;
	}
	
    @Override
    public void doInTransaction(Connection connection) {
        try {
        	executePreparedStatement(connection, SQLStatements.INSERT_CUSTOMER, customerMapper, customer);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}

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
	
	@Override
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