package TarFinal.graphics;

import javax.swing.*;

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
