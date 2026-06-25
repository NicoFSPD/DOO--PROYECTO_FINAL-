package TarFinal.logica;

import java.util.ArrayList;
import java.util.List;

public class GestorReservas{
    private static GestorReservas instancia;    //patron de diseño singleton

    private List<Tutor> tutores;
    private List<Estudiante> estudiantes;
    private List<Reserva> reservas;

    // El constructor es privado para que nadie más pueda hacer un "new GestorReservas()"
    private GestorReservas(){ //constructor
        this.tutores= new ArrayList<>();
        this.estudiantes= new ArrayList<>();
        this.reservas= new ArrayList<>();
    }
    
    public static GestorReservas getInstancia(){
        if (instancia == null) {
            instancia = new GestorReservas();
        }
        return instancia;
    }

    public void registrarTutor(Tutor tutor){
        tutores.add(tutor);
    }

    public void registrarEstudiante(Estudiante estudiante){
        estudiantes.add(estudiante);
    }


    public boolean agendarClase(String idReserva, Estudiante estudiante, Tutor tutor, Materia materia, BloqueHorario horarioSolicitado) {   //metodo para validar y reservar
        for (Reserva reservaExistente : reservas){  //valida el horario disp para el tutor en cuestion
            if (reservaExistente.getTutor().getId().equals(tutor.getId())){
                if (reservaExistente.getHorario().choqueHorario(horarioSolicitado)){
                    System.out.println("ERROR: no se pudo agendar la clase ya que el tutor " + tutor.getNombre() + " ya tiene una clase en ese horario");
                    return false;
                }
            }
        }

        Reserva nuevaReserva = new Reserva(idReserva, estudiante, tutor, materia, horarioSolicitado);   //si pasa lo anterior es pq el horari oestá disp
        reservas.add(nuevaReserva);
        System.out.println("Clase " + idReserva + " agendada correctamente");
        return true;
    }


    public List<Reserva> getReservas(){
        return reservas;
    }

    public List<Tutor> getTutores(){
        return tutores;
    }

    public List<Estudiante> getEstudiantes(){
        return estudiantes;
    }
}