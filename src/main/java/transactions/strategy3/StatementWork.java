package transactions.strategy3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import utils.DatabaseException;

public class StatementWork implements DatabaseWork<Connection> {

	private String sql;
	
	public StatementWork(String sql) {
		this.sql = sql;
	}

	public void doInTransaction(Connection c) {
		Statement statement = null;
		try {
			statement = c.createStatement();
			statement.execute(sql);
		}
		catch(SQLException e) {
			throw new DatabaseException("", e);
		}
		finally {
			try {
				if(statement != null)
					statement.close();
			}
			catch(SQLException e) {
				throw new DatabaseException("", e);
			}
		}
	}
}
