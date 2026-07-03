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

    public boolean agendarClase(String idReserva, Estudiante estudiante, Tutor tutor, Materia materia, BloqueHorario horarioSolicitado) {
        for (Reserva r : reservas) {        //revisa que el tutor no tenga choque de horarios
            if (r.getTutor().getId().equals(tutor.getId())) {
                if (r.getHorario().choqueHorario(horarioSolicitado)) {
                    System.out.println("ERROR: El tutor " + tutor.getNombre() + " ya tiene una clase en ese horario.");
                    return false;
                }
            }
        }

        int inscritos = 0; //revision de cupos disponibles en la materia
        for (Reserva r : reservas){
            if (r.getTutor().getId().equals(tutor.getId()) && r.getMateria().getNombre().equalsIgnoreCase(materia.getNombre())){
                inscritos++;
            }
        }

        if (inscritos >= materia.getCupoMaximo()){ // revisa si se supero el limite
            System.out.println("error, no quedan cupos para " + materia.getNombre() + " con este tutor");
            return false;
        }

        Reserva nuevaReserva = new Reserva(idReserva, estudiante, tutor, materia, horarioSolicitado);   //crea la reserva
        reservas.add(nuevaReserva);
        System.out.println("Clase " + idReserva + " agendada con correctamente.");
        return true;
    }


    public boolean cancelarReserva(String idReserva){  //metodo para cancelar la reserva
        for (int i = 0; i < reservas.size(); i++){
            if (reservas.get(i).getIdReserva().equals(idReserva)){
                reservas.remove(i);
                System.out.println("Reserva " + idReserva + " cancelada");
                return true;
            }
        }
        System.out.println("error, no existe la reserva " + idReserva); //si no se encuenra el ID ingresado, entonces no se cancela nada y notifica
        return false;
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