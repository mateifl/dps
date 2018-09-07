package transactions.strategy3;

public interface SQLStatements {
	public static final String CUSTOMER_UPDATE = "update customers set address = ? where id = ?";
	public static final String INSERT_ORDER = "insert into orders values(?, ?, ?, ?)";
}
