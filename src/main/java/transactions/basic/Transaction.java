package transactions.basic;

import utils.DatabaseException;

/**
 * This is the basic implementation for a transaction. 
 * All the actions are executed in the same method: create transaction, 
 * execute database operations, commit transactions or roll back in 
 * case of error. 
 * Such an implementation breaks basic software design principles, 
 * like procedural modularization. 
 */

public interface Transaction {
	void doTransaction() throws DatabaseException;
}
