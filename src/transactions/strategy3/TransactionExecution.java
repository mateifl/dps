package transactions.strategy3;

import java.sql.SQLException;


/** Context for our Strategy Pattern */
public class TransactionExecution {

	private Transaction transaction;
	
	public TransactionExecution(Transaction transaction) {
		this.transaction = transaction;
	}
	
    public <T> void execute(DatabaseWork<T> worker) throws Exception {
        try {
            transaction.start();
            transaction.execute(worker);
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
            throw new Exception(e);
        }
    }

}
