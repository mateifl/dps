package transactions.strategy2;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/* TODO Is this an implementation of the command or the template method pattern? */

public interface ParameterMapper<T> {
	void mapParameters(PreparedStatement ps, T bean) throws SQLException;
}

