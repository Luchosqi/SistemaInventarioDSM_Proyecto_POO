import Cartel.Bebestible;
import Cartel.Comestible;
import Usuarios.Cliente;
import Mensajeria.Pedido;
import java.util.List;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== Iniciando pruebas del sistema de compras ===");


            Cliente cliente = new Cliente("Luis Jaramillo", "1234",
                    "l.jaramillo02@ufromail.cl", "Cliente", 10000);
            Cliente cliente1 = new Cliente("mauri","mauri","arbol","cliente",5000);
            System.out.println("Cliente creado: " + cliente.getNombreUsuario());

            // Crear productos
            Bebestible bebida = new Bebestible(true, 1500, "Jugo de Naranja", "Jugo Natural", "500ml", 20);
            Comestible snack = new Comestible(true, 2500, "Galletas de Chocolate", "Galletas Choco", "200g", 15);
            System.out.println("Productos creados: ");
            System.out.println("- " + bebida.getNombre() + " ($" + bebida.getPrecio() + ")");
            System.out.println("- " + snack.getNombre() + " ($" + snack.getPrecio() + ")");

            bebida.guardarEnBaseDeDatos();
            snack.guardarEnBaseDeDatos();

            System.out.println("\n=== Realizando pedido ===");
            cliente.realizarPedido(List.of(bebida, snack));


            Pedido pedido = new Pedido(LocalDate.now(), cliente);
            pedido.agregarProducto(bebida);
            pedido.agregarProducto(snack);
            pedido.calcularTotal();
            System.out.println("Pedido total: $" + pedido.getTotalPedido());


            pedido.guardarPedidoEnBaseDeDatos();

            System.out.println("\n=== Pruebas finalizadas correctamente ===");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error durante las pruebas.");
        }
    }
}
