package transactions.strategy;

import java.sql.SQLException;

/** This is another Context for our strategy pattern. It can be used for
 * testing purposes because it will rollback the transaction even if
 * every SQL statement succeeds.
 */

public class TestTransactionExecution {
    public void execute(Transaction transaction) throws Exception {
        try {
            transaction.start();
            transaction.execute();
            transaction.rollback();
        } catch (SQLException e) {
            transaction.rollback();
            throw new Exception(e);
        }
    }
}
