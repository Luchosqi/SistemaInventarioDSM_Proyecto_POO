package Mensajeria;

import java.time.LocalDate;

public class Pago {
    private int monto;
    private String metodoPago;
    private LocalDate fechaPago;

    public Pago(int monto, String metodoPago, LocalDate fechaPago) {
        this.monto = monto;
        this.metodoPago = metodoPago;
        this.fechaPago = fechaPago;
    }

    public int getMonto() {return monto;}

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getMetodoPago() {return metodoPago;}

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public LocalDate getFechaPago() {return fechaPago;}

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public void realizarPago (){}
    public void generarRecibo(){}
    public void modificarComentario(){}
}
