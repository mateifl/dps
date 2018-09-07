package transactions.strategy;

import dbaccess.beans.Category;
import dbaccess.beans.Customer;
import utils.DatabaseBuilder;

public class Main {

	public static void main(String[] args) {
		
		try {
			DatabaseBuilder.createDatabase();
			
			TransactionExecution transactionExecution = new TransactionExecution();
			
			Customer customer = new Customer();
			customer.setName("Test insert");
			customer.setAddress("Test insert address");
			customer.setPhone("+102323323");
			Transaction transaction = new CustomerInsert(customer);
			transactionExecution.execute(transaction);
			
			Category category = new Category();
			transaction = new CategoryInsert(category);
			transactionExecution.execute(transaction);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}