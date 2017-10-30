package transactions.strategy3.mappers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Customer;
import transactions.strategy3.ParameterMapper;

public class CustomerUpdateParameterMapper implements ParameterMapper<Customer>{


	@Override
	public void mapParameters(PreparedStatement ps, Customer bean) throws SQLException {
		ps.setString(1, bean.getAddress());
		ps.setInt(2, bean.getId());
	}
}
