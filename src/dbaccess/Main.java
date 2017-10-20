package dbaccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            Connection c = null;
            //

            CustomerDataLoader customerDataLoader = new CustomerDataLoader(c);
            List l = customerDataLoader.execute("", new ArrayList());
            c.close();
        } catch (SQLException e) {

        }
    }
}
