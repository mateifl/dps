package transactions.basic;


import utils.TransactionException;

public interface BasicTransaction {
	void doTransaction() throws TransactionException;
}


