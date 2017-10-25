package transactions.strategy3;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ParameterMapper<T> {
	void mapParameters(PreparedStatement ps, T bean) throws SQLException;
}
