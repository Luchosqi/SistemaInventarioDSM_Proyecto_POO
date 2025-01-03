package Mensajeria;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Cartel.Producto;
import Usuarios.Cliente;

import DB.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Pedido {
    private int id;
    private LocalDate fecha;
    private int totalPedido;
    private List<Producto> productos;
    private Cliente cliente;

    public Pedido(LocalDate fecha, Cliente cliente) {
        this.fecha = fecha;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.totalPedido = 0;
    }

    public Pedido(){

    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}
    public LocalDate getFecha() {return fecha;}
    public void setFecha(LocalDate fecha) {this.fecha = fecha;}
    public void setTotalPedido(int totalPedido) {this.totalPedido = totalPedido;}

    public List<Producto> getProductos() {return productos;}

    public Cliente getCliente() {return cliente;}

    public void setCliente(Cliente cliente) {this.cliente = cliente;}

    public int getTotalPedido() {
        return totalPedido;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void calcularTotal() {
        totalPedido = productos.stream().mapToInt(Producto::getPrecio).sum();
        System.out.println("Total calculado: " + totalPedido);
    }

    public String generarDetalles(int saldoAnterior) {
        StringBuilder detalles = new StringBuilder();
        detalles.append("Fecha del Pedido: ").append(fecha).append("\n");
        detalles.append("Cliente: ").append(cliente.getNombreUsuario()).append("\n");
        detalles.append("Productos:\n");
        for (Producto producto : productos) {
            detalles.append("- ").append(producto.getNombre())
                    .append(": $").append(producto.getPrecio()).append("\n");
        }
        detalles.append("Total: $").append(totalPedido).append("\n");
        detalles.append("Saldo anterior: $").append(saldoAnterior).append("\n");
        detalles.append("Saldo restante: $").append(cliente.getSaldo()).append("\n");
        return detalles.toString();
    }

    public void guardarPedidoEnBaseDeDatos() {
        if (this.cliente == null || this.cliente.getId() <= 0) {
            System.err.println("Error: Cliente sin ID válido para guardar el pedido.");
            return;
        }
        String queryPedido = "INSERT INTO pedidos (fecha, cliente_id, total) VALUES (?, ?, ?)";
        String queryProductos = "INSERT INTO productos_pedido (pedido_id, producto_id, cantidad) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);


            try (PreparedStatement stmtPedido = conn.prepareStatement(queryPedido, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmtPedido.setDate(1, java.sql.Date.valueOf(this.fecha));
                stmtPedido.setInt(2, this.cliente.getId());
                stmtPedido.setInt(3, this.totalPedido);
                stmtPedido.executeUpdate();

                try (ResultSet generatedKeys = stmtPedido.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        this.id = generatedKeys.getInt(1);
                    } else {
                        throw new SQLException("No se pudo obtener el ID del pedido.");
                    }
                }
            }

            try (PreparedStatement stmtProductos = conn.prepareStatement(queryProductos)) {
                for (Producto producto : productos) {
                    stmtProductos.setInt(1, this.id);
                    stmtProductos.setInt(2, producto.getId());
                    stmtProductos.setInt(3, 1);
                    stmtProductos.addBatch();
                }
                stmtProductos.executeBatch();
            }

            conn.commit();
            System.out.println("Pedido y productos guardados en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



