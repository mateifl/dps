package dbaccess;

import dbaccess.beans.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data loader - loads data from the database into the application, using prepared statements.
 * Solution using inheritance.
 */

//public interface DataLoader {
//
//    List execute(String sql, List parameters) throws SQLException;
//    void cleanUp() throws SQLException;
//}


//abstract class AbstractDataLoader implements DataLoader {
//
//    protected Connection connection;
//    protected PreparedStatement preparedStatement;
//
//    public AbstractDataLoader(Connection c) {
//        connection = c;
//    }
//
//    public void cleanUp() throws SQLException {
//        if(preparedStatement != null)
//            preparedStatement.close();
//    }
//
//}

/**
 * Customer loader with one parameter.
 * Problems:
 * - just 1 filter value;
 * - it cannot filter by id;
 * - cannot handle situations where we want to get only customer ids;
 * The method execute() does too much - code smell.
 */

class CustomersLoader //extends AbstractDataLoader
{

    public List execute(String sql, String filterValue) throws SQLException {
        // COMMON - create connection and prepared statement
        Connection connection = ConnectionBuilder.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        // -------------------------------------------------
        // map parameters
        preparedStatement.setString(1, filterValue);
        // -------------------------------------------------
        // COMMON execute statement
        ResultSet r = preparedStatement.executeQuery();
        // -------------------------------------------------
        // create the list
        List l = new ArrayList();
        while(r.next()) {
            Customer c = new Customer();
            c.setId(r.getInt(0));
            c.setName(r.getString(1));
            c.setCountry(r.getString(2));
            c.setCity(r.getString(3));
            l.add(c);
        }
        // close the result set
        // COMMON - clean up
        r.close();
        preparedStatement.close();
        connection.close();
        // return the list
        return l;
    }

}

//class CustomersByCountryLoader extends AbstractDataLoader {
//
//    public CustomersByCountryLoader(Connection c) {
//        super(c);
//    }
//
//    public List execute(String sql, List parameters) throws SQLException {
//
//        // create prepared statement
//        preparedStatement = connection.prepareStatement(sql);
//        // map parameters
//        preparedStatement.setString(1, (String) parameters.get(0));
//        // execute query
//        ResultSet r = preparedStatement.executeQuery();
//
//        // create the list
//        List l = new ArrayList();
//        while(r.next()) {
//            Customer c = new Customer();
//            c.setId(r.getInt(0));
//            c.setName(r.getString(1));
//            c.setCountry(r.getString(2));
//            c.setCity(r.getString(3));
//            l.add(c);
//        }
//        // close the result set
//        r.close();
//        // return the list
//        return l;
//    }
//
//}


//class EmployeeDataLoader extends AbstractDataLoader {
//
//    public EmployeeDataLoader(Connection c) {
//        super(c);
//    }
//
//    public List execute(String sql, List parameters) {
//        return null;
//    }
//
//}