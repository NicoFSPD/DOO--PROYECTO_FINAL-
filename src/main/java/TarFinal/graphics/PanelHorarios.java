package TarFinal.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**Panel de los horarios a decision del usuario para agendar
 * @author Nicolas Silva
 * @author Daniel López
 * @version v1.1 - 1 de Julio de 2026*/
public class PanelHorarios extends JPanel{


    private JPanel panelBloques;
    private ArrayList<JRadioButton> horas;

    /**Constructor de la clase*/
    public PanelHorarios(){
        setLayout(null);

        this.panelBloques = new JPanel();

        this.panelBloques.setLayout(new GridLayout(3,1));
        this.panelBloques.setBounds(60,60,180,180);

        this.horas = new ArrayList<>();

        //CREACION DE LOS BOTONES DE LOS HORARIOS EXISTENTES
        JRadioButton b1 = new JRadioButton("12:00 - 14:00");
        JRadioButton b2 = new JRadioButton("15:00 - 17:00");
        JRadioButton b3 = new JRadioButton("18:00 - 20:00");

        b1.addActionListener(e -> {
            TarFinal.controlador.GestorSeleccion.getInstancia().setHoraInicio(java.time.LocalTime.of(12, 0));
            TarFinal.controlador.GestorSeleccion.getInstancia().setHoraFin(java.time.LocalTime.of(14, 0));
        });

        b2.addActionListener(e -> {
            TarFinal.controlador.GestorSeleccion.getInstancia().setHoraInicio(java.time.LocalTime.of(15, 0));
            TarFinal.controlador.GestorSeleccion.getInstancia().setHoraFin(java.time.LocalTime.of(17, 0));
        });

        b3.addActionListener(e -> {
            TarFinal.controlador.GestorSeleccion.getInstancia().setHoraInicio(java.time.LocalTime.of(18, 0));
            TarFinal.controlador.GestorSeleccion.getInstancia().setHoraFin(java.time.LocalTime.of(20, 0));
        });

        //Agruparlos para seleccion tipo XOR
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(b1);
        grupo.add(b2);
        grupo.add(b3);

        panelBloques.add(b1);
        panelBloques.add(b2);
        panelBloques.add(b3);
        horas.add(b1);
        horas.add(b2);
        horas.add(b3);

        this.add(panelBloques);
    }

    public ArrayList<JRadioButton> getHoras() {
        return horas;
    }

    /**Metodo paintComponent() para la clase*/
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.setColor(Color.DARK_GRAY);
        g.drawString("HORARIOS:",30,30);
    }
}
