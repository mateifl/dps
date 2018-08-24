package transactions.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Category;

public class CategoryInsert extends AbstractJdbcTransaction {

    private static String sql = "insert into categories values(?, ?)";
    private Category category;
    private PreparedStatement preparedStatement;

    public CategoryInsert(Category c) {
    	category = c;
    }
    
    public void execute() throws SQLException {
        // again common code, prepare and execute
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, category.getId());
        preparedStatement.setString(2, category.getName());
        preparedStatement.execute();
        preparedStatement.close();
    }

}
