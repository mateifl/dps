package transactions.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.DatabaseException;

/** 
 * Context for our Strategy Pattern. It will execute a Transaction implementation and commit.
 * In case of error, the transaction will be rolled back. 
 */

public class TransactionExecution {

    private static Logger logger = LoggerFactory.getLogger(TransactionExecution.class);
    /**
     * Execution method
     * @param transaction - implementation of the transaction interface that will be executed in this context.
     * @throws DatabaseException
     */
    public void execute(Transaction transaction) throws DatabaseException {
        try {
            transaction.start();
            transaction.execute();
            transaction.commit();
            logger.info("transaction committed");
        } catch (DatabaseException e) {
            transaction.rollback();
            logger.error("", e);
            throw e;
        }
    }

}
