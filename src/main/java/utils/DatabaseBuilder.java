package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import dbaccess.ConnectionBuilder;

public class DatabaseBuilder {

    private static Logger logger = LoggerFactory.getLogger(DatabaseBuilder.class);

    public static void createDatabase() {
        try {
            Connection connection = ConnectionBuilder.getConnection();
            Statement statement = connection.createStatement();	
            BufferedReader reader = new BufferedReader(new FileReader(FileSystems.getDefault().getPath("db.sql").toString()));
            String line;
            while((line = reader.readLine()) != null)
            {
            	statement.execute(line);
            }
            reader.close();
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
    }

    public static void printDataFromTable(String table) {
        try {
            Connection connection = ConnectionBuilder.getConnection();
            Statement statement = connection.createStatement();	
            ResultSet rs = statement.executeQuery("select * from " + table);
            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            for(int i = 1; i <= columnCount; i++) {
            	int colType = metadata.getColumnType(i);
            	System.out.println(i);
            }
        }
        catch (Exception e)
        {
            logger.error("", e);
        }
    	
    }
    
}
