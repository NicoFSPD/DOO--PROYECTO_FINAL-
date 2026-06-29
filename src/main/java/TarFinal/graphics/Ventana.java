package TarFinal.graphics;

import javax.swing.*;

/**
 * Se hara reutilizacion de multiples features de la tarea 3 en lo que contempla a GUI
 * @author Nicolas Silva
 * @version v1.0 - 29 de Junio de 2026
 * */
public class Ventana extends JFrame {

    //CONSTRUCTOR PRINCIPAL DE VENTANA
    public Ventana(){
        super();
        this.setTitle("Maquina expendedora");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        this.add(panelPrincipal);
        this.setSize(1290, 755);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
