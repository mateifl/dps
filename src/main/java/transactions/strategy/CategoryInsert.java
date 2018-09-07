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
        // again common code, prepare and execute
        try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, category.getId());
			preparedStatement.setString(2, category.getName());
			preparedStatement.execute();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new DatabaseException("", e);
		}
    }

}
