import Usuarios.Usuario;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaUsuario extends JFrame {
    public VentanaUsuario (){
        super("Usuario");

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel etiquetaNombre = new JLabel("Nombre de Usuario:");
        JTextField campoNombre = new JTextField(20);

        JLabel etiquetaContrasenia = new JLabel("Contraseña:");
        JPasswordField campoContrasenia = new JPasswordField(20);

        JLabel etiquetaCorreo = new JLabel("Correo:");
        JTextField campoCorreo = new JTextField(20);

        JLabel etiquetaRol = new JLabel("Rol:");
        JTextField campoRol = new JTextField(20);

        JButton botonGuardar = new JButton("Guardar");
        botonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = campoNombre.getText();
                String contrasenia = campoContrasenia.getText();
                String correo = campoCorreo.getText();
                String rol = campoRol.getText();

                Usuario nuevoUsuario = new Usuario(nombre, contrasenia, correo, rol);
                JOptionPane.showMessageDialog(null, "Usuario guardado:\n" +
                        "Nombre: " + nuevoUsuario.getNombreUsuario() + "\n" +
                        "Correo: " + Usuario.getCorreo() + "\n" +
                        "Rol: " + nuevoUsuario.getRol());
            }
        }

    }

}
