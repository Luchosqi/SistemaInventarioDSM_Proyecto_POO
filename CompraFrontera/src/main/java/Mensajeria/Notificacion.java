package Mensajeria;
import Usuarios.*;
import Cartel.*;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Notificacion {

    public class EnviarCorreo {
        private String servidorSMTP = "smtp.gmail.com";
        private final String remitente = "fronteracompra@gmail.com";
        private final String contrasenaRemitente = "zzqu zyym uiya kwhv";

        public void enviarCorreo(Usuario usuario1, String costo, String producto, String lugarRetiro) {
            Properties propiedades = new Properties();
            propiedades.put("mail.smtp.host", servidorSMTP);
            propiedades.put("mail.smtp.port", "587");
            propiedades.put("mail.smtp.auth", "true");
            propiedades.put("mail.smtp.starttls.enable", "true");

            // Autenticación de la sesión
            Session sesion = Session.getInstance(propiedades, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(remitente, contrasenaRemitente);
                }
            });

            // Cuerpo del mensaje
            String cuerpo = "Estimado/a " + usuario1.getNombreUsuario() + " (" + usuario1.getCorreo() + ")\n\n"
                    + "¡Gracias por realizar tu compra con nosotros!\n"
                    + "Detalles de la Compra:\n"
                    + "- Producto: " + producto + "\n"
                    + "- Costo Total: " + costo + "\n"
                    + "Tu pedido está disponible para retiro en " + lugarRetiro
                    + ". Por favor, lleva una copia de este correo.\n\n"
                    + "Atentamente,\n"
                    + "El equipo de CompraFrontera";

            try {
                MimeMessage mensaje = new MimeMessage(sesion);
                mensaje.setFrom(new InternetAddress(remitente));
                mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario1.getCorreo()));
                mensaje.setSubject("¡Gracias por tu compra en CompraFrontera!");
                mensaje.setText(cuerpo);

                // Envío del correo
                Transport.send(mensaje);
                System.out.println("Correo enviado correctamente.");
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
