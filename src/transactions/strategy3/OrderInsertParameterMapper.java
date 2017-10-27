package transactions.strategy3;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Order;

public class OrderInsertParameterMapper implements ParameterMapper<Order> {

	@Override
	public void mapParameters(PreparedStatement ps, Order bean) throws SQLException {
		ps.setInt(1, bean.getId());
		ps.setDate(2, bean.getOrderDate());
		ps.setInt(3, bean.getCustomerId());
		ps.setInt(4, bean.getProductId());
	}

}
