package transactions.strategy2;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Customer;

public class CustomerInsertParameterMapper implements ParameterMapper<Customer>{

	public void mapParameters(PreparedStatement preparedStatement, Customer bean) throws SQLException {
		preparedStatement.setInt(1, bean.getId());
	}
}
