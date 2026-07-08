import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.awt.Color;
import java.awt.Component;
import java.time.LocalTime;
import javax.swing.JButton;
import javax.swing.JPanel;
import static org.junit.jupiter.api.Assertions.*;

import TarFinal.logica.*;
import TarFinal.controlador.*;
import TarFinal.graphics.*;

/**
 * Clase para realización de pruebas de control de calidad del código.
 * @author Nicolas Silva
 * @author Daniel López
 * @version v1.1 - 8 de julio de 2026
 */

class TutoriasTest {

    private GestorReservas gestor;
    private GestorSeleccion seleccion;
    private ControladorReserva controlador;

    @BeforeEach
    void setUp() {
        /// Reiniciar el estado del Singleton antes de cada prueba
        gestor = GestorReservas.getInstancia();
        gestor.limpiarDatos();

        seleccion = GestorSeleccion.getInstancia();
        seleccion.setTutorSeleccionado(null);
        seleccion.setEstudianteSeleccionado(null);
        seleccion.setMateriaSeleccionada(null);
        seleccion.setDiaSeleccionado(null);
        seleccion.setHoraInicio(null);
        seleccion.setHoraFin(null);

        controlador = new ControladorReserva();
    }

    /// Pruebas de lógica

    @Test
    void bloqueHorarioChoqueLimitesExactos() {
        BloqueHorario b1 = new BloqueHorario("Lunes", LocalTime.of(12, 0), LocalTime.of(14, 0));
        BloqueHorario b2 = new BloqueHorario("Lunes", LocalTime.of(14, 0), LocalTime.of(16, 0));
        assertFalse(b1.choqueHorario(b2), "Bloques adyacentes no deben chocar");
    }

    @Test
    void bloqueHorarioChoqueDiasDiferentes() {
        BloqueHorario b1 = new BloqueHorario("Lunes", LocalTime.of(12, 0), LocalTime.of(14, 0));
        BloqueHorario b2 = new BloqueHorario("Martes", LocalTime.of(12, 0), LocalTime.of(14, 0));
        assertFalse(b1.choqueHorario(b2), "Misma hora en distintos días no debe chocar");
    }

    @Test
    void tutorDisponibilidadConNumeroDeDia() {
        Tutor t = new Tutor("T1", "Enrique", "e@udec.cl", 1000);
        t.agregarDisponibilidad(new BloqueHorario("Lunes", LocalTime.of(10, 0), LocalTime.of(12, 0)));
        BloqueHorario solicitud = new BloqueHorario("1", LocalTime.of(10, 0), LocalTime.of(11, 0));
        assertTrue(t.tieneDisponibilidad(solicitud), "El tutor debe estar disponible mapeando el día 1 a Lunes");
    }

    @Test
    void tutorDisponibilidadLimitesInflexibles() {
        Tutor t = new Tutor("T1", "Enrique", "e@udec.cl", 1000);
        t.agregarDisponibilidad(new BloqueHorario("Lunes", LocalTime.of(10, 0), LocalTime.of(12, 0)));
        BloqueHorario solicitudFuera = new BloqueHorario("Lunes", LocalTime.of(9, 59), LocalTime.of(11, 0));
        BloqueHorario solicitudFuera2 = new BloqueHorario("Lunes", LocalTime.of(11, 0), LocalTime.of(12, 1));
        assertFalse(t.tieneDisponibilidad(solicitudFuera), "No debe permitir empezar antes de su horario");
        assertFalse(t.tieneDisponibilidad(solicitudFuera2), "No debe permitir terminar después de su horario");
    }

    @Test
    void modificarReservaGeneraChoqueConOtraReservaDelMismoTutor() {
        Estudiante e1 = new Estudiante("E1", "Estudiante 1", "e1@udec.cl");
        Estudiante e2 = new Estudiante("E2", "Estudiante 2", "e2@udec.cl");
        Tutor t1 = new Tutor("T1", "Tutor 1", "t1@udec.cl", 1000);
        Materia m = new Materia("Programacion", 10);
        t1.agregarMateria("Programacion", 10);
        t1.agregarDisponibilidad(new BloqueHorario("Lunes", LocalTime.of(10, 0), LocalTime.of(14, 0)));
        gestor.registrarEstudiante(e1);
        gestor.registrarEstudiante(e2);
        gestor.registrarTutor(t1);
        BloqueHorario b1 = new BloqueHorario("Lunes", LocalTime.of(10, 0), LocalTime.of(11, 0));
        BloqueHorario b2 = new BloqueHorario("Lunes", LocalTime.of(12, 0), LocalTime.of(13, 0));
        gestor.agendarReserva("R1", e1, t1, m, b1);
        gestor.agendarReserva("R2", e2, t1, m, b2);
        BloqueHorario nuevoChoque = new BloqueHorario("Lunes", LocalTime.of(10, 30), LocalTime.of(11, 30));
        assertThrows(IllegalStateException.class, () -> {
            gestor.modificarReserva("R2", nuevoChoque);
        });
    }

    /// Pruebas de controlador

    @Test
    void controladorNuevaReservaLanzaExcepcionSinTutor() {
        Estudiante e = new Estudiante("E1", "Estudiante 1", "e1@udec.cl");
        Materia m = new Materia("Calculo", 1);
        assertThrows(IllegalStateException.class, () -> {
            controlador.procesarNuevaReserva("R1", e, m, "Lunes", LocalTime.of(10,0), LocalTime.of(12,0));
        });
    }

    @Test
    void controladorNuevaReservaExitoActualizaModelo() {
        Estudiante e = new Estudiante("E1", "Estudiante 1", "e1@udec.cl");
        Tutor t = new Tutor("T1", "Tutor 1", "t1@udec.cl", 1000);
        Materia m = new Materia("Calculo", 1);

        t.agregarMateria("Calculo", 1);
        t.agregarDisponibilidad(new BloqueHorario("15", LocalTime.of(10, 0), LocalTime.of(12, 0)));

        gestor.registrarEstudiante(e);
        gestor.registrarTutor(t);
        seleccion.setTutorSeleccionado(t);

        assertTrue(controlador.procesarNuevaReserva("R1", e, m, "15", LocalTime.of(10, 0), LocalTime.of(11, 0)));
        assertEquals(1, gestor.getReservas().size(), "La reserva debió guardarse en el Gestor");
    }

    /// Pruebas de interfáz gráfica

    @Test
    void panelCalendarioPatronObserverActualizaColor() {
        Estudiante e = new Estudiante("E1", "Test UI", "testui@udec.cl");
        Tutor t = new Tutor("T1", "Tutor UI", "tutorui@udec.cl", 5000);
        Materia m = new Materia("Fisica", 5);
        t.agregarMateria("Fisica", 5);
        t.agregarDisponibilidad(new BloqueHorario("15", LocalTime.of(12, 0), LocalTime.of(14, 0)));
        gestor.registrarEstudiante(e);
        gestor.registrarTutor(t);
        PanelCalendario calendario = new PanelCalendario();
        JButton botonDia15 = encontrarBotonDia(calendario, "15");
        assertNotNull(botonDia15, "El botón del día 15 debe estar instanciado");
        assertNotEquals(Color.GREEN, botonDia15.getBackground());
        gestor.agendarReserva("R_UI", e, t, m, new BloqueHorario("15", LocalTime.of(12, 0), LocalTime.of(14, 0)));
        assertEquals(Color.GREEN, botonDia15.getBackground(), "El botón del día 15 debe pintarse de verde tras registrar la reserva");
    }

    @Test
    void panelesInicianSinExcepcionesConModeloVacio() {
        assertDoesNotThrow(() -> {
            new PanelControl();
            new PanelInfo();
            new PanelCalendario();
            new PanelPrincipal();
        }, "La interfaz debe poder inicializarse correctamente incluso si la base de datos (Listas) está vacía");
    }

    private JButton encontrarBotonDia(JPanel calendario, String textoDia) {
        for (Component c1 : calendario.getComponents()) {
            if (c1 instanceof JPanel) {
                for (Component c2 : ((JPanel) c1).getComponents()) {
                    if (c2 instanceof JButton && textoDia.equals(((JButton) c2).getText())) {
                        return (JButton) c2;
                    }
                }
            }
        }
        return null;
    }
}