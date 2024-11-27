package Usuarios;

import Mensajeria.Notificacion;
import Mensajeria.Pedido;
import Cartel.Producto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {

    private int saldo;
    private List<Pedido> pedidos;

    public Cliente(String nombreUsuario, String contrasenia, String correo, String rol, int saldo) {
        super(nombreUsuario, contrasenia, correo, rol);
        this.saldo = saldo;
        this.pedidos = new ArrayList<>();
    }

    public int getSaldo() { return saldo; }

    public void realizarPedido(List<Producto> productos) {
        Pedido pedido = new Pedido(LocalDate.now(), this);
        for (Producto producto : productos) {
            pedido.agregarProducto(producto);
        }

        if (saldo >= pedido.getTotalPedido()) {
            saldo -= pedido.getTotalPedido();
            pedidos.add(pedido);

            System.out.println("Pedido realizado. Saldo restante: $" + saldo);

            // Enviar notificación
            Notificacion notificacion = new Notificacion();
            notificacion.enviarCorreoPedido(pedido);
        } else {
            System.out.println("Saldo insuficiente para realizar el pedido.");
        }
    }
}
