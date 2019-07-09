package transactions.strategy2;

import utils.DatabaseException;

/** T represents the type that manages the transaction, for example
 * java.sql.Connection in JDBC, org.hibernate.Session in Hibernate, the entity manager in JPA.
 *
 *
 */
public interface Transaction<T> {

    /** Create the transaction */
    void start() throws DatabaseException;
    
    /** 
     * Executes the database work.
     * T - parameter that represents the transaction provider. 
     * For JDBC, this is a java.sql.Connection object.
     */
    void execute(DatabaseWork<T> databaseWork) throws DatabaseException;

    /** Commit the transaction */
    void commit() throws DatabaseException;

    /** Roll back the transaction */
    void rollback() throws DatabaseException;

}
