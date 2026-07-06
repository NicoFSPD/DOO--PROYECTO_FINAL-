package TarFinal.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar las reservas (núcleo principal del programa).
 * Tiene la responsabilidad de guardar las listas de profesores, alumnos y reservas, además de hacer cumplir todas las
 * reglas de negocio (que no choquen los horarios, que queden cupos, etc.).
 * @author Eduardo Riveros
 * @author Daniel López
 * @version v1.2, 6 de julio de 2026
 */
public class GestorReservas {
    /// Instancia única global de la clase (patrón Singleton).
    private static GestorReservas instancia;

    /// Listas internas que guardan los datos del programa.
    private List<Tutor> tutores;
    private List<Estudiante> estudiantes;
    private List<Reserva> reservas;

    /// Lista de pantallas interesadas en enterarse cuando cambien los datos (patrón Observer).
    private List<ObservadorReservas> observadores = new ArrayList<>();

    /**
     * Constructor privado, nadie fuera de esta clase puede usar un new GestorReservas().
     * Esto asegura que las listas solo se creen una única vez en todo el programa.
     */
    private GestorReservas() {
        this.tutores = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
        this.reservas = new ArrayList<>();
    }

    /**
     * Método público para obtener la instancia única. Si no se ha creado antes, la crea.
     * Si ya existía, devuelve la que ya estaba guardada.
     * @return La instancia única de GestorReservas.
     */
    public static GestorReservas getInstancia() {
        if (instancia == null) {
            instancia = new GestorReservas();
        }
        return instancia;
    }

    /**
     * Registra un nuevo profesor en el sistema.
     * @param tutor El profesor a agregar.
     */
    public void registrarTutor(Tutor tutor) {
        tutores.add(tutor);
    }

    /**
     * Registra un nuevo alumno en el sistema.
     * @param estudiante El alumno a agregar.
     */
    public void registrarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    /**
     * Intenta agendar una nueva clase. Revisa paso a paso que se cumplan todas las reglas:
     * 1) Que el ID de la reserva no esté repetido.
     * 2) Que el tutor trabaje en ese horario.
     * 3) Que el tutor realmente enseñe esa materia.
     * 4) Que el alumno no tenga otra tutoría al mismo tiempo.
     * 5) Que el tutor no esté ocupado con otra materia diferente a esa hora.
     * 6) Que queden cupos libres para la clase.
     * Si todo está en orden, guarda la clase y le avisa automáticamente al calendario.
     */
    public boolean agendarReserva(String idReserva, Estudiante estudiante, Tutor tutor, Materia materia, BloqueHorario horarioSolicitado) {
        for (Reserva r : reservas) {
            if (r.getIdReserva().equals(idReserva)) {
                throw new IllegalArgumentException("Ya existe una reserva guardada con el ID: " + idReserva);
            }
        }

        if (!tutor.tieneDisponibilidad(horarioSolicitado)) {
            throw new IllegalStateException("El tutor no está disponible en el horario solicitado.");
        }

        Materia materiaReal = null;
        for (Materia m : tutor.getMaterias()) {
            if (m.getNombre().equalsIgnoreCase(materia.getNombre())) {
                materiaReal = m;
                break;
            }
        }

        if (materiaReal == null) {
            throw new IllegalStateException("El tutor seleccionado no imparte la materia: " + materia.getNombre());
        }

        for (Reserva r : reservas) {
            if (r.getEstudiante().getId().equals(estudiante.getId()) && r.getHorario().choqueHorario(horarioSolicitado)) {
                throw new IllegalStateException("El estudiante ya tiene otra tutoria agendada en este horario.");
            }
        }

        int inscritosEnBloque = 0;
        for (Reserva r : reservas) {
            if (r.getTutor().getId().equals(tutor.getId()) && r.getHorario().choqueHorario(horarioSolicitado)) {
                if (!r.getMateria().getNombre().equalsIgnoreCase(materiaReal.getNombre())) {
                    throw new IllegalStateException("El tutor ya tiene una clase de otra materia en este horario.");
                }
                inscritosEnBloque++;
            }
        }

        if (inscritosEnBloque >= materiaReal.getCupoMaximo()) {
            throw new IllegalStateException("No quedan cupos para " + materiaReal.getNombre() + " en este horario.");
        }

        Reserva nuevaReserva = new Reserva(idReserva, estudiante, tutor, materiaReal, horarioSolicitado);
        reservas.add(nuevaReserva);
        notificarObservadores();
        return true;
    }

    /**
     * Busca una reserva por su ID y la elimina del sistema.
     * Al terminar, le avisa a los observadores (calendario) para actualizar los colores.
     */
    public boolean cancelarReserva(String idReserva) {
        for (int i = 0; i < reservas.size(); i++) {
            if (reservas.get(i).getIdReserva().equals(idReserva)) {
                reservas.remove(i);
                notificarObservadores();
                return true;
            }
        }
        throw new IllegalArgumentException("No existe la reserva indicada.");
    }


    /**
     * Modifica el horario de una clase que ya existe. Revisa que el nuevo horario
     * no sea idéntico al que ya tiene y comprueba que el profesor no tenga otro
     * choque de horario debido a este cambio.
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

        if (reservaEncontrada.getHorario().getDiaSemana().equals(nuevoHorario.getDiaSemana()) &&
                reservaEncontrada.getHorario().getHoraInicio().equals(nuevoHorario.getHoraInicio()) &&
                reservaEncontrada.getHorario().getHoraFin().equals(nuevoHorario.getHoraFin())) {
            throw new IllegalStateException("El nuevo horario es idéntico al actual. No se realizó ningún cambio.");
        }

        for (Reserva r : reservas) {
            if (!r.getIdReserva().equals(idReserva) && r.getTutor().getId().equals(reservaEncontrada.getTutor().getId())) {
                if (r.getHorario().choqueHorario(nuevoHorario)) {
                    throw new IllegalStateException("Horario no disponible por choque horario del tutor.");
                }
            }
        }

        reservaEncontrada.setHorario(nuevoHorario);
        notificarObservadores();
        return true;
    }

    /**
     * Busca y devuelve una lista filtrada con todas las clases asociadas a un tutor específico.
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
     * Busca y devuelve una lista filtrada con todas las clases pertenecientes a un estudiante específico.
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

    /**
     * Añade un panel interesado a la lista de observadores (por ejemplo el PanelCalendario).
     */
    public void agregarObservador(ObservadorReservas o) {
        observadores.add(o);
    }

    /**
     * Recorre todos los paneles agregados en la lista y llama a su método actualizar().
     * Esto hace que las pantallas hagan refresh automáticamente al haber cualquier cambio.
     */
    private void notificarObservadores() {
        for (ObservadorReservas o : observadores) {
            o.actualizar();
        }
    }

    /// Getters de listas.
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
     * Borra por completo toda la información del sistema (pruebas unitarias).
     */
    public void limpiarDatos() {
        tutores.clear();
        estudiantes.clear();
        reservas.clear();
    }
}