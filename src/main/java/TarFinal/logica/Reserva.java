package TarFinal.logica;

public class Reserva {
    private String idReserva;
    private Estudiante estudiante;
    private Tutor tutor;
    private Materia materia;
    private BloqueHorario horario;

    public Reserva(String idReserva, Estudiante estudiante, Tutor tutor, Materia materia, BloqueHorario horario){
        this.idReserva= idReserva;
        this.estudiante = estudiante;
        this.tutor = tutor;
        this.materia = materia;
        this.horario = horario;
    }

    public String getIdReserva(){
        return idReserva;
    }

    public void setIdReserva(String idReserva){
        this.idReserva= idReserva;
    }

    public Estudiante getEstudiante(){
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante){
        this.estudiante= estudiante;
    }

    public Tutor getTutor(){
        return tutor;
    }

    public void setTutor(Tutor tutor){
        this.tutor= tutor;
    }

    public Materia getMateria(){
        return materia;
    }

    public void setMateria(Materia materia){
        this.materia= materia;
    }

    public BloqueHorario getHorario(){
        return horario;
    }

    public void setHorario(BloqueHorario horario){
        this.horario= horario;
    }
}
