package transactions.strategy2;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Customer;

public class CustomerInsertParameterMapper implements ParameterMapper<Customer>{

	@Override
	public void mapParameters(PreparedStatement ps, Customer bean) throws SQLException {
		ps.setInt(1, bean.getId());
		
	}


}
