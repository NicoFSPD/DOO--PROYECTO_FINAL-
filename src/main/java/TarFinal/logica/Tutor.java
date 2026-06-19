package TarFinal.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Tutor encargado de impartir clases particulares.
 * Contiene información sobre sus tarifas y agenda.
 * @author Daniel López
 * @version 1.0, 19/06/2026
 */

public class Tutor extends Usuario {
    private double tarifaPorHora;
    private List<String> materias;
    ///Constructor.
    public Tutor(String id, String nombre, String correo) {
        super(id, nombre, correo);
        this.materias = new ArrayList<>();
        this.tarifaPorHora = tarifaPorHora;
    }

    ///Método para añadir una materia.
    public void agregarMateria(String materia) {
        this.materias.add(materia);
    }

    ///Getters y setters.
    public double getTarifaPorHora() {
        return tarifaPorHora;
    }
    public void setTarifaPorHora(double tarifaPorHora) {
        this.tarifaPorHora = tarifaPorHora;
    }
    public List<String> getMaterias() {
        return materias;
    }
}
