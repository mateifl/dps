package transactions.strategy3;

import java.sql.Date;
import java.util.Calendar;

import dbaccess.beans.Customer;
import dbaccess.beans.Order;
import utils.DatabaseBuilder;

public class Main {

	public static void main(String[] args) {
		DatabaseBuilder.createDatabase();
		
		try {
			Transaction transaction = new JdbcTransaction();
			TransactionExecution transactionExecution = new TransactionExecution(transaction);
			
			Customer customer = new Customer();
			customer.setId(1);
			customer.setAddress("test first transaction");
			DatabaseWork updateCustomerAddress = new UpdateCustomer(customer, null);
			Order order = new Order();
			order.setCustomerId(1);
			order.setId(1);
			order.setProductId(1);
			order.setOrderDate(new Date(Calendar.getInstance().getTimeInMillis()));
			DatabaseWork insertOrderAndUpdateCustomer = new InsertOrder(order, updateCustomerAddress);
			
			transactionExecution.execute(insertOrderAndUpdateCustomer);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
