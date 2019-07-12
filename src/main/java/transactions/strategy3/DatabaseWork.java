package transactions.strategy3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbaccess.SQLStatements;
import dbaccess.beans.Customer;
import dbaccess.beans.Order;
import transactions.strategy3.mappers.CustomerUpdateParameterMapper;
import transactions.strategy3.mappers.OrderInsertParameterMapper;


/** General contract for a database unit of work that has to be executed in a transaction. */
public interface DatabaseWork<T> {

	/**
	 *
	 * @param transactionManager - an object used to start/commit/roll back a transaction
	 * For example, the java.sql.Connection for JDBC and javax.persistence.EntityManager for
	 * JPA.
	 */
    void doInTransaction(T transactionManager);
}

abstract class JdbcDatabaseWork {
    private static Logger logger = LoggerFactory.getLogger(JdbcDatabaseWork.class);
	protected DatabaseWork<Connection> worker;

	/**
	 * TODO
	 * @param <T>
	 * @param connection
	 * @param sql
	 * @param mapper
	 * @param bean - the object that has the data to be saved in the database
	 * @throws SQLException
	 */
	protected <T> void updateWithPreparedStatement(Connection connection, String sql, ParameterMapper<T> mapper, T bean)
			throws SQLException {
		logger.info("Update with prepared statement");
		PreparedStatement ps = connection.prepareStatement(sql);
		mapper.mapParameters(ps, bean);
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * TODO
	 * @param connection
	 * @param sql
	 * @throws SQLException
	 */
	protected void updateWithStatement(Connection connection, String sql) throws SQLException {
		logger.info("Update with prepared statement");
		Statement statement = connection.createStatement();
		statement.executeUpdate(sql);
		statement.close();
	}
}

class UpdateCustomer extends JdbcDatabaseWork implements DatabaseWork<Connection> {

	private ParameterMapper<Customer> customerMapper;
	private Customer customer;

	public UpdateCustomer(Customer customer, DatabaseWork<Connection> worker) {
		this.customerMapper = new CustomerUpdateParameterMapper();
		this.customer = customer;
		this.worker = worker;
	}

    public void doInTransaction(Connection connection) {
        try {
        	if(this.worker != null)
        		worker.doInTransaction(connection);
        	System.out.println("update customer");
        	updateWithPreparedStatement(connection, SQLStatements.CUSTOMER_UPDATE, customerMapper, customer);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

class InsertOrder extends JdbcDatabaseWork implements DatabaseWork<Connection> {

	private ParameterMapper<Order> orderMapper;
	private Order order;

	public InsertOrder(Order order, DatabaseWork<Connection> worker) {
		this.orderMapper = new OrderInsertParameterMapper();
		this.order = order;
		this.worker = worker;
	}

	public void doInTransaction(Connection connection) {
        try {
        	if(worker != null)
        		worker.doInTransaction(connection);
        	System.out.println("inserting order");
        	updateWithPreparedStatement(connection, SQLStatements.INSERT_ORDER, orderMapper, order);
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

	}
}

