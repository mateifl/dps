package transactions.basic;

import dbaccess.ConnectionBuilder;
import utils.DatabaseException;

import java.sql.Connection;
import java.sql.SQLException;

public class CategoryInsert implements Transaction {

    public void doTransaction() throws DatabaseException {
        Connection connection = null;
        try {
            ConnectionBuilder.getConnection();
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            throw new DatabaseException(e.getMessage(), e);
        }
    }
}
