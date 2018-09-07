package transactions.strategy2;

import dbaccess.ConnectionBuilder;
import utils.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of the transaction interface for JDBC.
 * It uses the JDBC connection as a transaction manager.
 */
// No need to make this class abstract anymore. The specific code
// will be separated in the parameter classes. This is a step in the good
// direction, as it removes the need for inheritance.
public class JdbcTransaction implements Transaction<Connection> {

    protected Connection connection;

    public void start() throws DatabaseException {

        try {
            connection = ConnectionBuilder.getConnection();
            connection.setAutoCommit(false);
        }
        catch (SQLException e) {
            closeConnection();
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
            if (connection != null) {
                connection.commit();
                connection.close();
            }
        }
        catch(SQLException e) {
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

    public void rollback() throws DatabaseException {
        if(connection != null) {
            try {
                connection.rollback();
                connection.close();
            }
            catch (SQLException e) {
                closeConnection();
                throw new DatabaseException("", e);
            }
        }
    }
}