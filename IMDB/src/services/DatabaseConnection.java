package services;
import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {

    static Connection connection;
    public static Connection getDbConnection() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/pao_test";
            String user = "root";
            String password = "mysql";
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
