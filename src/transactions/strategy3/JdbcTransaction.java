package transactions.strategy3;

import java.sql.Connection;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;

/** Strategy implementation */
public class JdbcTransaction implements Transaction {

    private Connection connection;

    @Override
    public void start() throws SQLException {
    	System.out.println("creating connection");
        connection = ConnectionBuilder.getConnection();
        connection.setAutoCommit(false);
        System.out.println("connection created");
    }

    @Override
    /** This method implements the Template design pattern */
    public void execute(DatabaseWork worker) throws SQLException {
    	worker.doInTransaction(connection);
    }
    
    @Override
    public void commit() throws SQLException {
    	System.out.println("JdbcTransaction.commit()");
    	if(connection != null) {
    		connection.commit();
    		connection.close();
    	}
    }

    @Override
    public void rollback() throws SQLException {
    	System.out.println("JdbcTransaction.rollback()");
    	if(connection != null) {
    		connection.rollback();
    		connection.close();
    	}   		
    }
    
}