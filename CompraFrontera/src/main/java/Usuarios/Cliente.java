package Usuarios;

import java.util.Scanner;

public class Cliente extends Usuario {

    private int saldo;

    public Cliente(String nombreUsuario, String contrasenia, String correo, String rol, int saldo) {
        super(nombreUsuario, contrasenia, correo, rol);
        this.saldo = saldo;
    }

    public int getSaldo() {
        return saldo;
    }

    public void realizarPedido(){
        System.out.println("Se ha realizado un pedido. Saldo actual: $" + (saldo -= 100));
    }
    public void recargarSaldo(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la cantidad a recargar: ");
        int recarga = scanner.nextInt();
        saldo += recarga;
        System.out.println("Saldo actualizado: $" + saldo);
    }
}
