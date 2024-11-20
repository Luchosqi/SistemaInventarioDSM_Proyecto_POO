package Cartel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Menu {

    private String nombre;
    private String descripcion;
    private double descuento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Producto> productos;

    public Menu(String nombre, String descripcion, double descuento, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.descuento = descuento;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.productos = new ArrayList<>();
    }

    // Métodos Getter y Setter
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public List<Producto> getProductos() { return productos; }


    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void listarProductos() {
        for (Producto producto : productos) {
            System.out.println("Producto: " + producto.getNombre() +
                    ", Precio: " + producto.getPrecio() +
                    ", Descripcion: " + producto.getDescripcion());
        }
    }


    public String exportarProductosJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(productos);
    }


    public boolean esValida() {
        LocalDate hoy = LocalDate.now();
        return !hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin);
    }

    public void aplicarDescuento() {
        for (Producto producto : productos) {
            int precioConDescuento = (int) (producto.getPrecio() * (1 - descuento / 100));
            producto.setPrecio(precioConDescuento);
        }
    }
}
