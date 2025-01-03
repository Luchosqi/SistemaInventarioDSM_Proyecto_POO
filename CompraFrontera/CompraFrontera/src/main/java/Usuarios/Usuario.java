package Usuarios;
// Esta clase se creo pensada para crear un Cliente y un Administrador.
// Por temas de complicaciones a la hora de otorgar funcionalidades al administrador se elimino la clase Administrador.
public class Usuario {

    private String nombreUsuario;
    private String contrasenia;
    private String correo; // Se utiliza como un String sin utilidad real en los correos.
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

    public String getCorreo() {return correo;}

    public void setCorreo(String correo) {this.correo = correo;}

    public String getRol() {return rol;}

    public void setRol(String rol) {
        this.rol = rol;
    }

}
