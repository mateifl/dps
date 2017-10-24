package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.FileSystems;
import java.sql.Connection;
import java.sql.Statement;

import dbaccess.ConnectionBuilder;

public class DatabaseBuilder {

    public static void createDatabase() {
        try {
//        	FileSystems.getDefault().getPath("", more)
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
            System.out.println(e.getMessage());
        }
    }

}
