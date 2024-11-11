import Mensajeria.Notificacion;
import Usuarios.Usuario;

public class Main {
    public static void main(String[] args) {

        Usuario usuario1 = new Usuario("LUISITO", "AA", "b.beroiza01@ufromail.cl", "admin");
        Notificacion notificacion = new Notificacion();
        Notificacion.EnviarCorreo envioCorreo = notificacion.new EnviarCorreo();
        String producto = "mantequilla";
        String costo = "$3";
        String lugarRetiro = "retiro";
        envioCorreo.enviarCorreo(usuario1, costo, producto, lugarRetiro);
    }
}
