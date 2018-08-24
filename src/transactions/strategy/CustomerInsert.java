package transactions.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Customer;

public class CustomerInsert extends AbstractJdbcTransaction {

    private static String sql = "insert into customers(name, address, phone) values(?, ?, ?)";
    private Customer customer;
    private PreparedStatement preparedStatement;

    public CustomerInsert(Customer c) {
    	customer = c;
    }
    
    public void execute() throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getAddress());
        preparedStatement.setString(3, customer.getPhone());
        preparedStatement.execute();
        preparedStatement.close();
    }
    
}
