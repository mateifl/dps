package transactions.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseException;

/**
 * Implementation of insert customer transaction.
 *
 */

public class CustomerInsert extends AbstractJdbcTransaction {

	private static Logger logger = LoggerFactory.getLogger(CustomerInsert.class);
    private static String sql = "insert into customers values(?, ?, ?, ?)";
    private Customer customer;

    public CustomerInsert(Customer c) {
    	customer = c;
    }
    
    public void execute() throws DatabaseException {
		logger.info("Execute insert customer");
    	PreparedStatement preparedStatement = null;
        try {
        	// start common code
			preparedStatement = connection.prepareStatement(sql);
			// end common code
			preparedStatement.setInt(1, customer.getId());
			preparedStatement.setString(2, customer.getName());
			preparedStatement.setString(3, customer.getAddress());
			preparedStatement.setString(4, customer.getPhone());
			// start common code
			preparedStatement.execute();
			preparedStatement.close();
			// end common code
		} catch (SQLException e) {
			logger.error("", e);
			throw new DatabaseException("", e);
		}
    }
}
