package TarFinal.logica;

import java.time.LocalTime;


public class BloqueHorario {        //permite ver la disponibilidad de clases en un tiempo especifico
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime  horaFin;

    public BloqueHorario(String diaSemana, LocalTime horaInicio, LocalTime horaFin){    //constructor
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }



    public boolean choqueHorario(BloqueHorario otro){   //revisa si hay un chque horario en el bloque horario correspondiente
                                                        //si si, entonces retorna true
        if (!this.diaSemana.equalsIgnoreCase(otro.getDiaSemana())){
            return false;
        }


        return this.horaInicio.isBefore((otro.getHoraFin())) && otro.getHoraInicio().isBefore(this.horaFin); // (inicio1<fin2) && (inicio2<fin1)
    }

    public String getDiaSemana(){
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana){
        this.diaSemana = diaSemana;
    }

    public LocalTime gettHoraInicio(){
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio){
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin(){
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin){
        this.horaFin = horaFin;
    }
}
