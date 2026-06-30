package TarFinal.graphics;

import javax.swing.*;
import java.awt.*;

/**Panel de controles de gestion de reservas
 * @author Nicolas Silva
 * @version v1.0 - 29 de Junio de 2026*/

public class PanelControl extends JPanel {

    private JPanel panelAcciones;

    /**Constructor de la clase*/
    public PanelControl(){

        JButton btnReservar = new JButton("Crear reservacion");
        JButton btnModificar = new JButton("Modificar reservacion");
        JButton btnAnular = new JButton("Anular reservacion");

        this.panelAcciones = new JPanel();

        panelAcciones.setLayout(new GridLayout(3, 1, 5, 5));
        panelAcciones.setBounds(60, 60, 160, 100);
        panelAcciones.setBackground(Color.CYAN);
        panelAcciones.add(btnReservar);
        panelAcciones.add(btnModificar);
        panelAcciones.add(btnAnular);

        add(panelAcciones);

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        //Literalmente, el repintado del apartado de botones de esta seccion
        panelAcciones.setBounds(60, 60, 160, 100);
        panelAcciones.revalidate();
        panelAcciones.repaint();
    }


}
