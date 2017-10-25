package transactions.strategy3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;

public interface Transaction {

    /** Create the transaction
     *
     * @throws SQLException
     */
    void start() throws SQLException;
    
    /** Executes the statements on the database */
    void execute() throws SQLException;

    /** Commit the transaction */
    void commit() throws SQLException;

    /** Roll back the transaction */
    void rollback() throws SQLException;

}

abstract class AbstractJdbcTransaction<T> implements Transaction {
	
	private Transaction transaction;
	
    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected T bean;
    protected abstract String getSQLStatement();

    
    
    @Override
    public void start() throws SQLException {
        connection = ConnectionBuilder.getConnection();
        connection.setAutoCommit(false);
    }

    @Override
    /** This method implements the Template design pattern */
    public void execute() throws SQLException {
    	if(transaction != null)
    		transaction.execute();
    	preparedStatement = connection.prepareStatement(getSQLStatement());
    	preparedStatement.execute();
    }
    
    @Override
    public void commit() throws SQLException {
    	if(preparedStatement != null)
    		preparedStatement.close();
    	if(connection != null) {
    		connection.commit();
    		connection.close();
    	}
    }

    @Override
    public void rollback() throws SQLException {
    	if(preparedStatement != null)
    		preparedStatement.close();
    	if(connection != null) {
    		connection.rollback();
    		connection.close();
    	}
    }
    
}