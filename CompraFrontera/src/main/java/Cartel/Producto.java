package Cartel;
import java.sql.*;

package Cartel;

public class Producto {
    private int id; // Nuevo atributo
    private String nombre;
    private String descripcion;
    private int precio;
    private boolean estado;
    private int stock;

    // Constructor para creación en memoria (sin ID)
    public Producto(boolean estado, int precio, String descripcion, String nombre, int stock) {
        this.estado = estado;
        this.precio = precio;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.stock = stock;
    }

    // Constructor completo (incluyendo ID desde la base de datos)
    public Producto(int id, boolean estado, int precio, String descripcion, String nombre, int stock) {
        this(estado, precio, descripcion, nombre, stock);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            System.out.println("El stock no puede ser negativo.");
        } else {
            this.stock = stock;
        }
    }


    public void agregarStock(int cantidad) {
        if (cantidad > 0) {
            this.stock += cantidad;
            System.out.println("Se han agregado " + cantidad + " unidades. Stock actual: " + stock);
        } else {
            System.out.println("La cantidad para agregar debe ser positiva.");
        }
    }


    public void eliminarStock(int cantidad) {
        if (cantidad > 0) {
            if (this.stock >= cantidad) {
                this.stock -= cantidad;
                System.out.println("Se han eliminado " + cantidad + " unidades. Stock actual: " + stock);
            } else {
                System.out.println("No hay suficiente stock para eliminar " + cantidad + " unidades.");
            }
        } else {
            System.out.println("La cantidad para eliminar debe ser positiva.");
        }
    }


    public void actualizarEstado() {
        this.estado = this.stock > 0;
    }

    public void actualizarPrecio(int nuevoPrecio) {
        if (nuevoPrecio > 0) {
            this.precio = nuevoPrecio;
            System.out.println("El precio ha sido actualizado a $" + nuevoPrecio);
        } else {
            System.out.println("El precio debe ser mayor a cero.");
        }
    }
    public void guardarEnBaseDeDatos() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO productos (nombre, descripcion, precio, tipo, cantidad) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.descripcion);
            stmt.setInt(3, this.precio);
            stmt.setString(4, this instanceof Bebestible ? "bebestible" : "comestible");
            stmt.setInt(5, this.stock);
            stmt.executeUpdate();

            // Obtener el ID generado automáticamente
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                this.id = generatedKeys.getInt(1);
            }
            System.out.println("Producto guardado con ID: " + this.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void actualizarStockEnBaseDeDatos() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "UPDATE productos SET cantidad = ? WHERE nombre = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, this.stock);
            stmt.setString(2, this.nombre);
            stmt.executeUpdate();
            System.out.println("Stock actualizado en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
