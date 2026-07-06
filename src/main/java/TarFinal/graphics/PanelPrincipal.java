package TarFinal.graphics;

import javax.swing.*;
import java.awt.*;

/**
 * Módulo principal que orquesta he inicializa a los demás sub-paneles
 * @author Nicolas Silva
 * @author Daniel López
 * @version v2.1 - 06 de julio de 2026
 **/

public class PanelPrincipal extends JPanel {

    private PanelCalendario calendario;
    private PanelControl controles;
    private PanelInfo informacion;

    /**Constructor del panel principal*/
    public PanelPrincipal() {
        this.setLayout(null);

        this.calendario = new PanelCalendario();
        this.controles = new PanelControl();
        this.informacion = new PanelInfo();

        calendario.setBounds(0,0,800,600);
        controles.setBounds(1100,0,300,300);
        informacion.setBounds(800,0,300,300);

        this.add(calendario);
        this.add(controles);
        this.add(informacion);
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
