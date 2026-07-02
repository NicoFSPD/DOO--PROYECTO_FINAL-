package TarFinal.graphics;

import TarFinal.controlador.ProxyTutorActual;
import TarFinal.logica.GestorReservas;
import TarFinal.logica.Tutor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelTutores extends JPanel {

    private JPanel panelOpciones;

    private GestorReservas gestor;

    public PanelTutores(GestorReservas gestor){
        this.gestor = gestor;
        this.setLayout(null);

        this.panelOpciones = new JPanel(new GridLayout(5,1,5,5));

        panelOpciones.setBounds(60,60,150,150);

        ButtonGroup grupo = new ButtonGroup();

        for(Tutor t: gestor.getTutores()){
            String nombre = t.getNombre();
            JRadioButton boton = new JRadioButton(nombre);

            boton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ProxyTutorActual.getInstancia().setTutorSeleccionado(t);
                }
            });

            grupo.add(boton);
            panelOpciones.add(boton);
        }

        add(panelOpciones);
    }
}
