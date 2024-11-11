package Cartel;

public class Producto {

    private String nombre;
    private String descripcion;
    private int precio;
    private boolean estado;

    public Producto(boolean estado, int precio, String descripcion, String nombre) {
        this.estado = estado;
        this.precio = precio;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public int getPrecio() {return precio;}

    public void setPrecio(int precio) {this.precio = precio;}

    public boolean isEstado() {return estado;}

    public void setEstado(boolean estado) {this.estado = estado;}

    public void actualizarEstado(){}
    public void actualizarPrecio(){}
}
