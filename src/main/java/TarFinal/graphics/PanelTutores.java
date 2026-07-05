package TarFinal.graphics;

import TarFinal.logica.GestorReservas;
import TarFinal.logica.Tutor;

import javax.swing.*;
import java.awt.*;

public class PanelTutores extends JPanel {

    private JPanel panelOpciones;

    private GestorReservas gestor;

    public PanelTutores(GestorReservas gestor){
        this.gestor = gestor;

        this.panelOpciones = new JPanel(new GridLayout(5,1,5,5));

        panelOpciones.setBounds(60,60,150,150);

        for(Tutor t: gestor.getTutores()){
            String nombre = t.getNombre();
            panelOpciones.add(new JRadioButton(nombre));
        }

        add(panelOpciones);
    }
}
