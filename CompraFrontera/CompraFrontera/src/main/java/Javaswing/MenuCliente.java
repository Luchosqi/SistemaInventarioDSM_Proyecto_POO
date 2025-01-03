package Javaswing;

import Mensajeria.Pedido;
import Usuarios.Cliente;
import Cartel.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MenuCliente extends JFrame {
    private Cliente clienteAutenticado;
    private JList<String> listaProductos;
    private DefaultListModel<String> modeloProductos;
    private List<Producto> productosDisponibles;
    private List<Producto> pedidoActual;
    private JButton btnAgregar, btnVerPedido, btnComprar, btnEliminar;
    private JLabel lblSaldo;

    public MenuCliente(Cliente clienteAutenticado) {
        this.clienteAutenticado = clienteAutenticado;
        setTitle("Menú de Cliente");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel lblProductos = new JLabel("Productos Disponibles:");
        lblProductos.setBounds(20, 20, 200, 30);
        add(lblProductos);

        lblSaldo = new JLabel("Saldo Actual: $" + clienteAutenticado.getSaldo());
        lblSaldo.setBounds(20, 500, 300, 30);
        add(lblSaldo);

        modeloProductos = new DefaultListModel<>();
        listaProductos = new JList<>(modeloProductos);
        JScrollPane scrollProductos = new JScrollPane(listaProductos);
        scrollProductos.setBounds(20, 60, 600, 400);
        add(scrollProductos);

        btnAgregar = new JButton("Agregar al Pedido");
        btnAgregar.setBounds(650, 60, 120, 30);
        add(btnAgregar);

        btnVerPedido = new JButton("Ver Pedido");
        btnVerPedido.setBounds(650, 100, 120, 30);
        add(btnVerPedido);

        btnComprar = new JButton("Comprar");
        btnComprar.setBounds(650, 140, 120, 30);
        add(btnComprar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(650, 180, 120, 30);
        add(btnEliminar);

        productosDisponibles = cargarProductos();
        pedidoActual = new ArrayList<>();

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaProductos.getSelectedIndex();
                if (index != -1) {
                    Producto producto = productosDisponibles.get(index);
                    pedidoActual.add(producto);
                    JOptionPane.showMessageDialog(null, "Producto agregado: " + producto.getNombre());
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto");
                }
            }
        });

        btnVerPedido.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder detalles = new StringBuilder("Productos en el Pedido:\n");
                for (Producto producto : pedidoActual) {
                    detalles.append("- ").append(producto.getNombre()).append("\n");
                }
                JOptionPane.showMessageDialog(null, detalles.toString());
            }
        });

        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarCompra();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = listaProductos.getSelectedIndex();
                if (index != -1) {
                    Producto producto = productosDisponibles.get(index);
                    if (pedidoActual.contains(producto)) {
                        pedidoActual.remove(producto);
                        JOptionPane.showMessageDialog(null, "Producto eliminado: " + producto.getNombre());
                    } else {
                        JOptionPane.showMessageDialog(null, "El producto seleccionado no está en el pedido.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Seleccione un producto para eliminar.");
                }
            }
        });

        cargarProductosEnLista();
    }

    private void actualizarSaldoEnPantalla() {
        lblSaldo.setText("Saldo Actual: $" + clienteAutenticado.getSaldo());
    }

    private List<Producto> cargarProductos() {
        ProductoDAO productoDAO = new ProductoDAO();
        System.out.println("Cargando productos desde la base de datos...");
        return productoDAO.cargarProductos();
    }

    private void cargarProductosEnLista() {
        for (Producto producto : productosDisponibles) {
            modeloProductos.addElement(producto.getNombre() + " - " + producto.getDescripcion() + " - $" + producto.getPrecio());
        }
    }

    private void realizarCompra() {
        if (pedidoActual.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El pedido está vacío.");
            return;
        }

        int total = pedidoActual.stream().mapToInt(Producto::getPrecio).sum();
        int saldoAnterior = clienteAutenticado.getSaldo();

        if (saldoAnterior < total) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para realizar la compra. Tu saldo actual es: $" + saldoAnterior);
            return;
        }

        ProductoDAO productoDAO = new ProductoDAO();
        boolean compraExitosa = true;


        for (Producto producto : pedidoActual) {
            if (productoDAO.cargarProductos().stream().noneMatch(p -> p.getId() == producto.getId() && p.getStock() > 0)) {
                JOptionPane.showMessageDialog(null, "No hay suficiente inventario para el producto: " + producto.getNombre());
                compraExitosa = false;
                break;
            }
        }

        if (compraExitosa) {

            for (Producto producto : pedidoActual) {
                boolean actualizado = productoDAO.actualizarInventario(producto.getId(), -1);
                if (!actualizado) {
                    JOptionPane.showMessageDialog(null, "No se pudo actualizar el inventario para el producto: " + producto.getNombre());
                    compraExitosa = false;
                    break;
                }
            }

            if (compraExitosa) {

                clienteAutenticado.setSaldo(clienteAutenticado.getSaldo() - total);

                if (!clienteAutenticado.actualizarSaldoEnBaseDeDatos()) {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el saldo en la base de datos. Verifique su conexión.");
                }
                actualizarSaldoEnPantalla();

                Pedido pedido = new Pedido(LocalDate.now(), clienteAutenticado);
                pedido.setProductos(pedidoActual);
                pedido.calcularTotal();

                StringBuilder mensajeFinal = new StringBuilder();
                mensajeFinal.append("Compra realizada con éxito.\n");
                mensajeFinal.append("\nDetalles del Pedido:\n").append(pedido.generarDetalles(saldoAnterior));
                mensajeFinal.append("\nSu pedido estará listo para retirar en 5 a 10 minutos");


                JOptionPane.showMessageDialog(null, mensajeFinal.toString());

                pedido.guardarPedidoEnBaseDeDatos();

                pedidoActual.clear();
            } else {
                JOptionPane.showMessageDialog(null, "La compra no pudo completarse. Verifique el inventario.");
            }
        }
    }



}
