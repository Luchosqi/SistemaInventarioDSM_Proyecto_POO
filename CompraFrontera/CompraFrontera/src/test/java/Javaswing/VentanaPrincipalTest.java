package Javaswing;

import org.junit.jupiter.api.Test;

import javax.swing.*;



class VentanaPrincipalTest {


    @Test
    void testRegistrarButton() {
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();

        ventanaPrincipal.txtNombreUsuario.setText("usuarioPrueba");
        ventanaPrincipal.txtContrasenia.setText("1234");
        ventanaPrincipal.txtCorreo.setText("correofalso1@gmail.com");
        ventanaPrincipal.txtSaldo.setText("10000");
        ventanaPrincipal.btnRegistrar.doClick();

        JOptionPane.showMessageDialog(null, "Test realizado, si cliente ya existe, cambiar parametros");
    }
}