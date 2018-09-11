package transactions.strategy;

import java.sql.Connection;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;
import utils.DatabaseException;

/**
 * Basic contract for a transaction. By separating the steps is easier to
 * modify each one of them.
 * Example: to add logging when on commit/rollback we can change in only one place,
 * where the commit/rollback is implemented.
 */

public interface Transaction {

    /**
     * Create the transaction
     */
    void start() throws DatabaseException;

    /**
     * Executes the statements on the database
     */
    void execute() throws DatabaseException;

    /**
     * Commit the transaction
     */
    void commit() throws DatabaseException;

    /**
     * Roll back the transaction
     */
    void rollback() throws DatabaseException;

}


abstract class AbstractJdbcTransaction implements Transaction {

    protected Connection connection;

    public void start() throws DatabaseException {

        try {
            connection = ConnectionBuilder.getConnection();
            if (connection == null)
                throw new DatabaseException("Null connection!");
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            closeConnection();
            throw new DatabaseException("", e);
        }
    }

    public void commit() throws DatabaseException {
        try {
            if (connection != null)
                connection.commit();

        } catch (SQLException e) {
            closeConnection();
            throw new DatabaseException("", e);
        }

    }

    public void rollback() throws DatabaseException {

        try {
            if (connection != null)
                connection.rollback();
        } catch (SQLException e) {
            closeConnection();
            throw new DatabaseException("", e);
        }

    }

    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("error closing connection: " + e.getMessage());
        }
    }


}