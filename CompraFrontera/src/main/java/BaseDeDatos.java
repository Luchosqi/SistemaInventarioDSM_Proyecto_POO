import java.sql.*;

public class BaseDeDatos {
    private static String url = "jdbc:mysql://localhost:3306/";
    private static final String user = "root";
    private static final String password = "****";

    public static void main(String[] args) {
        ejecutarConsulta("Select * from usuarios");
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url + user, password, url);
    }

    public static void ejecutarConsulta(String consulta) {
        try(Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consulta)){
            while(resultSet.next()){
                System.out.println(resultSet.getString("nombre"));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage() + "No se pudo hacer la consulta");
            throw new RuntimeException(e);
        }
    }

}
