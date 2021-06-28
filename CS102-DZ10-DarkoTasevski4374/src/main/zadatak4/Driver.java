import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public final class Driver {
    public static Connection connection = null;

    private Driver(){}

    public static Connection getConnection(){
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("db.properties"));

            String dburl = props.getProperty("dburl");
            String user = props.getProperty("user");
            String password = props.getProperty("password");

            System.out.println("Connecting to database: " + dburl);

            connection = DriverManager.getConnection(dburl, user, password);

            System.out.println("Connection successful!!!");
        }  catch (Exception exc) {
            System.out.println("Connection failed!!!");
            exc.printStackTrace();
        }

        return connection;
    }
}
