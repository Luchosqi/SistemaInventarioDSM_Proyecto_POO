package Usuarios;

public class Usuario {

    private String nombreUsuario;
    private String contrasenia;
    private static String correo;
    private String rol;

    public Usuario(String nombreUsuario, String contrasenia, String correo, String rol) {
        this.nombreUsuario = nombreUsuario;
        this.contrasenia = contrasenia;
        this.correo = correo;
        this.rol = rol;
    }

    public String getNombreUsuario() {return nombreUsuario;}

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {return contrasenia;}

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public static String getCorreo() {return correo;}


    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {return rol;}

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void registrarUsuario() {

    }
    public void iniciarSesion() {

    }
    public void actualizarDatos() {

    }
}
