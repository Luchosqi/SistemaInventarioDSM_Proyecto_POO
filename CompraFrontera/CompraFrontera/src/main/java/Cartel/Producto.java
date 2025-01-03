package Cartel;



public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private boolean estado;
    private int stock;

    // Constructor
    public Producto(int id, String nombre, String descripcion, int precio, boolean estado, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.estado = estado;
        this.stock = stock;
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNombre() {return nombre;}
    public void setNombre(String nombre) {this.nombre = nombre;}
    public String getDescripcion() {return descripcion;}
    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}
    public int getPrecio() {return precio;}
    public void setPrecio(int precio) {this.precio = precio;}
    public boolean isEstado() {return estado;}
    public void setEstado(boolean estado) {this.estado = estado;}
    public int getStock() {return stock;}
    public void setStock(int stock) {this.stock = stock;}


}
