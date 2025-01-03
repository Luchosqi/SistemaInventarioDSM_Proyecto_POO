package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class DatabaseConnection {
        private static final String URL = "jdbc:mysql://localhost:32769/CompraFrontera";
        private static final String USER = "root";
        private static final String PASSWORD = "benja21babg09";

        public static Connection getConnection() {
            Connection connection = null;
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexión exitosa a la base de datos.");
            } catch (ClassNotFoundException e) {
                System.err.println("Error: No se encontró el driver de MySQL.");
                e.printStackTrace();
            } catch (SQLException e) {
                System.err.println("Error: No se pudo conectar a la base de datos.");
                e.printStackTrace();
            }
            return connection;
        }
    }
