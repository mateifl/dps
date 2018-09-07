package transactions.strategy3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dbaccess.ConnectionBuilder;


public interface Transaction {

    /** Create the transaction */
    void start() throws SQLException;
    
    /** Executes the statements on the database */
    void  execute(DatabaseWork worker) throws SQLException;

    /** Commit the transaction */
    void commit() throws SQLException;

    /** Roll back the transaction */
    void rollback() throws SQLException;

}

///** Strategy implementation */
//class JdbcTransaction2<T> implements Transaction {
//
//    private Connection connection;
//    private PreparedStatement preparedStatement;
//    // Command implementation
//    private List<ParameterMapper> parameterMapper;
//    private T bean;
//    private String sqlStatement;
//    private Transaction transaction;
//    
//    public JdbcTransaction2(List<ParameterMapper> parameterMapper, String sqlStatement, Transaction transaction) {
//		this.parameterMapper = parameterMapper;
//		this.sqlStatement = sqlStatement;
//		this.transaction = transaction;
//	}
//    
//    public void setBeans(List beans) {
//    	this.bean = bean;
//    }
//    
//    @Override
//    public void start() throws SQLException {
//    	System.out.println("creating connection");
//        connection = ConnectionBuilder.getConnection();
//        connection.setAutoCommit(false);
//        System.out.println("connection created");
//    }
//
//    @Override
//    /** This method implements the Template design pattern */
//    public void execute() throws SQLException {
//    	System.out.println("execute database operations");
//    	if(transaction != null)
//    		transaction.execute();
//    	
//    	preparedStatement = connection.prepareStatement(sqlStatement);
////    	parameterMapper.mapParameters(preparedStatement, bean);
//    	preparedStatement.execute();
//    }
//    
//    @Override
//    public void commit() throws SQLException {
//    	System.out.println("JdbcTransaction.commit()");
//    	if(preparedStatement != null)
//    		preparedStatement.close();
//    	if(connection != null) {
//    		connection.commit();
//    		connection.close();
//    	}
//    }
//
//    @Override
//    public void rollback() throws SQLException {
//    	System.out.println("JdbcTransaction.rollback()");
//    	if(preparedStatement != null)
//    		preparedStatement.close();
//    	if(connection != null) {
//    		connection.rollback();
//    		connection.close();
//    	}   		
//    }
//    
//}

