package TarFinal.graphics;

import TarFinal.controlador.ControladorReserva;
import TarFinal.controlador.GestorSeleccion;
import TarFinal.logica.Estudiante;
import TarFinal.logica.GestorReservas;
import TarFinal.logica.Materia;
import TarFinal.logica.Tutor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel de control para la gestión de reservas.
 * Actúa como la vista en la arquitectura del sistema, delegando toda la lógica de negocio
 * y las validaciones complejas al ControladorReserva (uso del patrón Mediator).
 * Captura las selecciones del usuario y las almacena temporalmente usando el GestorSeleccion.
 * @author Nicolas Silva
 * @author Daniel López
 * @version v1.4 - 6 de julio de 2026
 */
public class PanelControl extends JPanel {
    private JPanel panelAcciones;
    private ControladorReserva controlador;
    private JComboBox<String> comboEstudiantes;
    private JComboBox<String> comboMaterias;
    private JComboBox<String> comboTutores;
    private JComboBox<String> comboHorarios;

    /**
     * Constructor de la clase.
     * Inicializa los componentes de la interfaz de usuario, rellena los menús desplegables
     * extrayendo los datos actuales del modelo (GestorReservas) y configura los listeners
     * para actualizar el estado global de selección.
     */
    public PanelControl() {
        this.setLayout(null);
        this.controlador = new ControladorReserva();
        GestorReservas gestor = GestorReservas.getInstancia();

        this.panelAcciones = new JPanel();
        panelAcciones.setLayout(new GridLayout(7, 1, 5, 5));
        panelAcciones.setBounds(30, 35, 220, 240);
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

        comboTutores = new JComboBox<>();
        comboTutores.addItem("Seleccione Tutor...");
        for (Tutor t : gestor.getTutores()) {
            comboTutores.addItem(t.getNombre());
        }
        comboTutores.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboTutores.getSelectedIndex();
                if (index > 0) {
                    GestorSeleccion.getInstancia().setTutorSeleccionado(gestor.getTutores().get(index - 1));
                }
            }
        });

        comboMaterias = new JComboBox<>();
        comboMaterias.addItem("Seleccione Materia...");
        List<String> materiasUnicas = new ArrayList<>();
        for (Tutor t : gestor.getTutores()) {
            for (Materia m : t.getMaterias()) {
                if (!materiasUnicas.contains(m.getNombre())) {
                    materiasUnicas.add(m.getNombre());
                    comboMaterias.addItem(m.getNombre());
                }
            }
        }
        comboMaterias.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboMaterias.getSelectedIndex() > 0) {
                    Materia mat = new Materia(comboMaterias.getSelectedItem().toString(), 0);
                    GestorSeleccion.getInstancia().setMateriaSeleccionada(mat);
                }
            }
        });

        comboHorarios = new JComboBox<>();
        comboHorarios.addItem("Seleccione Horario...");
        comboHorarios.addItem("12:00 - 14:00");
        comboHorarios.addItem("15:00 - 17:00");
        comboHorarios.addItem("18:00 - 20:00");
        comboHorarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = comboHorarios.getSelectedIndex();
                if (index == 1) {
                    GestorSeleccion.getInstancia().setHoraInicio(LocalTime.of(12, 0));
                    GestorSeleccion.getInstancia().setHoraFin(LocalTime.of(14, 0));
                } else if (index == 2) {
                    GestorSeleccion.getInstancia().setHoraInicio(LocalTime.of(15, 0));
                    GestorSeleccion.getInstancia().setHoraFin(LocalTime.of(17, 0));
                } else if (index == 3) {
                    GestorSeleccion.getInstancia().setHoraInicio(LocalTime.of(18, 0));
                    GestorSeleccion.getInstancia().setHoraFin(LocalTime.of(20, 0));
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
        panelAcciones.add(comboTutores);
        panelAcciones.add(comboMaterias);
        panelAcciones.add(comboHorarios);
        panelAcciones.add(btnReservar);
        panelAcciones.add(btnModificar);
        panelAcciones.add(btnAnular);
        this.add(panelAcciones);
    }

    /**
     * Dibuja los textos descriptivos del panel.
     * @param g Objeto Graphics que provee el contexto de dibujo.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("SISTEMA DE RESERVAS:", 30, 20);
    }

    /**
     * Toma los datos seleccionados en la pantalla, revisa que no falte ninguno,
     * pide un ID al usuario y manda la información al controlador para crear la reserva.
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
                actualizarCalendario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error al Agendar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Pide el ID de una reserva existente y le aplica el nuevo día y horario
     * que el usuario marcó en la pantalla.
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
                actualizarCalendario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Pide el ID de una reserva y solicita su eliminación del sistema.
     */
    private void ejecutarAnularReserva() {
        String id = JOptionPane.showInputDialog(this, "Ingrese el ID de la reserva a anular:");
        if (id != null && !id.trim().isEmpty()) {
            try {
                controlador.anularReserva(id);
                JOptionPane.showMessageDialog(this, "Reserva anulada con exito.");
                actualizarCalendario();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Busca el PanelCalendario dentro de la ventana principal para obligarlo
     * a recalcular sus colores y redibujarse inmediatamente.
     */
    private void actualizarCalendario() {
        Container parent = this.getParent();
        if (parent instanceof TarFinal.graphics.PanelPrincipal) {
            for (Component c : parent.getComponents()) {
                if (c instanceof TarFinal.graphics.PanelCalendario) {
                    ((TarFinal.graphics.PanelCalendario) c).actualizarColoresReservas();
                    c.repaint();
                }
            }
        }
    }
}
