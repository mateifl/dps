package transactions.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
		
	private Customer customer;

	public CustomerInsert(Customer customer) {
		this.customer = customer;
	}

	public void doTransaction() throws DatabaseException  {
        Connection connection = ConnectionBuilder.getConnection();
        try {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement(SQLStatements.INSERT_CUSTOMER);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getPhone());
			preparedStatement.setInt(4, customer.getId());
			preparedStatement.execute();
			preparedStatement.close();
			connection.commit();
		} catch (SQLException e) {
			try {
				if (connection != null) {
					connection.rollback();
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DatabaseException(e.getMessage(), e);
		} finally {
        	try {
				if (connection != null)
					connection.close();
			}
			catch(SQLException e) {
                System.out.println("ERROR closing connection");
                System.out.println(e.getMessage());
			}
		}
	}
}
