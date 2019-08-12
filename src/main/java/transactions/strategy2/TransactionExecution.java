package transactions.strategy2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import utils.DatabaseException;

public class TransactionExecution<T> {

	private Transaction<T> transaction;
	
	public TransactionExecution(Transaction<T> transaction) {
		this.transaction = transaction;
	}
	
	private static Logger logger = LoggerFactory.getLogger(TransactionExecution.class);
	
	public void execute(DatabaseWork<T> databaseWork) {
		try {
        	logger.info("start transaction");
            transaction.start();
            transaction.execute(databaseWork);
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
