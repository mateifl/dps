package transactions.strategy;

import java.sql.Connection;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;
import utils.DatabaseException;

/**
 * Basic contract for a transaction. By separating the steps is easier to
 * modify each one of them.
 * Example: to add logging when on commit/rollback takes place we can change in only one method.
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

/**
 * AbstractJdbcTransaction implements the common steps (start, commit and rollback).
 * The specific step, execute, will be implemented in the specific classes.
 * This implements a design principle: separate the common code from the specific code.
 */

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
            throw new DatabaseException("", e);
        }
        finally {
            closeConnection();
        }
    }

    public void rollback() throws DatabaseException {

        try {
            if (connection != null)
                connection.rollback();
        } catch (SQLException e) {
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
            System.out.println("error closing connection: " + e.getMessage());
        }
    }


}