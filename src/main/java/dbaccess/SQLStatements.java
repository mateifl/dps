package dbaccess;

public interface SQLStatements {
	public static final String CUSTOMER_UPDATE = "update customers set address = ? where id = ?";
	public static final String INSERT_ORDER = "insert into orders values(?, ?, ?, ?)";
	public static final String INSERT_CUSTOMER = "insert into customers values(?, ?, ?, ?, ?)";
	public static final String INSERT_CATEGORY = "insert into categories values(?, ?)";
}
