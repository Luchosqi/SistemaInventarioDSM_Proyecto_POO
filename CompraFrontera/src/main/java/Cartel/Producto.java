package Cartel;

public class Producto {

    private String nombre;
    private String descripcion;
    private int precio;
    private boolean estado;
    private int stock;

    public Producto(boolean estado, int precio, String descripcion, String nombre, int stock) {
        this.estado = estado;
        this.precio = precio;
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.stock = 0;
    }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }

    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getPrecio() { return precio; }

    public void setPrecio(int precio) { this.precio = precio; }

    public boolean isEstado() { return estado; }

    public void setEstado(boolean estado) { this.estado = estado; }

    public int getStock() { return stock; }

    public void setStock(int stock) {
        if (stock < 0) {
            System.out.println("El stock no puede ser negativo.");
        } else {
            this.stock = stock;
        }
    }


    public void agregarStock(int cantidad) {
        if (cantidad > 0) {
            this.stock += cantidad;
            System.out.println("Se han agregado " + cantidad + " unidades. Stock actual: " + stock);
        } else {
            System.out.println("La cantidad para agregar debe ser positiva.");
        }
    }


    public void eliminarStock(int cantidad) {
        if (cantidad > 0) {
            if (this.stock >= cantidad) {
                this.stock -= cantidad;
                System.out.println("Se han eliminado " + cantidad + " unidades. Stock actual: " + stock);
            } else {
                System.out.println("No hay suficiente stock para eliminar " + cantidad + " unidades.");
            }
        } else {
            System.out.println("La cantidad para eliminar debe ser positiva.");
        }
    }


    public void actualizarEstado() {
        this.estado = this.stock > 0;
    }

    public void actualizarPrecio() {

    }
}
