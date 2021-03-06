package transactions.strategy3;

import utils.DatabaseException;


/** Context for the Strategy Pattern. */
public class TransactionExecution {

	private Transaction transaction;
	
	public TransactionExecution(Transaction<?> transaction) {
		this.transaction = transaction;
	}
	
    public void execute(DatabaseWork<?> worker) throws DatabaseException {
        try {
            transaction.start();
            transaction.execute(worker);
            transaction.commit();
        } catch (DatabaseException e) {
            transaction.rollback();
            throw e;
        }
    }

}
