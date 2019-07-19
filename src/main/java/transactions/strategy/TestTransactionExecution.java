package transactions.strategy;

import utils.DatabaseException;

/** This is another Context for our strategy pattern. It can be used for
 * testing purposes because it will roll back the transaction even if
 * every SQL statement succeeds.
 * Along with {@link transactions.strategy.TransactionExecution} it shows how
 * the same Strategy, in this case represented by the {@link transactions.strategy.Transaction} interface
 * and its implementations can be used in different contexts without changing the strategy itself.
 * To achieve this, the a new implementation of the transactions was necessary.
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
