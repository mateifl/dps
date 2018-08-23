package transactions.strategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;

/** Basic contract for a transaction */

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



abstract class AbstractJdbcTransaction implements Transaction {

    protected Connection connection;
    // TODO do i need this field here? it makes the transaction dependent on PreparedStatemts
    // I can move it in implementation classes and I can also use Statements, maybe.
    protected PreparedStatement preparedStatement;
    
    @Override
    public void start() throws SQLException {
        connection = ConnectionBuilder.getConnection();
        connection.setAutoCommit(false);
    }

    @Override
    public void commit() throws SQLException {
        connection.commit();
        preparedStatement.close();
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