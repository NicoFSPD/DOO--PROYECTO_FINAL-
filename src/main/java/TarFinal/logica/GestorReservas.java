package TarFinal.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestor central que administra las reservas, tutores y estudiantes.
 * Implementa el patrón Singleton.
 * @author Eduardo Riveros
 * @author Daniel López
 * @version v1.1 - 6 de julio de 2026
 */
public class GestorReservas {
    private static GestorReservas instancia;
    private List<Tutor> tutores;
    private List<Estudiante> estudiantes;
    private List<Reserva> reservas;

    /**
     * Constructor privado para asegurar que solo exista un gestor en todo el programa.
     */
    private GestorReservas() {
        this.tutores = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    /**
     * Devuelve la instancia única del gestor. Si no existe, la crea.
     * @return La copia única de GestorReservas.
     */
    public static GestorReservas getInstancia() {
        if (instancia == null) {
            instancia = new GestorReservas();
        }
        return instancia;
    }

    /**
     * Guarda un nuevo tutor en la lista del sistema.
     * @param tutor El tutor a registrar.
     */
    public void registrarTutor(Tutor tutor) {
        tutores.add(tutor);
    }

    /**
     * Guarda un nuevo estudiante en la lista del sistema.
     * @param estudiante El estudiante a registrar.
     */
    public void registrarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    /**
     * Crea y guarda una nueva reserva en el sistema.
     * Revisa que el tutor no tenga otra materia en ese mismo horario y que no se supere el cupo máximo de alumnos.
     * Ahora lanza excepciones en caso de error para facilitar el manejo en las pruebas unitarias.
     * @param idReserva El identificador de la reserva.
     * @param estudiante El estudiante que toma la clase.
     * @param tutor El tutor que dicta la clase.
     * @param materia La materia a impartir.
     * @param horarioSolicitado El bloque de horas y día de la clase.
     * @return true si la reserva se crea exitosamente.
     */
    public boolean agendarClase(String idReserva, Estudiante estudiante, Tutor tutor, Materia materia, BloqueHorario horarioSolicitado) {
        int inscritosEnBloque = 0;

        for (Reserva r : reservas) {
            if (r.getTutor().getId().equals(tutor.getId()) && r.getHorario().choqueHorario(horarioSolicitado)) {
                if (!r.getMateria().getNombre().equalsIgnoreCase(materia.getNombre())) {
                    throw new IllegalStateException("El tutor ya tiene una clase de otra materia en este horario.");
                }
                inscritosEnBloque++;
            }
        }

        if (inscritosEnBloque >= materia.getCupoMaximo()) {
            throw new IllegalStateException("No quedan cupos para " + materia.getNombre() + " en este horario.");
        }

        Reserva nuevaReserva = new Reserva(idReserva, estudiante, tutor, materia, horarioSolicitado);
        reservas.add(nuevaReserva);
        return true;
    }

    /**
     * Elimina una reserva del sistema usando su identificador.
     * Si no encuentra el ID ingresado, lanza una excepción en lugar de solo imprimir un error.
     * @param idReserva El identificador de la reserva a borrar.
     * @return true si se elimina correctamente.
     */
    public boolean cancelarReserva(String idReserva) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getIdReserva().equals(idReserva)) {
                reservas.remove(i);
                return true;
            }
        }
        throw new IllegalArgumentException("No existe la reserva indicada.");
    }

    /**
     * Cambia el bloque horario de una reserva que ya existe.
     * Valída que la reserva exista y que el nuevo horario no choque con otras clases del tutor.
     * Utiliza excepciones para notificar si hay errores, mejorando la integración con los tests.
     * @param idReserva El identificador de la reserva a cambiar.
     * @param nuevoHorario El nuevo bloque de día y horas.
     * @return true si se actualiza con éxito.
     */
    public boolean modificarReserva(String idReserva, BloqueHorario nuevoHorario) {
        Reserva reservaEncontrada = null;
        for (Reserva r : reservas) {
            if (r.getIdReserva().equals(idReserva)) {
                reservaEncontrada = r;
                break;
            }
        }

        if (reservaEncontrada == null) {
            throw new IllegalArgumentException("La reserva no existe.");
        }

        for (Reserva r : reservas) {
            if (!r.getIdReserva().equals(idReserva) && r.getTutor().getId().equals(reservaEncontrada.getTutor().getId())) {
                if (r.getHorario().choqueHorario(nuevoHorario)) {
                    throw new IllegalStateException("Horario no disponible por choque horario del tutor.");
                }
            }
        }

        reservaEncontrada.setHorario(nuevoHorario);
        return true;
    }

    /**
     * Busca y devuelve todas las clases que tiene agendadas un profesor específico.
     * @param idTutor El identificador del tutor.
     * @return Una lista con las reservas de ese tutor.
     */
    public List<Reserva> obtenerReservasPorTutor(String idTutor) {
        List<Reserva> filtradas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getTutor().getId().equals(idTutor)) {
                filtradas.add(r);
            }
        }
        return filtradas;
    }

    /**
     * Busca y devuelve todas las clases que tiene agendadas un alumno específico.
     * @param idEstudiante El identificador del estudiante.
     * @return Una lista con las reservas de ese estudiante.
     */
    public List<Reserva> obtenerReservasPorEstudiante(String idEstudiante) {
        List<Reserva> filtradas = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.getEstudiante().getId().equals(idEstudiante)) {
                filtradas.add(r);
            }
        }
        return filtradas;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    public List<Tutor> getTutores() {
        return tutores;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    /**
     * Vacía completamente las listas de tutores, estudiantes y reservas.
     * Creado específicamente para reiniciar el sistema antes de cada prueba unitaria.
     */
    public void limpiarDatos() {
        tutores.clear();
        estudiantes.clear();
        reservas.clear();
    }
}