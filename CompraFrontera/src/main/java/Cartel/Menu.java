package Cartel;

import java.time.LocalDate;

public class Menu {

    private String nombre;
    private String descripcion;
    private double descuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Menu(String nombre, String descripcion, double descuento, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public double getDescuento() {return descuento;}

    public void setDescuento(double descuento) {this.descuento = descuento;}

    public LocalDate getFechaInicio() {return fechaInicio;}

    public void setFechaInicio(LocalDate fechaInicio) {this.fechaInicio = fechaInicio;}

    public LocalDate getFechaFin() {return fechaFin;}

    public void setFechaFin(LocalDate fechaFin) {this.fechaFin = fechaFin;}

    public void esValida(){}
    public void aplicarDescuento(){}
}
