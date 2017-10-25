package transactions.strategy3;

import dbaccess.beans.Customer;
import dbaccess.beans.Order;
import utils.DatabaseBuilder;

public class Main {

	public static void main(String[] args) {
		DatabaseBuilder.createDatabase();
		
		try {
			TransactionExecution transactionExecution = new TransactionExecution();
			
			Transaction updateCustomerAddress = new JdbcTransaction<Customer>(new CustomerUpdateParameterMapper(), 
														  SQLStatements.CUSTOMER_UPDATE, null);
			
			Transaction insertOrderAndUpdateCustomer = new JdbcTransaction<Order>(new OrderInsertParameterMapper(), 
					  									  SQLStatements.INSERT_ORDER, updateCustomerAddress);
			
			transactionExecution.execute(insertOrderAndUpdateCustomer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
