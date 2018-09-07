package transactions.strategy;

import java.sql.Connection;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;
import utils.DatabaseException;

/** Basic contract for a transaction */

public interface Transaction {

    /** Create the transaction
     */
    void start() throws DatabaseException;

    /** Executes the statements on the database */
    void execute() throws DatabaseException;

    /** Commit the transaction */
    void commit() throws DatabaseException;

    /** Roll back the transaction */
    void rollback() throws DatabaseException;

}



abstract class AbstractJdbcTransaction implements Transaction {

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
    
    private void closeConnection() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            System.out.println("error closing connection: " + e.getMessage());
        }
    }


}