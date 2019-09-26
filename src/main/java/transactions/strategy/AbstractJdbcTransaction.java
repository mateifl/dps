package transactions.strategy;

import dbaccess.ConnectionBuilder;
import utils.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * AbstractJdbcTransaction implements the common steps (start, commit and rollback).
 * The specific step, execute, will be implemented in the specific classes.
 * This implements a design principle: <b>separate the common code from the specific code</b>.
 */

public abstract class AbstractJdbcTransaction implements Transaction {

    protected Connection connection;

    /** Creates the connection and sets up the transaction by setting autocommit to false.*/
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

    /** Commits the transaction and closes the connection.*/
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

    /** Rolls back the transaction and closes the connection.*/
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