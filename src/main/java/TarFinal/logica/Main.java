package TarFinal.logica;

import TarFinal.graphics.Ventana;
import TarFinal.logica.Estudiante;
import TarFinal.logica.GestorReservas;
import TarFinal.logica.Tutor;

/**
 * Clase principal encargada de iniciar la aplicación.
 * Utiliza datos de prueba.
 * @author Daniel López
 * @version 1.0
 */
public class Main {
    public static void main(String[] args) {
        GestorReservas gestor = GestorReservas.getInstancia();
        Tutor tutor1 = new Tutor("t1", "Daniel López", "dalopez@udec.cl", 8000);
        Tutor tutor2 = new Tutor("t2", "Enrique Plan", "enripl@udec.cl", 5000);
        gestor.registrarTutor(tutor1);
        gestor.registrarTutor(tutor2);
        Estudiante est1 = new Estudiante("e1", "Nicolas Silva", "nicosilva@udec.cl");
        Estudiante est2 = new Estudiante("e2", "Eduardo Riveros", "eduriveros@udec.cl");
        gestor.registrarEstudiante(est1);
        gestor.registrarEstudiante(est2);
        new Ventana();
    }
}