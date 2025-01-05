package Usuarios;

import DB.DatabaseConnection;
import Mensajeria.Pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
    private int id;
    private int saldo;
    private List<Pedido> pedidos;

    public Cliente(String nombreUsuario, String contrasenia, String correo, String rol, int saldo) {
        super(nombreUsuario, contrasenia, correo, rol);
        this.saldo = saldo;
        this.pedidos = new ArrayList<>();
    }

    public Cliente() {
        super("", "", "", "Cliente");
        this.saldo = saldo ;
        this.pedidos = new ArrayList<>();
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getSaldo() { return saldo; }
    public void setSaldo(int saldo) { this.saldo = saldo; }


    public boolean verificarCredenciales(String nombreUsuario, String contrasenia) {
        String query = "SELECT * FROM clientes WHERE nombre_usuario = ? AND contrasenia = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasenia);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                this.id = rs.getInt("id");
                this.setNombreUsuario(rs.getString("nombre_usuario"));
                this.setContrasenia(rs.getString("contrasenia"));
                this.setCorreo(rs.getString("correo"));
                this.setRol(rs.getString("rol"));
                this.saldo = rs.getInt("saldo");
                System.out.println("Nombre de usuario: " + this.getNombreUsuario());
                System.out.println("Contrasenia: " + this.getContrasenia());
                System.out.println("Correo: " + this.getCorreo());
                System.out.println("Saldo cargado desde la base de datos: " + this.saldo);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean actualizarSaldoEnBaseDeDatos() {
        String query = "UPDATE clientes SET saldo = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, this.saldo);
            stmt.setInt(2, this.id);
            int filasAfectadas = stmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Saldo actualizado correctamente en la base de datos.");
                return true;
            } else {
                System.err.println("No se pudo actualizar el saldo en la base de datos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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

    public boolean existeUsuario(String nombreUsuario, String contrasenia, String correo) {
        String query = "SELECT COUNT(*) FROM clientes WHERE nombre_usuario = ? OR contrasenia = ? OR correo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contrasenia);
            stmt.setString(3, correo);

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