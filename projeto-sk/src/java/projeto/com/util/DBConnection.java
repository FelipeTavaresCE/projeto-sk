package projeto.com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getDb() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost/projetoskyone", "root", "***");
            return conexao;
        } catch (SQLException e) {
            System.out.println("ERROR" + e);
        }
        return getDb();
    }
}
