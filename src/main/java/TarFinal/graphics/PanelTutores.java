package TarFinal.graphics;

import TarFinal.logica.GestorReservas;
import TarFinal.logica.Tutor;
import TarFinal.controlador.GestorSeleccion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel de la pantalla que muestra la lista de los tutores disponibles.
 * Permite al administrador elegir a un profesor y guarda esa selección en el sistema.
 * @author Nicolas Silva
 * @author Daniel López
 * @version v1.1 - 5 de julio de 2026
 */
public class PanelTutores extends JPanel {
    private JPanel panelOpciones;
    private GestorReservas gestor;
    private ButtonGroup grupoTutores;

    /**
     * Crea el panel, crea un grupo para que solo se pueda seleccionar un tutor a la vez,
     * y le añade a cada botón la acción de guardar el tutor seleccionado en el gestor.
     * @param gestor El encargado de entregar la lista con los tutores registrados.
     */
    public PanelTutores(GestorReservas gestor){
        this.gestor = gestor;
        this.panelOpciones = new JPanel(new GridLayout(5, 1, 5, 5));
        this.panelOpciones.setBounds(60, 60, 150, 150);
        this.grupoTutores = new ButtonGroup();

        for (Tutor t : gestor.getTutores()) {
            JRadioButton radioTutor = new JRadioButton(t.getNombre());
            this.grupoTutores.add(radioTutor);

            radioTutor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    GestorSeleccion.getInstancia().setTutorSeleccionado(t);
                }
            });
            this.panelOpciones.add(radioTutor);
        }
        this.add(panelOpciones);
    }
}
