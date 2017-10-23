package utils;

import dbaccess.ConnectionBuilder;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseBuilder {

    public void createDatabase() {
        try {
            Connection connection = ConnectionBuilder.getConnection();
            Statement statement = connection.createStatement();
            statement.execute("create table customers(id int primary key, name varchar(255), country varchar(255), city varchar(255)) ");
            statement.execute("insert into customers values(1, 'John D', 'USA', 'New York')");
            statement.execute("insert into customers values(2, 'Jane D', 'USA', 'New York')");
            statement.execute("insert into customers values(3, 'Tom S', 'USA', 'Los Angeles')");
            statement.execute("insert into customers values(4, 'Tim S', 'UK', 'Manchester')");
            statement.execute("insert into customers values(5, 'Dave P', 'UK', 'London')");
            statement.execute("insert into customers values(6, 'Jean M', 'France', 'Paris')");
            statement.execute("insert into customers values(7, 'Monique N', 'France', 'Lyon')");
            statement.execute("create table categories(id int primary key, name varchar(255), description varchar(255)) ");
            statement.execute("insert into categories values(1, 'Paper', 'All kinds of paper')");
            statement.execute("insert into categories values(2, 'Pen', 'All kinds of pens')");
            statement.execute("insert into categories values(3, 'File', 'All kinds of files')");

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }

}
