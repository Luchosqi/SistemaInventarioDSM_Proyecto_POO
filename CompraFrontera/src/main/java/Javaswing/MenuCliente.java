package Javaswing;

import Cartel.Producto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MenuCliente extends JFrame {
    private JList<String> listaProductos;
    private DefaultListModel<String> modeloProductos;
    private List<Producto> productosDisponibles;
    private List<Producto> pedidoActual;
    private JButton btnAgregar, btnVerPedido, btnComprar;

    public MenuCliente() {
        setTitle("Menú de Cliente");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblProductos = new JLabel("Productos Disponibles:");
        lblProductos.setBounds(20, 20, 200, 30);
        add(lblProductos);

        modeloProductos = new DefaultListModel<>();
        listaProductos = new JList<>(modeloProductos);
        JScrollPane scrollProductos = new JScrollPane(listaProductos);
        scrollProductos.setBounds(20, 60, 400, 200);
        add(scrollProductos);

        btnAgregar = new JButton("Agregar al Pedido");
        btnAgregar.setBounds(450, 60, 120, 30);
        add(btnAgregar);

        btnVerPedido = new JButton("Ver Pedido");
        btnVerPedido.setBounds(450, 100, 120, 30);
        add(btnVerPedido);

        btnComprar = new JButton("Comprar");
        btnComprar.setBounds(450, 140, 120, 30);
        add(btnComprar);

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

        cargarProductosEnLista();
    }

    private List<Producto> cargarProductos() {
        // Simula la carga de productos desde la base de datos
        return List.of(
                new Producto(true, 5000, "Bebida", "Coca-Cola", 10),
                new Producto(true, 1500, "Snack", "Papas Fritas", 20)
        );
    }

    private void cargarProductosEnLista() {
        for (Producto producto : productosDisponibles) {
            modeloProductos.addElement(producto.getNombre() + " - $" + producto.getPrecio());
        }
    }

    private void realizarCompra() {
        int total = pedidoActual.stream().mapToInt(Producto::getPrecio).sum();
        JOptionPane.showMessageDialog(null, "Compra realizada. Total: $" + total);
        pedidoActual.clear();
    }
}

