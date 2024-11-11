package Usuarios;

public class Cliente extends Usuario {

    private int saldo;

    public Cliente(String nombreUsuario, String contrasenia, String correo, String rol, int saldo) {
        super(nombreUsuario, contrasenia, correo, rol);
        this.saldo = saldo;
    }



    public void realizarPedido(){

    }
    public void recargarSaldo(){

    }
}
