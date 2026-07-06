package TarFinal.controlador;

import TarFinal.logica.*;
import java.time.LocalTime;

/**
 * Clase encargada de funcionar como mediador (patrón Mediator) entre la interfaz gráfica y la
 * interfaz lógica.
 * @author Eduardo Riveros
 * @author Daniel López
 * @version v1.2 - 5 de julio de 2026
 */
public class ControladorReserva {

    private GestorReservas gestor;
    private GestorSeleccion proxy;

    /// Método constructor que inicializa las variables para trabajar con ellas
    public ControladorReserva() {
        this.gestor = GestorReservas.getInstancia();
        this.proxy = GestorSeleccion.getInstancia();
    }

    /// Método que se usa cuando se aprieta el botón para agendar en la interfaz
    public boolean procesarNuevaReserva(String idReserva, Estudiante estudiante, Materia materia, String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        if (!proxy.hayTutorSeleccionado()) {
            throw new IllegalStateException("Se debe seleccionar un tutor antes de agendar una clase");
        }
        BloqueHorario nuevoHorario = new BloqueHorario(diaSemana, horaInicio, horaFin);
        return gestor.agendarClase(idReserva, estudiante, proxy.getTutorSeleccionado(), materia, nuevoHorario);
    }

    /// Método utilizado para cancelar las reservas en la interfaz
    public boolean anularReserva(String id) {
        return gestor.cancelarReserva(id);
    }

    /// Método utilizado para modificar el horario de una reserva existente
    public boolean modificarReserva(String idReserva, String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        BloqueHorario nuevoHorario = new BloqueHorario(diaSemana, horaInicio, horaFin);
        return gestor.modificarReserva(idReserva, nuevoHorario);
    }
}