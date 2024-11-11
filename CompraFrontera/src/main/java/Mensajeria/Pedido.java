package Mensajeria;

import java.time.LocalDate;

public class Pedido {
    private LocalDate fecha;
    private int totalPedido;

    public Pedido(LocalDate fecha, int totalPedido) {
        this.fecha = fecha;
        this.totalPedido = totalPedido;
    }

    public LocalDate getFecha() {return fecha;}

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getTotalPedido() {return totalPedido;}

    public void setTotalPedido(int totalPedido) {
        this.totalPedido = totalPedido;
    }

    public void confirmarPedido(){}
    public void calcularTotal(){}
}
