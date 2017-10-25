package transactions.strategy2;

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

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected abstract ParameterMapper<T> getParameterMapper();
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
    	preparedStatement = connection.prepareStatement(getSQLStatement());
    	getParameterMapper().mapParameters(preparedStatement, bean);
    	preparedStatement.execute();
    }
    
    @Override
    public void commit() throws SQLException {
    	if(preparedStatement != null)
    		preparedStatement.close();
        connection.commit();
        connection.close();
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


/** Strategy implementation */
class JdbcTransaction<T> implements Transaction {

    private Connection connection;
    private PreparedStatement preparedStatement;
    // Command implementation
    private ParameterMapper<T> parameterMapper;
    private T bean;
    private String sqlStatement;
    
    public JdbcTransaction(ParameterMapper<T> parameterMapper, String sqlStatement) {
		this.parameterMapper = parameterMapper;
		this.sqlStatement = sqlStatement;
	}
    
    public void setBean(T bean) {
    	this.bean = bean;
    }
    
    @Override
    public void start() throws SQLException {
        connection = ConnectionBuilder.getConnection();
        connection.setAutoCommit(false);
    }

    @Override
    /** This method implements the Template design pattern */
    public void execute() throws SQLException {
    	preparedStatement = connection.prepareStatement(sqlStatement);
    	parameterMapper.mapParameters(preparedStatement, bean);
    	preparedStatement.execute();
    }
    
    @Override
    public void commit() throws SQLException {
    	if(preparedStatement != null)
    		preparedStatement.close();
        connection.commit();
        connection.close();
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
