package transactions.strategy;

import utils.DatabaseException;

/** This is another Context for our strategy pattern. It can be used for
 * testing purposes because it will roll back the transaction even if
 * every SQL statement succeeds.
 */

public class TestTransactionExecution {

    public void execute(Transaction transaction) throws DatabaseException {
        try {
            transaction.start();
            transaction.execute();
        }
        finally {
            transaction.rollback();
        }
    }
}
