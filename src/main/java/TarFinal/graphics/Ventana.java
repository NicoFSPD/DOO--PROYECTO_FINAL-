package TarFinal.graphics;

import javax.swing.*;

/**
 * Clase encargada de desplegar la ventana para el funcionamiento de la aplicación.
 * @author Nicolas Silva
 * @version v1.0 - 29 de junio de 2026
 * */
public class Ventana extends JFrame {

    /// CONSTRUCTOR PRINCIPAL DE VENTANA
    public Ventana(){
        super();
        this.setTitle("SISTEMA DE RESERVAS");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PanelPrincipal panelPrincipal = new PanelPrincipal();
        this.add(panelPrincipal);
        this.setSize(1400, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
