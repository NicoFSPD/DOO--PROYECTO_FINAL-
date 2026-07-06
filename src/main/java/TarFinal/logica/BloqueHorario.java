package TarFinal.logica;

import java.time.LocalTime;

/**
 * Se usa para definir un momento específico en la agenda, guardando el día
 * de la semana, la hora en que empieza la clase y la hora en que termina.
 * @author Eduardo Riveros
 * @version v1.0, 24 de junio de 2026
 */
public class BloqueHorario {
    private String diaSemana;
    private LocalTime horaInicio;
    private LocalTime  horaFin;

    /// Constructor.
    public BloqueHorario(String diaSemana, LocalTime horaInicio, LocalTime horaFin){
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }


    /**
     * Revisa si hay un choque horario en el bloque horario correspondiente
     * @param otro el bloque horario con el que se quiere comparar.
     * @return true si hay un choque.
     */
    public boolean choqueHorario(BloqueHorario otro){
        if (!this.diaSemana.equalsIgnoreCase(otro.getDiaSemana())){
            return false;
        }
        return this.horaInicio.isBefore((otro.getHoraFin())) && otro.getHoraInicio().isBefore(this.horaFin); // (inicio1<fin2) && (inicio2<fin1)
    }

    /// Getters y setters.
    public String getDiaSemana(){
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana){
        this.diaSemana = diaSemana;
    }

    public LocalTime getHoraInicio(){
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
