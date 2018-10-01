package transactions.strategy3;

import java.sql.Connection;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;
import utils.DatabaseException;

/** Strategy implementation */
public class JdbcTransaction implements Transaction {

    private Connection connection;

    public void start() throws DatabaseException {
        try {
            System.out.println("creating connection");
            connection = ConnectionBuilder.getConnection();
            connection.setAutoCommit(false);
            System.out.println("connection created");
        }
        catch (SQLException e) {
            throw new DatabaseException("", e);
        }
    }

    /** This method implements the Template Method design pattern */
    public void execute(DatabaseWork worker) throws DatabaseException {
    	worker.doInTransaction(connection);
    }
    
    public void commit() throws DatabaseException {
        try {
            System.out.println("JdbcTransaction.commit()");
            if(connection != null) {
                connection.commit();
                connection.close();
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("", e);
        }
    }

    public void rollback() throws DatabaseException {
        try {
            System.out.println("JdbcTransaction.rollback()");
            if(connection != null) {
                connection.rollback();
                connection.close();
            }
        }
        catch (SQLException e) {
            throw new DatabaseException("", e);
        }
    }
    
}