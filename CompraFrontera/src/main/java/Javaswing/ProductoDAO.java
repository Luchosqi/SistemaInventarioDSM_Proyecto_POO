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
                        rs.getBoolean("estado"),
                        rs.getInt("precio"),
                        rs.getString("descripcion"),
                        rs.getString("nombre"),
                        rs.getInt("cantidad")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }
}

