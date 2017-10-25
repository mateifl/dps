package transactions.strategy2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;

/** T represents the type that manages the transaction, for example
 * java.sql.Connection in JDBC, org.hibernate.Session in Hibernate, the entity manager in JPA.
 *
 *
 * */
public interface Transaction<T> {

    /** Create the transaction */
    void start() throws SQLException;
    
    /** Executes the database work */
    void execute(DatabaseWork<T> databaseWork) throws SQLException;

    /** Commit the transaction */
    void commit() throws SQLException;

    /** Roll back the transaction */
    void rollback() throws SQLException;

}

