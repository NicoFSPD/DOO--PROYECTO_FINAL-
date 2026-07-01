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
    private PanelInfo informacion;

    private Estudiante modeloEst;
    private Tutor modeloTutor;
    private Administrador modeloAdmin;
    private GestorReservas gestor;

    public PanelPrincipal() {
        this.setBackground(Color.CYAN);
        this.setLayout(null);

        this.gestor = GestorReservas.getInstancia();

        this.calendario = new PanelCalendario();
        this.tutores = new PanelTutores(gestor);
        this.horarios = new PanelHorarios();
        this.controles = new PanelControl();
        this.informacion = new PanelInfo();

        calendario.setBounds(0,0,800,600);
        tutores.setBounds(800,300,300,300);
        controles.setBounds(1100,0,300,300);
        horarios.setBounds(1100,300,300,300);
        informacion.setBounds(800,0,300,300);

        this.add(calendario);
        this.add(tutores);
        this.add(controles);
        this.add(horarios);
        this.add(informacion);
    }

    private void add(PanelHorarios horarios) {
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
