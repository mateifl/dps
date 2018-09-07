package dbaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBuilder {

	private ConnectionBuilder() {
	}

	private static boolean init = false;
	private static String url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	private static String user = "";
	private static String password = "";

	public static Connection getConnection() {

		try {
			if (!init) {
				Class.forName("org.h2.Driver");
				init = true;
			}
			Connection connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return null;
	}

}
