package Cartel;

public class Comestible extends Producto{

    private String gramos;

    public Comestible(boolean estado, int precio, String descripcion, String nombre, String gramos) {
        super(estado, precio, descripcion, nombre);
        this.gramos = gramos;
    }
    public String getGramos() {return gramos;}
}
