package TarFinal.logica;

import TarFinal.graphics.Ventana;
import java.time.LocalTime;

/**
 * Clase principal encargada de iniciar la aplicación.
 * Utiliza datos de prueba.
 * @author Daniel López
 * @version v1.1 - 6 de julio de 2026
 */
public class Main {
    public static void main(String[] args) {
        GestorReservas gestor = GestorReservas.getInstancia();

        Tutor tutor1 = new Tutor("t1", "Daniel López", "dalopez@udec.cl", 8000);
        Tutor tutor2 = new Tutor("t2", "Enrique Plan", "enripl@udec.cl", 5000);

        tutor1.agregarMateria("Matemáticas Discretas", 5);
        tutor1.agregarMateria("DOO", 15);
        tutor2.agregarMateria("Matemáticas Discretas", 5);

        tutor1.agregarDisponibilidad(new BloqueHorario("Lunes", LocalTime.of(12, 0), LocalTime.of(14, 0)));
        tutor1.agregarDisponibilidad(new BloqueHorario("Lunes", LocalTime.of(18, 0), LocalTime.of(20, 0)));
        tutor2.agregarDisponibilidad(new BloqueHorario("Viernes", LocalTime.of(15, 0), LocalTime.of(17, 0)));

        gestor.registrarTutor(tutor1);
        gestor.registrarTutor(tutor2);

        Estudiante est1 = new Estudiante("e1", "Nicolas Silva", "nicosilva@udec.cl");
        Estudiante est2 = new Estudiante("e2", "Eduardo Riveros", "eduriveros@udec.cl");
        Estudiante est3 = new Estudiante("e3", "John Tobias", "saibot@udec.cl");
        Estudiante est4 = new Estudiante("e4", "Ed Boon", "noob@udec.cl");
        Estudiante est5 = new Estudiante("e5", "John Cena", "jcena@udec.cl");
        Estudiante est6 = new Estudiante("e6", "Javiera Gonzales", "javigonz@udec.cl");

        gestor.registrarEstudiante(est1);
        gestor.registrarEstudiante(est2);
        gestor.registrarEstudiante(est3);
        gestor.registrarEstudiante(est4);
        gestor.registrarEstudiante(est5);
        gestor.registrarEstudiante(est6);

        new Ventana();
    }
}