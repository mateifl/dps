package transactions.strategy3;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbaccess.ConnectionBuilder;
import utils.DatabaseException;

/** Strategy implementation. */
public class JdbcTransaction implements Transaction<Connection> {

    private static Logger logger = LoggerFactory.getLogger(JdbcTransaction.class);
    private Connection connection;

    /** Sets up the transaction */
    public void start() throws DatabaseException {
        try {
            logger.trace("creating connection");
            connection = ConnectionBuilder.getConnection();
            connection.setAutoCommit(false);
            logger.trace("connection created");
        }
        catch (SQLException e) {
        	logger.error("", e);
            throw new DatabaseException("", e);
        }
    }

    /** This method implements the Template Method design pattern */
    public void execute(DatabaseWork<Connection> worker) throws DatabaseException {
    	worker.doInTransaction(connection);
    }
    
    /** Commits the transaction */
    public void commit() throws DatabaseException {
        try {
        	logger.trace("commit transaction");
            if(connection != null) {
                connection.commit();
                connection.close();
            }
        }
        catch (SQLException e) {
        	logger.error("", e);
            throw new DatabaseException("", e);
        }
    }

    /** Rolls back the transaction */
    public void rollback() throws DatabaseException {
        try {
        	logger.trace("rollback transaction");
            if(connection != null) {
                connection.rollback();
                connection.close();
            }
        }
        catch (SQLException e) {
        	logger.error("", e);
            throw new DatabaseException("", e);
        }
    }
    
}