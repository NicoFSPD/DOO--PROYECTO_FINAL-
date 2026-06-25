package TarFinal.controlador;

import TarFinal.logica.*;
import java.time.LocalTime;

public class ControladorReserva{

    private GestorReservas gestor;
    private ProxyTutorActual proxy;

    public ControladorReserva() {
        this.gestor = GestorReservas.getInstancia();
        this.proxy = ProxyTutorActual.getInstancia();
    }

    //metodo que se usa cuando se apreta el boton para agendar en la interfaz
    public boolean procesarNuevaReserva(String idReserva, Estudiante estudiante, Materia materia, String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        if (!proxy.hayTutorSeleccionado()) {
            System.out.println("error, se debe seleccionar un tutor antes de agendar una clase");
            return false;
        }

        Tutor tutorActual = proxy.getTutorSeleccionado();
        BloqueHorario nuevoHorario = new BloqueHorario(diaSemana, horaInicio, horaFin);
        return gestor.agendarClase(idReserva, estudiante, tutorActual, materia, nuevoHorario);
    }
}