import Usuarios.Usuario;
import Usuarios.Administrador;
import Usuarios.Cliente;



import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de gestión.");
        System.out.println("Seleccione su rol:");
        System.out.println("1. Cliente");
        System.out.println("2. Administrador");
        System.out.print("Ingrese su opción: ");
        int opcionRol = scanner.nextInt();
        scanner.nextLine();

        if (opcionRol == 1) {
            gestionarCliente(scanner);
        } else if (opcionRol == 2) {
            gestionarAdministrador(scanner);
        } else {
            System.out.println("Opción inválida. Saliendo del sistema.");
        }

        scanner.close();
    }

    private static void gestionarCliente(Scanner scanner) {
        System.out.println("=== Cliente ===");
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasenia = scanner.nextLine();

        Cliente cliente = new Cliente(nombreUsuario, contrasenia, "cliente@correo.com", "Cliente", 1000);
        System.out.println("¡Bienvenido, " + cliente.getNombreUsuario() + "!");
        System.out.println("Saldo disponible: $" + cliente.getSaldo());

        int opcion;
        do {
            System.out.println("\nOpciones disponibles:");
            System.out.println("1. Realizar pedido");
            System.out.println("2. Recargar saldo");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    cliente.realizarPedido();
                    break;
                case 2:
                    cliente.recargarSaldo();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }

    private static void gestionarAdministrador(Scanner scanner) {
        System.out.println("=== Administrador ===");
        System.out.print("Ingrese su nombre de usuario: ");
        String nombreUsuario = scanner.nextLine();
        System.out.print("Ingrese su contraseña: ");
        String contrasenia = scanner.nextLine();

        Administrador administrador = new Administrador(nombreUsuario, contrasenia,
                "admin@correo.com", "Administrador");
        System.out.println("¡Bienvenido, " + administrador.getNombreUsuario() + "!");

        int opcion;
        do {
            System.out.println("\nOpciones disponibles:");
            System.out.println("1. Gestionar menú");
            System.out.println("2. Gestionar pedidos");
            System.out.println("3. Revisar pagos");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    administrador.gestionarMenu();
                    break;
                case 2:
                    administrador.gestionarPedidos();
                    break;
                case 3:
                    administrador.revisarPagos();
                    break;
                case 4:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }
}
