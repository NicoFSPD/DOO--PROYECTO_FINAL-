package TarFinal.controlador;

import TarFinal.logica.*;
import java.time.LocalTime;

/**
 * Clase encargada de funcionar como mediador (patrón Mediator) entre la interfaz gráfica y la
 * interfaz lógica.
 * @author Eduardo Riveros
 * @author Daniel López
 * @version v1.1 - 5 de julio de 2026
 */
public class ControladorReserva{

    private GestorReservas gestor;
    private GestorSeleccion proxy;

    ///Método constructor que inicializa las variables para trabajar con ellas
    public ControladorReserva() {
        this.gestor = GestorReservas.getInstancia();
        this.proxy = GestorSeleccion.getInstancia();
    }

    ///Método que se usa cuando se aprieta el botón para agendar en la interfaz
    public boolean procesarNuevaReserva(String idReserva, Estudiante estudiante, Materia materia, String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        if (!proxy.hayTutorSeleccionado()) {
            System.out.println("error, se debe seleccionar un tutor antes de agendar una clase");
            return false;
        }

        Tutor tutorActual = proxy.getTutorSeleccionado();
        BloqueHorario nuevoHorario = new BloqueHorario(diaSemana, horaInicio, horaFin);
        return gestor.agendarClase(idReserva, estudiante, tutorActual, materia, nuevoHorario);
    }

    ///Método utilizado para cancelar las reservas en la interfaz
    public boolean anularReserva(String id) {
        return gestor.cancelarReserva(id);
    }
}