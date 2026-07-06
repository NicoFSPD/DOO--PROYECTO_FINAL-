package TarFinal.graphics;

import TarFinal.logica.GestorReservas;
import TarFinal.logica.Tutor;

import javax.swing.*;
import java.awt.*;
/**
 * Panel de la pantalla que muestra la lista de los tutores disponibles.
 * Permite al administrador elegir a un profesor y guarda esa selección en el sistema.
 * @author Nicolas Silva
 * @author Daniel López
 * @version v1.0 - 5 de julio de 2026
 */
public class PanelTutores extends JPanel {
    private JPanel panelOpciones;
    private GestorReservas gestor;

    /**
     * Crea el panel, agrupa los botones para elegir solo un tutor a la vez y guarda la elección.
     * @param gestor El encargado de entregar la lista con los tutores registrados.
     */
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
