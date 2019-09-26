package transactions.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.DatabaseException;

/** 
 * <p>Context for our Strategy Pattern.</p> It will execute a Transaction implementation and commit.
 * In case of error, the transaction will be rolled back. 
 */

public class TransactionExecution {

    private static Logger logger = LoggerFactory.getLogger(TransactionExecution.class);
    /**
     * <p>Execution method.</p>
     * It takes a {@link transactions.basic.Transaction} implementation and executes each step.
     * @param transaction - implementation of the transaction interface that will be executed in this context.
     * @throws DatabaseException
     */
    public void execute(Transaction transaction) throws DatabaseException {
        try {
        	logger.info("start transaction");
            transaction.start();
            transaction.execute();

            transaction.commit();
            logger.info("transaction committed");
        } catch (DatabaseException e) {
            transaction.rollback();
            logger.info("transaction rolled back");
            logger.error("", e);
            throw e;
        }
    }

}
