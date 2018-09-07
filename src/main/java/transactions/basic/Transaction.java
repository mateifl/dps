package transactions.basic;

import utils.DatabaseException;

public interface Transaction {
	void doTransaction() throws DatabaseException;
}
