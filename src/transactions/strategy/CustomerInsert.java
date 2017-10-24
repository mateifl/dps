package transactions.strategy;

import java.sql.SQLException;

import dbaccess.beans.Customer;

public class CustomerInsert extends AbstractJdbcTransaction {

    private static String sql = "insert into customers values(?, ?, ?)";
    private Customer customer;

    public CustomerInsert(Customer c) {
    	customer = c;
    }
    
    public void execute() throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, customer.getId());
        preparedStatement.setString(2, customer.getName());
        preparedStatement.setString(3, customer.getName());
        preparedStatement.execute();
    }
    
}
