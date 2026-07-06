package TarFinal.controlador;
import TarFinal.logica.Estudiante;
import TarFinal.logica.Materia;
import TarFinal.logica.Tutor;
import java.time.LocalTime;

/**
 * Clase que almacena de forma temporal los datos que el administrador va seleccionando en la pantalla.
 * Utiliza el patrón de diseño Singleton para mantener esta información accesible
 * desde cualquier panel del sistema.
 * @author Eduardo Riveros
 * @author Daniel López
 * @version v1.1 - 5 de julio de 2026
 */
public class GestorSeleccion{
    private static GestorSeleccion instancia;
    private Tutor tutorSeleccionado;
    private String diaSeleccionado;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private Estudiante estudianteSeleccionado;
    private Materia materiaSeleccionada;

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
     * Revisa si el administrador ya eligió a un tutor en la pantalla.
     * @return true si ya hay un tutor seleccionado, false si no se ha elegido ninguno.
     */
    public boolean hayTutorSeleccionado(){
        return this.tutorSeleccionado!= null;
    }
    /**
     * Revisa si ya se seleccionó un día en el calendario.
     * @return true si ya hay un día seleccionado, false si está vacío.
     */
    public boolean hayDiaSeleccionado() {
        return this.diaSeleccionado != null;
    }
    /**
     * Revisa si ya se seleccionó un bloque de horas.
     * @return true si ya se definieron la hora de inicio y fin, false si falta alguna.
     */
    public boolean hayHorarioSeleccionado() {
        return this.horaInicio != null && this.horaFin != null;
    }
    /**
     * Revisa si ya se seleccionó un estudiante para la clase.
     * @return true si ya hay un estudiante seleccionado, false si está vacío.
     */
    public boolean hayEstudianteSeleccionado() {
        return this.estudianteSeleccionado != null;
    }
    /**
     * Revisa si ya se seleccionó la materia que se va a impartir.
     * @return true si ya hay una materia seleccionada, false si está vacío.
     */
    public boolean hayMateriaSeleccionada() {
        return this.materiaSeleccionada != null;
    }

    /// Getters y setters.
    public Tutor getTutorSeleccionado(){
        return tutorSeleccionado;
    }
    public void setTutorSeleccionado(Tutor tutorSeleccionado){
        this.tutorSeleccionado = tutorSeleccionado;
    }
    public String getDiaSeleccionado() {
        return diaSeleccionado;
    }

    public void setDiaSeleccionado(String diaSeleccionado) {
        this.diaSeleccionado = diaSeleccionado;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public Estudiante getEstudianteSeleccionado() {
        return estudianteSeleccionado;
    }

    public void setEstudianteSeleccionado(Estudiante estudianteSeleccionado) {
        this.estudianteSeleccionado = estudianteSeleccionado;
    }

    public Materia getMateriaSeleccionada() {
        return materiaSeleccionada;
    }

    public void setMateriaSeleccionada(Materia materiaSeleccionada) {
        this.materiaSeleccionada = materiaSeleccionada;
    }
}