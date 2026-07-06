package TarFinal.controlador;
import TarFinal.logica.Tutor;

/**
 * Clase que almacena el tutor seleccionado en la interfaz gráfica.
 * Utiliza el patrón de diseño Singleton para mantener esta información accesible
 * desde cualquier panel del sistema.
 * @author Eduardo Riveros
 * @version v1.0 - 5 de julio de 2026
 */
public class GestorSeleccion{
    private static GestorSeleccion instancia;
    private Tutor tutorSeleccionado;

    /// Constructor privado (Singleton)
    private GestorSeleccion(){ //constructor

    }
    /**
     * Si la instancia aún no ha sido creada, la crea por primera vez.
     * @return La instancia única de GestorSeleccion.
     */
    public static GestorSeleccion getInstancia(){
        if (instancia== null){
            instancia= new GestorSeleccion();
        }
        return instancia;
    }

    /**
     * Obtiene el tutor que el administrador seleccionó en la pantalla.
     * @return El objeto Tutor seleccionado o null si no se ha marcado ninguno todavía.
     */
    public Tutor getTutorSeleccionado(){
        return tutorSeleccionado;
    }
    /**
     * Se encarga de guardar el tutor que se eligió en la interfaz para usarlo al crear la reserva.
     * @param tutorSeleccionado El Tutor que se hizo clic en el panel correspondiente.
     */
    public void setTutorSeleccionado(Tutor tutorSeleccionado){
        this.tutorSeleccionado = tutorSeleccionado;
    }
    /**
     * Revisa si el administrador ya eligió a un tutor en la pantalla.
     * @return true si ya hay un tutor seleccionado, false si no se ha elegido ninguno.
     */
    public boolean hayTutorSeleccionado(){
        return this.tutorSeleccionado!= null;
    }
}