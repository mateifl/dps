package transactions.basic;


import utils.TransactionException;

public interface Transaction {
	void doTransaction() throws TransactionException;
}
