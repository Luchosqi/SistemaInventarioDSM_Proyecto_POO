package Javaswing;

import Usuarios.Cliente;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    JTextField txtNombreUsuario;
    JTextField txtCorreo;
    JTextField txtSaldo;
    JPasswordField txtContrasenia;
    JButton btnRegistrar;
    private JButton btnIniciarSesion;


    public VentanaPrincipal() {
        setTitle("CompraFrontera");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

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

        JLabel lblSaldo = new JLabel("<html>Saldo / No modificable<br>una vez ingresado:</html>");
        lblSaldo.setBounds(20, 140, 200, 50);
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

                if (txtNombreUsuario.getText().isEmpty() ||
                        String.valueOf(txtContrasenia.getPassword()).isEmpty() ||
                        txtCorreo.getText().isEmpty() ||
                        txtSaldo.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
                    return;
                }

                try {
                    String nombre = txtNombreUsuario.getText();
                    String contrasenia = String.valueOf(txtContrasenia.getPassword());
                    String correo = txtCorreo.getText();


                    if (!txtSaldo.getText().matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "El saldo debe ser un número válido.");
                        return;
                    }

                    int saldo = Integer.parseInt(txtSaldo.getText());

                    Cliente nuevoCliente = new Cliente(nombre, contrasenia, correo, "Cliente", saldo);
                    if (nuevoCliente.existeUsuario(nombre,contrasenia, correo)) {
                        JOptionPane.showMessageDialog(null, "El nombre de usuario, correo o contraseña ya está registrado. Por favor, elige otro.");
                    } else {
                        nuevoCliente.registrarUsuarioEnBaseDeDatos();
                        JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.");
                    }
                } catch (Throwable ex) {
                    JOptionPane.showMessageDialog(null, "Error al registrar cliente: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
        });

        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (txtNombreUsuario.getText().isEmpty() ||
                        String.valueOf(txtContrasenia.getPassword()).isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Por favor, completa los campos de Nombre de Usuario y Contraseña.");
                    return;
                }

                try {
                    String nombre = txtNombreUsuario.getText();
                    String contrasenia = String.valueOf(txtContrasenia.getPassword());

                    Cliente clienteAutenticado = new Cliente();
                    if (clienteAutenticado.verificarCredenciales(nombre, contrasenia)) {
                        MenuCliente menuCliente = new MenuCliente(clienteAutenticado);
                        JOptionPane.showMessageDialog(null, "Inicio de Sesión exitoso");
                        menuCliente.setVisible(true);

                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
                    }

                } catch (Throwable ex) {
                    JOptionPane.showMessageDialog(null, "Error al verificar las credenciales: " + ex.getMessage());
                    ex.printStackTrace();
                }

            }
        });

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaPrincipal().setVisible(true));
    }
}
