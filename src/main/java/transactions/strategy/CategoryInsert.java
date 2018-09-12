package transactions.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Category;
import utils.DatabaseException;

public class CategoryInsert extends AbstractJdbcTransaction {

    private static String sql = "insert into categories values(?, ?)";
    private Category category;
    private PreparedStatement preparedStatement;

    public CategoryInsert(Category c) {
    	category = c;
    }
    
    public void execute() throws DatabaseException {
    	try {
        	// common code
			preparedStatement = connection.prepareStatement(sql);
			// end common code
			preparedStatement.setInt(1, category.getId());
			preparedStatement.setString(2, category.getName());
			// common code
			preparedStatement.execute();
			preparedStatement.close();
			// end common code
		} catch (SQLException e) {
			throw new DatabaseException("", e);
		}
    }

}
