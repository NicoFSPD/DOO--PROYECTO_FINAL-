package TarFinal.graphics;

import TarFinal.controlador.ControladorReserva;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**Panel de controles de gestion de reservas.
 * @author Nicolas Silva
 * @author Daniel López
 * @version v1.1 - 5 de Julio de 2026*/

public class PanelControl extends JPanel {

    private JPanel panelAcciones;
    private ControladorReserva controlador;

    ///Inicializa el panel, instancia el controlador y configura los botones de acción.
    public PanelControl() {
        this.setLayout(null);
        this.controlador = new ControladorReserva();

        JButton btnReservar = new JButton("Crear reservacion");
        JButton btnModificar = new JButton("Modificar reservacion");
        JButton btnAnular = new JButton("Anular reservacion");

        this.panelAcciones = new JPanel();
        panelAcciones.setLayout(new GridLayout(3, 1, 5, 5));
        panelAcciones.setBounds(60, 60, 160, 100);
        panelAcciones.setBackground(Color.CYAN);

        btnReservar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarCrearReserva();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarModificarReserva();
            }
        });

        btnAnular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarAnularReserva();
            }
        });

        panelAcciones.add(btnReservar);
        panelAcciones.add(btnModificar);
        panelAcciones.add(btnAnular);

        this.add(panelAcciones);
    }

    /**
     * Genera un cuadro de diálogo para solicitar el ID de una nueva reserva.
     */
    private void ejecutarCrearReserva() {
        String id = JOptionPane.showInputDialog(this, "Ingrese ID de la nueva reserva:");
        if (id != null && !id.trim().isEmpty()) {
            System.out.println("Procesando formulario para reserva ID: " + id);
        }
    }

    /**
     * Despliega un cuadro de diálogo para solicitar el ID de la reserva a modificar.
     */
    private void ejecutarModificarReserva() {
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la reserva a modificar:");
        if (id != null && !id.trim().isEmpty()) {
            System.out.println("Solicitando modificacion para reserva ID: " + id);
        }
    }

    /**
     * Solicita el ID mediante un cuadro de diálogo y delega la anulación al controlador.
     */
    private void ejecutarAnularReserva() {
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la reserva a anular:");
        if (id != null && !id.trim().isEmpty()) {
            boolean exito = controlador.anularReserva(id);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Reserva anulada con exito.");
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo anular la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
