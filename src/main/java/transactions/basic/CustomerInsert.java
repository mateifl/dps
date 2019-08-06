package transactions.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbaccess.ConnectionBuilder;
import dbaccess.SQLStatements;
import dbaccess.beans.Customer;
import utils.DatabaseException;

/**
 * Implementation of the Transaction interface. 
 * On comparing with {@link transactions.basic.CategoryInsert}, 
 * we can see there is a lot of common code. 
 */

public class CustomerInsert implements Transaction {
		
	private static Logger logger = LoggerFactory.getLogger(CustomerInsert.class);
	
	private Customer customer;

	public CustomerInsert(Customer customer) {
		this.customer = customer;
	}

	public void doTransaction() throws DatabaseException  {
		logger.trace("Start update");
        Connection connection = ConnectionBuilder.getConnection();
        logger.trace("Connection created");
        try {
			connection.setAutoCommit(false);
			logger.trace("Creating prepared statement");
			PreparedStatement preparedStatement = connection.prepareStatement(SQLStatements.INSERT_CUSTOMER);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getPhone());
			preparedStatement.setInt(4, customer.getId());
			logger.trace("Executing prepared statement");
			preparedStatement.execute();
			preparedStatement.close();
			connection.commit();
			logger.trace("Transaction commited");
		} catch (SQLException e) {
			logger.error("", e);
			try {
				if (connection != null) {
					connection.rollback();
					logger.trace("Transaction rolled back");
				}
			} catch (SQLException e1) {
				logger.error("", e);
			}
			throw new DatabaseException(e.getMessage(), e);
		} finally {
        	try {
				if (connection != null)
					connection.close();
			}
			catch(SQLException e) {
				logger.error("", e);
				throw new DatabaseException(e.getMessage(), e);
			}
		}
	}
}
