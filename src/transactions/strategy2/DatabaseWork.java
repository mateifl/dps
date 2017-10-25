package transactions.strategy2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by matei on 10/25/17.
 */


/** General contract for a database unit of work that has to be executed in a transaction */
public interface DatabaseWork<T> {
    void doInTransaction(T object);
}


class InsertCustomer implements DatabaseWork<Connection> {


    @Override
    public void doInTransaction(Connection connection) {
        try {
            PreparedStatement ps = connection.prepareStatement(SQLStatements.INSERT_CUSTOMER);
//            ps.setInt();
        }
        catch(SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}