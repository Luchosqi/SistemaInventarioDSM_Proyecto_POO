package Usuarios;

import Mensajeria.Pedido;
import Cartel.Producto;

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

    // Métodos Getters y Setters
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
}
