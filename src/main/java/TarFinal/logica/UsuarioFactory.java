package TarFinal.logica;

public class UsuarioFactory {

    public static Usuario crearUsuario(String tipo, String id, String nombre, String correo, double tarifa) {
        if (tipo.equalsIgnoreCase("Administrador")) {
            return new Administrador(id, nombre, correo);
        } else if (tipo.equalsIgnoreCase("Estudiante")) {
            return new Estudiante(id, nombre, correo);
        } else if (tipo.equalsIgnoreCase("Tutor")) {
            return new Tutor(id, nombre, correo, tarifa);
        }
        return null;
    }
}
