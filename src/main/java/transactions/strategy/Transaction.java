package transactions.strategy;

import java.sql.Connection;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;
import utils.DatabaseException;

/**
 * Basic contract for a transaction.
 * On comparing with {@link transactions.basic.Transaction}, we can see the transactions
 * steps are separated one in its own method.
 * By separating the steps is easier to modify each one of them.
 * The main advantage is the common steps can be implemented in a single place.
 * In this case {@link transactions.strategy.AbstractJdbcTransaction} implements the common steps.
 *
 * Example: to add logging when on commit/rollback takes place we can change in 
 * only one method.
 */

public interface Transaction {

    /**
     * Create/setup the transaction
     */
    void start() throws DatabaseException;

    /**
     * Executes the statements on the database
     */
    void execute() throws DatabaseException;

    /**
     * Commit the transaction
     */
    void commit() throws DatabaseException;

    /**
     * Roll back the transaction
     */
    void rollback() throws DatabaseException;

}
