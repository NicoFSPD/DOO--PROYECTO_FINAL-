package TarFinal.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Tutor encargado de impartir clases particulares.
 * Contiene información sobre sus tarifas y agenda.
 * @author Daniel López
 * @version v1.2, 6 de julio de 2026
 */
public class Tutor extends Usuario {
    private double tarifaPorHora;
    private List<Materia> materias;
    private List<BloqueHorario> disponibilidad;

    /**
     * Constructor de la clase a partir de la superclase Usuario.
     * Inicializa las listas de materias o horarios vacías.
     */
    public Tutor(String id, String nombre, String correo, double tarifaPorHora) {
        super(id, nombre, correo);
        this.materias = new ArrayList<>();
        this.tarifaPorHora = tarifaPorHora;
        this.disponibilidad = new ArrayList<>();
    }

    /**
     * Añade una nueva materia a la lista de cursos que este profesor puede dictar.
     * @param nombreMateria Nombre de la asignatura.
     * @param cupoMaximo Cantidad máxima de alumnos permitidos en la tutoría.
     */
    public void agregarMateria(String nombreMateria, int cupoMaximo) {
        Materia nuevaMateria = new Materia(nombreMateria, cupoMaximo);
        this.materias.add(nuevaMateria);
    }

    /**
     * Añade un bloque de día y horas en el que el tutor declara estar disponible.
     * @param bloque El día de la semana junto con la hora de inicio y fin.
     */
    public void agregarDisponibilidad(BloqueHorario bloque) {
        this.disponibilidad.add(bloque);
    }

    /**
     * Verifica si el tutor tiene disponibilidad para cubrir un bloque de horario específico.
     * @param horarioSolicitado El bloque de horario que se desea consultar.
     * @return true si el horario solicitado está dentro de los bloques de disponibilidad del tutor, false en caso contrario.
     */
    public boolean tieneDisponibilidad(BloqueHorario horarioSolicitado) {
        String[] nombresDias = {"Domingo", "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado"};

        int numDiaSolicitado = -1;
        try {
            numDiaSolicitado = Integer.parseInt(horarioSolicitado.getDiaSemana());
        } catch (NumberFormatException e) {
        }
        String diaSemanaSolicitado = (numDiaSolicitado != -1) ? nombresDias[numDiaSolicitado % 7] : horarioSolicitado.getDiaSemana();

        for (BloqueHorario bloque : disponibilidad) {
            int numDiaBloque = -1;
            try {
                numDiaBloque = Integer.parseInt(bloque.getDiaSemana());
            } catch (NumberFormatException e) {
            }
            String diaSemanaBloque = (numDiaBloque != -1) ? nombresDias[numDiaBloque % 7] : bloque.getDiaSemana();

            if (diaSemanaBloque.equalsIgnoreCase(diaSemanaSolicitado)) {
                if ((horarioSolicitado.getHoraInicio().equals(bloque.getHoraInicio()) || horarioSolicitado.getHoraInicio().isAfter(bloque.getHoraInicio())) &&
                        (horarioSolicitado.getHoraFin().equals(bloque.getHoraFin()) || horarioSolicitado.getHoraFin().isBefore(bloque.getHoraFin()))) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Verifica si el tutor está registrado para impartir una materia en particular.
     * @param materia La materia que se desea verificar.
     * @return true si la materia se encuentra en la lista de materias que imparte el tutor, false en caso contrario.
     */
    public boolean dictaMateria(Materia materia) {
        for (Materia m : materias) {
            if (m.getNombre().equalsIgnoreCase(materia.getNombre())) {
                return true;
            }
        }
        return false;
    }

    /// Getters y setters.
    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }

    public List<Materia> getMaterias() {
        return materias;
    }

    public List<BloqueHorario> getDisponibilidad() {
        return disponibilidad;
    }
}
