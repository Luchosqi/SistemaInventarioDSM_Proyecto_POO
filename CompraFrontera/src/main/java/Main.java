
import Cartel.Bebestible;
import Cartel.Comestible;
import Cartel.Producto;
import Usuarios.Cliente;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear productos
        Bebestible bebestible = new Bebestible(
                true,
                1500,
                "Jugo de naranja natural",
                "Jugo de Naranja",
                "500ml",
                10
        );

        Comestible comestible = new Comestible(
                true,
                2500,
                "Galletas de avena y chocolate",
                "Galletas de Avena",
                "200g",
                15
        );

        // Crear cliente
        Cliente cliente = new Cliente(
                "Luis Jaramillo",
                "1234",
                "l.jaramillo02@ufromail.cl",
                "Cliente",
                5000 // Saldo inicial
        );

        // Agregar productos al pedido
        List<Producto> productos = new ArrayList<>();
        productos.add(bebestible);
        productos.add(comestible);

        // Realizar pedido
        System.out.println("Realizando pedido...");
        cliente.realizarPedido(productos);

        // Fin del programa
        System.out.println("Programa finalizado.");
    }
}
