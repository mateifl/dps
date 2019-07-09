package transactions.strategy;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dbaccess.beans.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseException;

/**
 * Implementation of insert category transaction.
 *
 */

public class CategoryInsert extends AbstractJdbcTransaction {

    private static Logger logger = LoggerFactory.getLogger(CategoryInsert.class);
    private static String sql = "insert into categories values(?, ?)";
    private Category category;

    public CategoryInsert(Category c) {
        category = c;
    }

    public void execute() throws DatabaseException {
        logger.info("Execute insert category");
        PreparedStatement preparedStatement = null;
        try {
            // start common code
            preparedStatement = connection.prepareStatement(sql);
            // end common code
            preparedStatement.setInt(1, category.getId());
            preparedStatement.setString(2, category.getName());
            // start common code
            preparedStatement.execute();
            preparedStatement.close();
            // end common code
        } catch (SQLException e) {
            logger.error("", e);
            throw new DatabaseException("", e);
        }
    }

}
