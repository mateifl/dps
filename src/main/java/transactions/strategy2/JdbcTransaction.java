package transactions.strategy2;

import dbaccess.ConnectionBuilder;
import transactions.strategy.CategoryInsert;
import utils.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the transaction interface for JDBC.
 * It uses the JDBC connection as a transaction manager.
 */
// No need to make this class abstract anymore. The specific code
// will be separated in the parameter classes. This is a step in the good
// direction, as it removes the need for inheritance.
public class JdbcTransaction implements Transaction<Connection> {
	private static Logger logger = LoggerFactory.getLogger(JdbcTransaction.class);
    protected Connection connection;

    public void start() throws DatabaseException {

        try {
            connection = ConnectionBuilder.getConnection();
            if (connection == null)
                throw new DatabaseException("Null connection!");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            closeConnection();
            logger.error("", e);
            throw new DatabaseException("", e);
        }
    }


    /**
     * This method implements the Template Method design pattern.
     */
    public void execute(DatabaseWork<Connection> databaseWork) throws DatabaseException {
        databaseWork.doInTransaction(connection);
    }

    public void commit() throws DatabaseException {
        try {
            if (connection != null && !connection.isClosed())
                connection.commit();
        } catch (SQLException e) {
        	logger.error("", e);
            throw new DatabaseException("", e);
        }
        finally {
            closeConnection();
        }
    }

    public void rollback() throws DatabaseException {

        try {
            if (connection != null && !connection.isClosed())
                connection.rollback();
        } catch (SQLException e) {
        	logger.error("", e);
            throw new DatabaseException("", e);
        }
        finally {
            closeConnection();
        }
    }

    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            logger.error("", e);
        }
    }

}