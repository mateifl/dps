package transactions.strategy3.mappers;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Order;
import transactions.strategy3.ParameterMapper;

public class OrderInsertParameterMapper implements ParameterMapper<Order> {

	public void mapParameters(PreparedStatement ps, Order bean) throws SQLException {
		ps.setDate(1, bean.getOrderDate());
		ps.setInt(2, bean.getCustomerId());
		ps.setInt(3, bean.getProductId());
	}

}
