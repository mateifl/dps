package dbaccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data loader - loads data from the database into the application, using prepared statements.
 * Solution using inheritance.
 */

public interface DataLoader {

    List execute(String sql, List parameters) throws SQLException;
    void cleanUp() throws SQLException;
}


abstract class AbstractDataLoader implements DataLoader {

    protected Connection connection;
    protected PreparedStatement preparedStatement;

    public AbstractDataLoader(Connection c) {
        connection = c;
    }

    public void cleanUp() throws SQLException {
        if(preparedStatement != null)
            preparedStatement.close();
    }

}

class CustomerDataLoader extends AbstractDataLoader {

    public CustomerDataLoader(Connection c) {
        super(c);
    }

    public List execute(String sql, List parameters) throws SQLException {

        // create prepared statement
        preparedStatement = connection.prepareStatement(sql);
        // map parameters


        // execute query

        ResultSet r = preparedStatement.executeQuery();

        // create the list
        List l = new ArrayList();
        while(r.next()) {

        }
        // close the result set
        r.close();
        // return the list
        return l;
    }

}

class EmployeeDataLoader extends AbstractDataLoader {

    public EmployeeDataLoader(Connection c) {
        super(c);
    }

    public List execute(String sql, List parameters) {
        return null;
    }

}