package Mensajeria;

import Usuarios.Cliente;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Notificacion {

    private String servidorSMTP = "smtp.gmail.com";
    private final String remitente = "fronteracompra@gmail.com";
    private final String contrasenaRemitente = "zzqu zyym uiya kwhv";

    public void enviarCorreoPedido(Pedido pedido) {
        Cliente cliente = pedido.getCliente();

        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", servidorSMTP);
        propiedades.put("mail.smtp.port", "587");
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true");

        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, contrasenaRemitente);
            }
        });


        String asunto = "Detalles de tu Pedido en CompraFrontera";
        String cuerpo = "Estimado/a " + cliente.getNombreUsuario() + ",\n\n"
                + "¡Gracias por realizar tu compra con nosotros!\n"
                + "Aquí tienes los detalles de tu pedido:\n\n"
                + pedido.generarDetalles()
                + "\nAtentamente,\n"
                + "El equipo de CompraFrontera";

        try {
            MimeMessage mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.addRecipient(Message.RecipientType.TO, new InternetAddress(cliente.getCorreo()));
            mensaje.setSubject(asunto);
            mensaje.setText(cuerpo);

            Transport.send(mensaje);
            System.out.println("Correo enviado correctamente a " + cliente.getCorreo());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
