package transactions.strategy2;

import dbaccess.ConnectionBuilder;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 */
// No need to make this class abstract anymore. The specific code
// will be separated in the parameter classes
public class AbstractJdbcTransaction implements Transaction<Connection> {

    protected Connection connection;

    @Override
    public void start() throws SQLException {
        connection = ConnectionBuilder.getConnection();
        connection.setAutoCommit(false);
    }

    @Override
    /** This method implements the Template design pattern */
    public void execute(DatabaseWork<Connection> databaseWork) throws SQLException {
        databaseWork.doInTransaction(connection);
    }

    @Override
    public void commit() throws SQLException {
        if(connection != null) {
            connection.commit();
            connection.close();
        }
    }

    @Override
    public void rollback() throws SQLException {
        if(connection != null) {
            connection.rollback();
            connection.close();
        }
    }
}