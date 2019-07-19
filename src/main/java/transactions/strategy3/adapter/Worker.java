package transactions.strategy3.adapter;

import java.sql.Connection;

import org.hibernate.Session;

import transactions.strategy3.DatabaseWork;

/**
 * TODO
 * What is the role of this interface and its implementations?
 * @author mflorescu
 *
 */

public interface Worker {
	void execute();
}

class JdbcWorker implements Worker {
	
	private Connection connection;
	private DatabaseWork<Connection> worker;
	
	public JdbcWorker(Connection connection, DatabaseWork<Connection> worker) {
		this.connection = connection;
		this.worker = worker;
	}
	
	public void execute() {
		worker.doInTransaction(connection);
	}
}

class HibernateWorker implements Worker {
	
	private Session session;
	private DatabaseWork<Session> worker;
	
	public HibernateWorker(Session session, DatabaseWork<Session> worker) {
		this.session = session;
		this.worker = worker;
	}
	
	public void execute() {
		worker.doInTransaction(session);
	}
}