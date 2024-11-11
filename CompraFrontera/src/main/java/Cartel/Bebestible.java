package Cartel;

public class Bebestible extends Producto{

    private String mililitros;

    public Bebestible(boolean estado, int precio, String descripcion, String nombre, String mililitros) {
        super(estado, precio, descripcion, nombre);
        this.mililitros = mililitros;
    }

    public String getMililitros(){return mililitros;}


}
