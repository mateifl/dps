package transactions.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Customer;
import utils.DatabaseException;

public class CustomerInsert extends AbstractJdbcTransaction {

    private static String sql = "insert into customers(name, address, phone) values(?, ?, ?)";
    private Customer customer;
    private PreparedStatement preparedStatement;

    public CustomerInsert(Customer c) {
    	customer = c;
    }
    
    public void execute() throws DatabaseException {
        try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, customer.getName());
			preparedStatement.setString(2, customer.getAddress());
			preparedStatement.setString(3, customer.getPhone());
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new DatabaseException("", e);
		}
    }
    
}
