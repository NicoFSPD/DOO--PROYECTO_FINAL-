package TarFinal.graphics;

import TarFinal.controlador.ControladorReserva;
import TarFinal.controlador.GestorSeleccion;
import TarFinal.logica.Estudiante;
import TarFinal.logica.GestorReservas;
import TarFinal.logica.Materia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel de controles de gestión de reservas.
 * Contiene los botones de acción principales y los menús desplegables para elegir estudiantes y materias.
 * @author Nicolas Silva
 * @author Daniel López
 * @version v1.3 - 6 de julio de 2026
 */
public class PanelControl extends JPanel {

    private JPanel panelAcciones;
    private ControladorReserva controlador;
    private JComboBox<String> comboEstudiantes;
    private JComboBox<String> comboMaterias;

    /**
     * Inicializa el panel, configura el diseño y añade los componentes de selección junto con los botones de acción.
     */
    public PanelControl() {
        this.setLayout(null);
        this.controlador = new ControladorReserva();
        GestorReservas gestor = GestorReservas.getInstancia();

        this.panelAcciones = new JPanel();
        panelAcciones.setLayout(new GridLayout(5, 1, 5, 5));
        panelAcciones.setBounds(30, 30, 220, 180);
        panelAcciones.setBackground(Color.CYAN);

        comboEstudiantes = new JComboBox<>();
        comboEstudiantes.addItem("Seleccione Estudiante...");
        for (Estudiante e : gestor.getEstudiantes()) {
            comboEstudiantes.addItem(e.getNombre());
        }

        comboEstudiantes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboEstudiantes.getSelectedIndex();
                if (index > 0) {
                    GestorSeleccion.getInstancia().setEstudianteSeleccionado(gestor.getEstudiantes().get(index - 1));
                }
            }
        });

        comboMaterias = new JComboBox<>();
        comboMaterias.addItem("Seleccione Materia...");
        comboMaterias.addItem("Calculo II");
        comboMaterias.addItem("Programacion Java");

        comboMaterias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboMaterias.getSelectedIndex() > 0) {
                    Materia mat = new Materia(comboMaterias.getSelectedItem().toString(), 5);
                    GestorSeleccion.getInstancia().setMateriaSeleccionada(mat);
                }
            }
        });

        JButton btnReservar = new JButton("Crear reservacion");
        JButton btnModificar = new JButton("Modificar reservacion");
        JButton btnAnular = new JButton("Anular reservacion");

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

        panelAcciones.add(comboEstudiantes);
        panelAcciones.add(comboMaterias);
        panelAcciones.add(btnReservar);
        panelAcciones.add(btnModificar);
        panelAcciones.add(btnAnular);

        this.add(panelAcciones);
    }

    /**
     * Solicita un identificador único y procesa la creación de la reserva en el sistema.
     * Valida de manera preventiva que existan selecciones activas en la interfaz y captura errores lógicos mediante ventanas emergentes.
     */
    private void ejecutarCrearReserva() {
        GestorSeleccion proxy = GestorSeleccion.getInstancia();

        if (!proxy.hayTutorSeleccionado() || !proxy.hayDiaSeleccionado() || !proxy.hayHorarioSeleccionado() || !proxy.hayEstudianteSeleccionado() || !proxy.hayMateriaSeleccionada()) {
            JOptionPane.showMessageDialog(this, "Faltan datos por seleccionar en la interfaz.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String id = JOptionPane.showInputDialog(this, "Ingrese ID de la nueva reserva:");
        if (id != null && !id.trim().isEmpty()) {
            try {
                controlador.procesarNuevaReserva(id, proxy.getEstudianteSeleccionado(), proxy.getMateriaSeleccionada(), proxy.getDiaSeleccionado(), proxy.getHoraInicio(), proxy.getHoraFin());
                JOptionPane.showMessageDialog(this, "Reserva creada con exito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al Agendar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Solicita el identificador de una reserva existente y modifica su bloque horario.
     * Requiere que el nuevo día y rango de horas se encuentren previamente marcados en la pantalla.
     */
    private void ejecutarModificarReserva() {
        GestorSeleccion proxy = GestorSeleccion.getInstancia();
        if (!proxy.hayDiaSeleccionado() || !proxy.hayHorarioSeleccionado()) {
            JOptionPane.showMessageDialog(this, "Seleccione el nuevo dia y horario primero.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la reserva a modificar:");
        if (id != null && !id.trim().isEmpty()) {
            try {
                controlador.modificarReserva(id, proxy.getDiaSeleccionado(), proxy.getHoraInicio(), proxy.getHoraFin());
                JOptionPane.showMessageDialog(this, "Reserva modificada con exito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Solicita el identificador de una reserva y delega la eliminación del registro al controlador central.
     * Despliega una alerta informativa si la operación concluye correctamente o si el id no es válido.
     */
    private void ejecutarAnularReserva() {
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la reserva a anular:");
        if (id != null && !id.trim().isEmpty()) {
            try {
                controlador.anularReserva(id);
                JOptionPane.showMessageDialog(this, "Reserva anulada con exito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
