package transactions.strategy;

import java.sql.SQLException;

import utils.DatabaseException;


/** Context for our Strategy Pattern */

public class TransactionExecution {

    public void execute(Transaction transaction) throws DatabaseException {
        try {
            transaction.start();
            transaction.execute();
            transaction.commit();
        } catch (DatabaseException e) {
            transaction.rollback();
            throw e;
        }
    }

}
