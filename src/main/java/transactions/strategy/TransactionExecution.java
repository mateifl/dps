package transactions.strategy;

import utils.DatabaseException;


/** Context for our Strategy Pattern. It will execute a Transaction implementation and commit.
 * In case of error, the transaction will be rolled back. */

public class TransactionExecution {

    /**
     *
     * @param transaction - implementation of the transaction interface that will be executed in this context.
     * @throws DatabaseException
     */
    public void execute(Transaction transaction) throws DatabaseException {
        try {
            transaction.start();
            transaction.execute();
            transaction.commit();
            System.out.println("transaction committed");
        } catch (DatabaseException e) {
            System.out.println("rollback");
            transaction.rollback();
            throw e;
        }
    }

}
