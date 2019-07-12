package transactions.basic;

import java.sql.Connection;
import java.sql.SQLException;

import dbaccess.ConnectionBuilder;
import dbaccess.beans.Category;
import utils.DatabaseException;

public class CategoryInsert implements Transaction {

	private Category category;

	public CategoryInsert(Category category) {
		this.category = category;
	}
	
    public void doTransaction() throws DatabaseException {
        Connection connection = null;
        try {
            connection = ConnectionBuilder.getConnection();
            
            connection.setAutoCommit(false);
            connection.commit();
        } catch (SQLException e) {
            try {
            	if(connection != null)
            		connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
                throw new DatabaseException(e.getMessage(), e1);
            }
            throw new DatabaseException(e.getMessage(), e);
        } finally {
        	try {
				if(connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DatabaseException(e.getMessage(), e);
			}
        }
    }
}
