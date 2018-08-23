package transactions.basic;


import utils.TransactionException;

public interface BasicTransaction {
	public void doInTransaction() throws TransactionException;
	
}


