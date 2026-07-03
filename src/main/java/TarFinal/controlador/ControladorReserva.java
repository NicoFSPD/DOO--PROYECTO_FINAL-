package TarFinal.controlador;

import TarFinal.logica.*;
import java.time.LocalTime;

public class ControladorReserva{

    private GestorReservas gestor;
    private GestorSeleccion proxy;

    public ControladorReserva() {
        this.gestor = GestorReservas.getInstancia();
        this.GestorSeleccion = GestorSeleccion.getInstancia();
    }

    //metodo que se usa cuando se apreta el boton para agendar en la interfaz
    public boolean procesarNuevaReserva(String idReserva, Estudiante estudiante, Materia materia, String diaSemana, LocalTime horaInicio, LocalTime horaFin) {
        if (!gestorSeleccion.hayTutorSeleccionado()) {
            System.out.println("error, se debe seleccionar un tutor antes de agendar una clase");
            return false;
        }

        Tutor tutorActual = gestorSeleccion.getTutorSeleccionado();
        BloqueHorario nuevoHorario = new BloqueHorario(diaSemana, horaInicio, horaFin);
        return gestor.agendarClase(idReserva, estudiante, tutorActual, materia, nuevoHorario);
    }
}