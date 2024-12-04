package Usuarios;

import DB.DatabaseConnection;
import Mensajeria.Pedido;
import Cartel.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private int id; // Nuevo atributo
    private int saldo;
    private List<Pedido> pedidos;

    // Constructor sin ID (para nuevos clientes)
    public Cliente(String nombreUsuario, String contrasenia, String correo, String rol, int saldo) {
        super(nombreUsuario, contrasenia, correo, rol);
        this.saldo = saldo;
        this.pedidos = new ArrayList<>();
    }


    // Constructor con ID (para cargar desde la base de datos)
    public Cliente(int id, String nombreUsuario, String contrasenia, String correo, String rol, int saldo) {
        this(nombreUsuario, contrasenia, correo, rol, saldo);
        this.id = id;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getSaldo() { return saldo; }
    public void setSaldo(int saldo) { this.saldo = saldo; }
    public List<Pedido> getPedidos() { return pedidos; }

    public void realizarPedido(List<Producto> productos) {
        Pedido pedido = new Pedido(LocalDate.now(), this);
        productos.forEach(pedido::agregarProducto);

        if (saldo >= pedido.getTotalPedido()) {
            saldo -= pedido.getTotalPedido();
            pedidos.add(pedido);

            System.out.println("Pedido realizado. Saldo restante: $" + saldo);


            pedido.guardarPedidoEnBaseDeDatos();
        } else {
            System.out.println("Saldo insuficiente para realizar el pedido.");
        }
    }
    public void registrarUsuarioEnBaseDeDatos() {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO clientes (nombre_usuario, contrasenia, correo, rol, saldo) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            stmt.setString(1, getNombreUsuario());
            stmt.setString(2, getContrasenia());
            stmt.setString(3, getCorreo());
            stmt.setString(4, getRol());
            stmt.setInt(5, saldo);

            int filasInsertadas = stmt.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("Cliente registrado exitosamente en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean existeUsuario(String nombreUsuario, String correo) {
        String query = "SELECT COUNT(*) FROM clientes WHERE nombre_usuario = ? OR correo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, correo);

            ResultSet rs = stmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

