package Mensajeria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Cartel.Producto;
import Usuarios.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pedido {
    private int id; // Nuevo atributo
    private LocalDate fecha;
    private int totalPedido;
    private List<Producto> productos;
    private Cliente cliente;

    // Constructor sin ID (para pedidos nuevos)
    public Pedido(LocalDate fecha, Cliente cliente) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.totalPedido = 0;
    }

    // Constructor con ID (para cargar desde la base de datos)
    public Pedido(int id, LocalDate fecha, Cliente cliente) {
        this(fecha, cliente);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(int totalPedido) {
        this.totalPedido = totalPedido;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        calcularTotal();
        System.out.println("Producto agregado: " + producto.getNombre());
    }

    public void calcularTotal() {
        totalPedido = productos.stream().mapToInt(Producto::getPrecio).sum();
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public String generarDetalles() {
        StringBuilder detalles = new StringBuilder();
        detalles.append("Fecha del Pedido: ").append(fecha).append("\n");
        detalles.append("Cliente: ").append(cliente.getNombreUsuario()).append("\n");
        detalles.append("Productos:\n");
        for (Producto producto : productos) {
            detalles.append("- ").append(producto.getNombre())
                    .append(": $").append(producto.getPrecio()).append("\n");
        }
        detalles.append("Total: $").append(totalPedido).append("\n");
        return detalles.toString();
    }

    public void guardarPedidoEnBaseDeDatos() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO pedidos (fecha, cliente_id, total) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDate(1, java.sql.Date.valueOf(this.fecha));
            stmt.setInt(2, this.cliente.getId());
            stmt.setInt(3, this.totalPedido);
            stmt.executeUpdate();

            // Guardar productos asociados al pedido
            for (Producto producto : productos) {
                String queryProducto = "INSERT INTO productos_pedido (pedido_id, producto_id, cantidad) VALUES (?, ?, ?)";
                PreparedStatement stmtProducto = conn.prepareStatement(queryProducto);
                stmtProducto.setInt(1, this.id); // Asegúrate de recuperar el ID del pedido
                stmtProducto.setInt(2, producto.getId());
                stmtProducto.setInt(3, producto.getStock());
                stmtProducto.executeUpdate();
            }
            System.out.println("Pedido guardado en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



