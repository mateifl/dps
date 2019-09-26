package transactions.strategy;

import utils.DatabaseException;

/**
 * <p>Basic contract for a transaction.</p>
 * Comparing with {@link transactions.basic.Transaction}, the transactions steps are separated.
 * Separating the steps makes it is easier to modify them.
 * <p>Also the common steps can be implemented in a single place.
 * In this case {@link transactions.strategy.AbstractJdbcTransaction} implements the common steps.
 * </p>
 * Example: to add logging when on commit/rollback takes place we can change in only one method.
 */

public interface Transaction {

    /**
     * Creates and sets up the transaction.
     */
    void start() throws DatabaseException;

    /**
     * Executes the statements on the database.
     */
    void execute() throws DatabaseException;

    /**
     * Commit the transaction.
     */
    void commit() throws DatabaseException;

    /**
     * Roll back the transaction.
     */
    void rollback() throws DatabaseException;

}
