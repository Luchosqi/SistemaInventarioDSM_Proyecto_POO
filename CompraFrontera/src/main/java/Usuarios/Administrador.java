package Usuarios;

import java.util.List;

public class Administrador extends Usuario {

    public Administrador(String nombreUsuario, String contrasenia, String correo, String rol) {
        super(nombreUsuario, contrasenia, correo, rol);
    }

    public void gestionarMenu(){
        System.out.println("Menu gestionado correctamente.");
    }
    public void gestionarPedidos(){
        System.out.println("Pedidos gestionados correctamente.");
    }
    public void revisarPagos(){
        System.out.println("Pagos revisados correctamente.");
    }
    public void listarUsuarios(List<Usuario> usuarios) {
        System.out.println("Usuarios registrados:");
        for (Usuario usuario : usuarios) {
            System.out.println("- " + usuario.getNombreUsuario() + " (" + usuario.getRol() + ")");
        }
    }

}
