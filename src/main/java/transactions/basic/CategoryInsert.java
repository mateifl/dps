package transactions.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dbaccess.ConnectionBuilder;
import dbaccess.SQLStatements;
import dbaccess.beans.Category;
import utils.DatabaseException;

public class CategoryInsert implements Transaction {

    private static Logger logger = LoggerFactory.getLogger(CategoryInsert.class);
	
	private Category category;

	public CategoryInsert(Category category) {
		this.category = category;
	}
	
    public void doTransaction() throws DatabaseException {
        Connection connection = null;
        try {
            connection = ConnectionBuilder.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQLStatements.INSERT_CATEGORY);
			preparedStatement.setString(1, category.getName());
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            try {
            	if(connection != null)
            		connection.rollback();
            } catch (SQLException e1) {
                logger.error("", e);
                throw new DatabaseException(e.getMessage(), e1);
            }
            throw new DatabaseException(e.getMessage(), e);
        } finally {
        	try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				logger.error("", e);
				throw new DatabaseException(e.getMessage(), e);
			}
        }
    }
}
