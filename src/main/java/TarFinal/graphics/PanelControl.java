package TarFinal.graphics;

import TarFinal.controlador.ControladorReserva;
import TarFinal.logica.Estudiante;
import TarFinal.logica.Materia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;

/**Panel de controles de gestion de reservas
 * @author Daniel López
 * @author Nicolas Silva
 * @version v1.2 - 01 de Julio de 2026*/

public class PanelControl extends JPanel {

    private JPanel panelAcciones;
    private ControladorReserva controlador;

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
     * Método encargado de solicitar los datos por pantalla para agendar una nueva reserva.
     * Instancia los objetos temporales y los envía al controlador.
     */
    private void ejecutarCrearReserva() {
        String id = JOptionPane.showInputDialog(this, "Ingrese ID de la nueva reserva:");
        if (id != null && !id.trim().isEmpty()) {

            ///Solicitud de datos del estudiante y materia.
            String idEstudiante = JOptionPane.showInputDialog(this, "Ingrese ID del estudiante:");
            String nombreEstudiante = JOptionPane.showInputDialog(this, "Ingrese nombre del estudiante:");
            String correoEstudiante = JOptionPane.showInputDialog(this, "Ingrese correo del estudiante:");
            Estudiante estudiante = new Estudiante(idEstudiante, nombreEstudiante, correoEstudiante);

            String nombreMateria = JOptionPane.showInputDialog(this, "Ingrese nombre de la materia:");
            Materia materia = new Materia(nombreMateria, 1);

            ///Solicitud de datos del bloque horario.
            String diaSemana = JOptionPane.showInputDialog(this, "Ingrese el dia de la semana:");
            String horaInicioStr = JOptionPane.showInputDialog(this, "Ingrese hora de inicio (HH:MM):");
            String horaFinStr = JOptionPane.showInputDialog(this, "Ingrese hora de fin (HH:MM):");

            try {
                ///Conversión de formato de horas.
                LocalTime horaInicio = LocalTime.parse(horaInicioStr);
                LocalTime horaFin = LocalTime.parse(horaFinStr);

                boolean exito = controlador.procesarNuevaReserva(id, estudiante, materia, diaSemana, horaInicio, horaFin);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Reserva creada con exito.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo agendar la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Formato de hora invalido. Use HH:MM", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método encargado de solicitar los nuevos datos de horario por pantalla.
     * Envía la información validada al controlador para modificar una reserva existente.
     */
    private void ejecutarModificarReserva() {
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la reserva a modificar:");
        if (id != null && !id.trim().isEmpty()) {

            ///Solicitud de los nuevos parámetros de tiempo.
            String nuevoDia = JOptionPane.showInputDialog(this, "Ingrese el nuevo dia:");
            String nuevaHoraInicioStr = JOptionPane.showInputDialog(this, "Ingrese nueva hora de inicio (HH:MM):");
            String nuevaHoraFinStr = JOptionPane.showInputDialog(this, "Ingrese nueva hora de fin (HH:MM):");

            try {
                LocalTime nuevaHoraInicio = LocalTime.parse(nuevaHoraInicioStr);
                LocalTime nuevaHoraFin = LocalTime.parse(nuevaHoraFinStr);

                boolean exito = controlador.modificarReserva(id, nuevoDia, nuevaHoraInicio, nuevaHoraFin);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Reserva modificada con exito.");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo modificar la reserva.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Formato de hora invalido. Use HH:MM", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Método que solicita el ID de una reserva por pantalla para anularla a través del controlador.
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