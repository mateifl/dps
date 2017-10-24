package transactions.strategy;

import java.sql.SQLException;


/** Context for our Strategy Pattern */

public class TransactionExecution {

    public void execute(Transaction transaction) throws Exception {
        try {
            transaction.start();
            transaction.execute();
            transaction.commit();
        } catch (SQLException e) {
            transaction.rollback();
            throw new Exception(e);
        }
    }

}
