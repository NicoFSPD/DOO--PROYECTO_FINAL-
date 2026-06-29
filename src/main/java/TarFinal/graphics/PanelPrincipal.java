package TarFinal.graphics;

import TarFinal.logica.Administrador;
import TarFinal.logica.Estudiante;
import TarFinal.logica.GestorReservas;
import TarFinal.logica.Tutor;

import javax.swing.*;
import java.awt.*;

public class PanelPrincipal extends JPanel {

    private PanelTutores tutores;
    private PanelCalendario calendario;
    private PanelControl controles;
    private PanelHorarios horarios;

    private Estudiante modeloEst;
    private Tutor modeloTutor;
    private Administrador modeloAdmin;
    private GestorReservas gestor;

    public PanelPrincipal() {
        this.setBackground(Color.CYAN);
        this.setLayout(null);

        this.gestor = GestorReservas.getInstancia();
    }

    /**
     * Limpia la pantalla invocando a la superclase y deja que swing maneje
     * el dibujo de forma nativa.
     * @param g El objeto graphics utilizado para pintar en el componente.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

}
