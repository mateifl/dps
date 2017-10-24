package transactions.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;
import dbaccess.beans.Customer;

public class CustomerInsert {
		
	private static String sql = "insert into customer values(?, ?, ?)";
	
	// TODO make this method throw and exception when connection is null, or when a database exception happens
	public void execute(Customer customer) {
        Connection connection = ConnectionBuilder.getConnection();
        try {
			connection.setAutoCommit(false);
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(1, customer.getName());
			
			preparedStatement.execute();
			preparedStatement.close();
			connection.commit();
		} catch (SQLException e) {
			try {
				if(connection != null)
					connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
        
	}
}
