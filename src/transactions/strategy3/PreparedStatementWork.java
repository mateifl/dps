package transactions.strategy3;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PreparedStatementWork<T> implements DatabaseWork<Connection> {

	private DatabaseWork<Connection> worker;
	private ParameterMapper<T> mapper;
	private T bean;
	private String sql;
	
	public PreparedStatementWork(T bean, ParameterMapper<T> mapper, String sql, DatabaseWork<Connection> worker) {
		this.bean = bean;
		this.mapper = mapper;
		this.sql = sql;
		this.worker = worker;
	}
	
	@Override
	public void doInTransaction(Connection connection) {
		try {
			if(worker != null)
				worker.doInTransaction(connection);
			PreparedStatement ps = connection.prepareStatement(sql);
			mapper.mapParameters(ps, bean);
			ps.execute();
			ps.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}