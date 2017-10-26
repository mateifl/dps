package transactions.strategy3;

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
	
	protected DatabaseWork<Connection> worker;
	
	protected <T> void executePreparedStatement(Connection connection, String sql, ParameterMapper<T> mapper, T bean) 
			throws SQLException {
		PreparedStatement ps = connection.prepareStatement(sql);
		mapper.mapParameters(ps, bean);
		ps.execute();
		ps.close();
	}
}

class UpdateCustomer extends AbstractDatabaseWork implements DatabaseWork<Connection> {

	private ParameterMapper<Customer> customerMapper;
	private Customer customer;
	
	public UpdateCustomer(ParameterMapper<Customer> customerMapper, Customer customer, 
						  DatabaseWork<Connection> worker) {
		this.customerMapper = customerMapper;
		this.customer = customer;
		this.worker = worker;
	}
	
    @Override
    public void doInTransaction(Connection connection) {
        try {
        	if(this.worker != null)
        		worker.doInTransaction(connection);
        	executePreparedStatement(connection, SQLStatements.CUSTOMER_UPDATE, customerMapper, customer);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}

class InsertOrderWithCustomerUpdate extends AbstractDatabaseWork implements DatabaseWork<Connection> {

	private ParameterMapper<Order> orderMapper;
	private Order order;
	
	public InsertOrderWithCustomerUpdate(ParameterMapper<Order> orderMapper, Order order, DatabaseWork<Connection> worker) {
		this.orderMapper = orderMapper;
		this.order = order;
		this.worker = worker;
	}
	
	@Override
	public void doInTransaction(Connection connection) {
        try {
        	if(worker != null)
        		worker.doInTransaction(connection);
        	executePreparedStatement(connection, SQLStatements.INSERT_ORDER, orderMapper, order);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
		
	} 
	
}