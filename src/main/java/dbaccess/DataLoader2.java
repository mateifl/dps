package dbaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dbaccess.beans.Customer;

/**
 * TODO what does this class do?
 * @author mflorescu
 *
 */
public interface DataLoader2 {
    //
    void setup(String sql) throws SQLException;

    //
    void mapParameters(List<?> parameters) throws SQLException;

    void execute() throws SQLException;

    List<?> processResult() throws SQLException;

    void cleanUp() throws SQLException;
}

abstract class AbstractDataLoader2 implements DataLoader2 {

    protected Connection connection;
    protected PreparedStatement preparedStatement;
    protected ResultSet resultSet;

    public AbstractDataLoader2(Connection c) {
        connection = c;
    }

    public void setup(String sql) throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
    }

    public void execute() throws SQLException {
        resultSet = preparedStatement.executeQuery();
    }

    public void cleanUp() throws SQLException {
        if(resultSet != null)
            resultSet.close();

        if(preparedStatement != null)
            preparedStatement.close();
    }

}

class CustomerDataLoader2 extends AbstractDataLoader2 {

    public CustomerDataLoader2(Connection c) {
        super(c);
    }

    public void mapParameters(List parameterValues) throws SQLException {
        preparedStatement.setInt(1, 1);
    }

    public List<Customer> processResult() throws SQLException {
        return null;
    }
}