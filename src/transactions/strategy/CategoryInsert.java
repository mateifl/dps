package transactions.strategy;

import java.sql.SQLException;

import dbaccess.beans.Category;

public class CategoryInsert extends AbstractJdbcTransaction {

    private static String sql = "insert into categories values(?, ?)";
    private Category category;

    public CategoryInsert(Category c) {
    	category = c;
    }
    
    public void execute() throws SQLException {
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, category.getId());
        preparedStatement.setString(2, category.getName());
        preparedStatement.execute();
    }

}
