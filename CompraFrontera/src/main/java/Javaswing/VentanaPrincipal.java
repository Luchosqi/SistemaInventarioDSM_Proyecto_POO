package Javaswing;

import Usuarios.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JTextField txtNombreUsuario, txtCorreo, txtSaldo;
    private JPasswordField txtContrasenia;
    private JButton btnRegistrar, btnIniciarSesion;

    public VentanaPrincipal() {
        setTitle("Registro de Cliente");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        lblUsuario.setBounds(20, 20, 150, 30);
        add(lblUsuario);

        txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(170, 20, 200, 30);
        add(txtNombreUsuario);

        JLabel lblContrasenia = new JLabel("Contraseña:");
        lblContrasenia.setBounds(20, 60, 150, 30);
        add(lblContrasenia);

        txtContrasenia = new JPasswordField();
        txtContrasenia.setBounds(170, 60, 200, 30);
        add(txtContrasenia);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(20, 100, 150, 30);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(170, 100, 200, 30);
        add(txtCorreo);

        JLabel lblSaldo = new JLabel("Saldo:");
        lblSaldo.setBounds(20, 140, 150, 30);
        add(lblSaldo);

        txtSaldo = new JTextField();
        txtSaldo.setBounds(170, 140, 200, 30);
        add(txtSaldo);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(50, 200, 120, 30);
        add(btnRegistrar);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setBounds(200, 200, 150, 30);
        add(btnIniciarSesion);

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombreUsuario.getText();
                String contrasenia = String.valueOf(txtContrasenia.getPassword());
                String correo = txtCorreo.getText();
                int saldo = Integer.parseInt(txtSaldo.getText());

                Cliente nuevoCliente = new Cliente(nombre, contrasenia, correo, "Cliente", saldo);
                // Guardar en la base de datos
                guardarClienteEnBD(nuevoCliente);
                JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente");
            }
        });

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes agregar la lógica para iniciar sesión y redirigir al menú de cliente
                new MenuCliente().setVisible(true);
                dispose();
            }
        });
    }

    private void guardarClienteEnBD(Cliente cliente) {
        cliente.registrarUsuario(); // Llama a la lógica para guardar el cliente en BD
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}

