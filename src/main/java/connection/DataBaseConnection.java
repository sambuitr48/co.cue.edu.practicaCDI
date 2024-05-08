package connection;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    private static String url = "jdbc:mysql://127.0.0.1:3306/toy_shop";
    private static String user = "root";
    private static String password = "";
    private static Connection connection;
    private static BasicDataSource pool;
    public static Connection getInstance() throws SQLException {

        if (connection == null) {
            connection = DriverManager.getConnection(url,user, password);
        }
        return connection;
    }
}
