package Mensajeria;

import java.time.LocalDate;

public class Opinion {

    private String comentarios;
    private String calificacion;
    private LocalDate fecha;

    public Opinion(String comentarios, String calificacion, LocalDate fecha) {
        this.comentarios = comentarios;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getCalificacion() {return calificacion;}

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFecha() {return fecha;}

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void actualizarCalificacion() {

    }
}
