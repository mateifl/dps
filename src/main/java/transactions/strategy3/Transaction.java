package transactions.strategy3;

import utils.DatabaseException;


public interface Transaction {

    /** Create the transaction */
    void start() throws DatabaseException;
    
    /** Executes the statements on the database */
    <T> void execute(DatabaseWork<T> worker) throws DatabaseException;

    /** Commit the transaction */
    void commit() throws DatabaseException;

    /** Roll back the transaction */
    void rollback() throws DatabaseException;

}
