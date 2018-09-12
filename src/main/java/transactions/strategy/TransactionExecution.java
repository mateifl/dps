package transactions.strategy;

import java.sql.SQLException;

import utils.DatabaseException;


/** Context for our Strategy Pattern. It will execute a Transaction implementation and commit.
 * In case of error, the transaction will be rolled back. */

public class TransactionExecution {

    // here we pass a Transaction implementation that will be executed in this method.
    public void execute(Transaction transaction) throws DatabaseException {
        try {
            transaction.start();
            transaction.execute();
            transaction.commit();
            System.out.println("committed");
        } catch (DatabaseException e) {
            System.out.println("rollback");
            transaction.rollback();
            throw e;
        }
    }

}
