package TarFinal.logica;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Tutor encargado de impartir clases particulares.
 * Contiene información sobre sus tarifas y agenda.
 * @author Daniel López
 * @version 1.1, 24/06/2026
 */

public class Tutor extends Usuario {
    private double tarifaPorHora;

    private List<Materia> materias; //mapa: "key" es el nombre de la materia y "value" es la cantidad de estudiantes máxima

    ///Constructor.
    public Tutor(String id, String nombre, String correo, double tarifaPorHora) {
        super(id, nombre, correo);
        this.materias = new ArrayList<>();
        this.tarifaPorHora = tarifaPorHora;
    }

    ///Método para añadir una materia.
    public void agregarMateria(String materia) {
        Materia nuevaMateria = new Materia(nombreMateria, cupoMaximo);
        this.materias.add(nuevaMateria);
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
