package transactions.strategy2;

import dbaccess.beans.Customer;

public class CustomerInsert extends AbstractJdbcTransaction<Customer> {

	private final static ParameterMapper<Customer> parameterMapper = new CustomerInsertParameterMapper();
	private final static String sqlStatement = "insert into customer values(?, ?, ?)";
	
	public CustomerInsert(Customer c) {
		bean = c;
	}
	
	@Override
	protected ParameterMapper<Customer> getParameterMapper() {
		return parameterMapper;
	}

	@Override
	protected String getSQLStatement() {
		return sqlStatement;
	}

}
