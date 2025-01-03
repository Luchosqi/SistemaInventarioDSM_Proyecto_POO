package Javaswing;

import Cartel.Producto;
import DB.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {


    public List<Producto> cargarProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT id, nombre, descripcion, precio, cantidad, estado FROM productos WHERE estado = 1";

        try (Connection conn = DatabaseConnection.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getInt("precio"),
                        rs.getBoolean("estado"),
                        rs.getInt("cantidad")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return productos;
    }

    public boolean actualizarInventario(int productoId, int cantidad) {
        String sql = "UPDATE productos SET cantidad = cantidad + ? WHERE id = ? AND cantidad + ? >= 0";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, cantidad);
            stmt.setInt(2, productoId);
            stmt.setInt(3, cantidad);

            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }


}
