package Mensajeria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import Cartel.Producto;
import Usuarios.Cliente;

public class Pedido {
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

    public LocalDate getFecha() { return fecha; }

    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public int getTotalPedido() { return totalPedido; }

    public void setTotalPedido(int totalPedido) { this.totalPedido = totalPedido; }

    public Cliente getCliente() { return cliente; }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        calcularTotal();
        System.out.println("Producto agregado: " + producto.getNombre());
    }

    public void calcularTotal() {
        totalPedido = productos.stream().mapToInt(Producto::getPrecio).sum();
    }

    public List<Producto> getProductos() { return productos; }

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
}
