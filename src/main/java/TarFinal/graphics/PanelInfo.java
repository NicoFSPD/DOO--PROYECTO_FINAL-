package TarFinal.graphics;

import TarFinal.logica.BloqueHorario;
import TarFinal.logica.Estudiante;
import TarFinal.logica.GestorReservas;
import TarFinal.logica.Materia;
import TarFinal.logica.Reserva;
import TarFinal.logica.Tutor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panel para mostrar información al usuario en lo que interactúa con el programa.
 * En esta información estarían los datos de los tutores, los estudiantes, y las reservas.
 * @author Nicolas Silva
 * @author Daniel López
 * @version v1.2 - 06 de julio de 2026
 **/
public class PanelInfo extends JPanel {
    private CardLayout cartel;
    private JPanel contenedor;
    public static final String bienvenida = "bienvenida";
    public static final String diaSeleccionado = "dia";
    public static final String tutorSeleccionado = "tutor";
    public static final String horarioSeleccionado = "horario";
    public static final String reservaCreada = "reservaCreada";
    public static final String reservaAnulada = "reservaAnulada";

    private JLabel etiquetaDia;
    private JLabel etiquetaTutor;
    private JComboBox<String> comboTutoresInfo;
    private JComboBox<String> comboEstudiantesInfo;

    /**
     * Constructor del panel de información.
     * Diseña visualmente la zona de datos y llena las listas desplegables
     * con los nombres de los profesores y alumnos registrados.
     */
    public PanelInfo(){
        this.contenedor = new JPanel();
        cartel = new CardLayout();
        this.contenedor.setLayout(cartel);
        setLayout(null);

        contenedor.setBounds(30, 35, 220, 240);
        contenedor.setOpaque(true);

        JPanel cartelBienvenida = new JPanel();
        cartelBienvenida.setLayout(null);
        cartelBienvenida.setOpaque(true);

        JPanel wrapBienvenida = new JPanel(new GridLayout(3, 1, 5, 5));
        wrapBienvenida.setBackground(Color.CYAN);
        wrapBienvenida.setBounds(0, 0, 220, 100);
        wrapBienvenida.setOpaque(true);

        comboTutoresInfo = new JComboBox<>();
        comboTutoresInfo.addItem("Info Tutores...");

        comboEstudiantesInfo = new JComboBox<>();
        comboEstudiantesInfo.addItem("Info Estudiantes...");

        GestorReservas gestor = GestorReservas.getInstancia();
        for (Tutor t : gestor.getTutores()) {
            comboTutoresInfo.addItem(t.getNombre());
        }
        for (Estudiante e : gestor.getEstudiantes()) {
            comboEstudiantesInfo.addItem(e.getNombre());
        }

        comboTutoresInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboTutoresInfo.getSelectedIndex() > 0) {
                    mostrarInfoTutor((String) comboTutoresInfo.getSelectedItem());
                    comboTutoresInfo.setSelectedIndex(0);
                }
            }
        });

        comboEstudiantesInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboEstudiantesInfo.getSelectedIndex() > 0) {
                    mostrarInfoEstudiante((String) comboEstudiantesInfo.getSelectedItem());
                    comboEstudiantesInfo.setSelectedIndex(0);
                }
            }
        });

        JButton btnVerReservas = new JButton("Ver todas las reservas");
        btnVerReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTodasLasReservas();
            }
        });

        wrapBienvenida.add(comboTutoresInfo);
        wrapBienvenida.add(comboEstudiantesInfo);
        wrapBienvenida.add(btnVerReservas);

        cartelBienvenida.add(wrapBienvenida);

        JPanel cartelDia = new JPanel();
        etiquetaDia = new JLabel();
        cartelDia.add(etiquetaDia);

        JPanel cartelTutor = new JPanel();
        etiquetaTutor = new JLabel();
        cartelTutor.add(etiquetaTutor);

        JPanel cartelHorario = new JPanel();

        JPanel cartelReserva = new JPanel();
        cartelReserva.add(new JLabel("Reserva creada exitosamente!"));

        JPanel cartelAnulacion = new JPanel();
        cartelAnulacion.add(new JLabel("La reserva ha sido anulada"));

        contenedor.add(cartelBienvenida, bienvenida);
        contenedor.add(cartelDia, diaSeleccionado);
        contenedor.add(cartelTutor, tutorSeleccionado);
        contenedor.add(cartelHorario, horarioSeleccionado);
        contenedor.add(cartelReserva, reservaCreada);
        contenedor.add(cartelAnulacion, reservaAnulada);

        add(contenedor);
    }

    /**
     * Busca todas las reservas en el sistema y las muestra ordenadas
     * dentro de una ventana emergente con una barra para scrollear hacia abajo.
     */
    private void mostrarTodasLasReservas() {
        GestorReservas gestor = GestorReservas.getInstancia();
        if (gestor.getReservas().isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay reservas registradas en el sistema actualmente.", "Info Reservas", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder info = new StringBuilder();
        info.append("=== CLASES AGENDADAS ===\n\n");
        for (Reserva r : gestor.getReservas()) {
            info.append("ID Reserva: ").append(r.getIdReserva()).append("\n");
            info.append("Tutor: ").append(r.getTutor().getNombre()).append("\n");
            info.append("Estudiante: ").append(r.getEstudiante().getNombre()).append("\n");
            info.append("Materia: ").append(r.getMateria().getNombre()).append("\n");
            info.append("Horario: Día ").append(r.getHorario().getDiaSemana())
                    .append(" de ").append(r.getHorario().getHoraInicio())
                    .append(" a ").append(r.getHorario().getHoraFin()).append("\n");
            info.append("------------------------------------------\n");
        }

        JTextArea areaTexto = new JTextArea(info.toString());
        areaTexto.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setPreferredSize(new Dimension(350, 250));

        JOptionPane.showMessageDialog(this, scroll, "Registro de Reservas", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Busca a un tutor por su nombre y muestra una ventana emergente
     * con su ID, correo, tarifa, materias y los horarios que tiene disponibles.
     */
    private void mostrarInfoTutor(String nombreSeleccionado) {
        GestorReservas gestor = GestorReservas.getInstancia();
        StringBuilder info = new StringBuilder();

        for (Tutor t : gestor.getTutores()) {
            if (nombreSeleccionado.equals(t.getNombre())) {
                info.append("=== INFORMACIÓN DEL PROFESOR ===\n");
                info.append("ID de Sistema: ").append(t.getId()).append("\n");
                info.append("Nombre: ").append(t.getNombre()).append("\n");
                info.append("Correo: ").append(t.getCorreo()).append("\n");
                info.append("Tarifa: $").append(t.getTarifaPorHora()).append(" por hora\n\n");

                info.append("Materias que imparte:\n");
                for (Materia m : t.getMaterias()) {
                    info.append("- ").append(m.getNombre()).append(" (Cupo máximo: ").append(m.getCupoMaximo()).append(")\n");
                }

                info.append("\nDisponibilidad de Horarios (Día | Horas):\n");
                for (BloqueHorario b : t.getDisponibilidad()) {
                    info.append("- Día ").append(b.getDiaSemana()).append(" | ").append(b.getHoraInicio()).append(" a ").append(b.getHoraFin()).append("\n");
                }

                JOptionPane.showMessageDialog(this, info.toString(), "Detalles del Profesor", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

    /**
     * Busca a un alumno por su nombre y muestra una ventana emergente
     * con sus datos personales básicos (ID, nombre y correo).
     */
    private void mostrarInfoEstudiante(String nombreSeleccionado) {
        GestorReservas gestor = GestorReservas.getInstancia();
        StringBuilder info = new StringBuilder();

        for (Estudiante est : gestor.getEstudiantes()) {
            if (nombreSeleccionado.equals(est.getNombre())) {
                info.append("=== INFORMACIÓN DEL ESTUDIANTE ===\n");
                info.append("ID de Sistema: ").append(est.getId()).append("\n");
                info.append("Nombre: ").append(est.getNombre()).append("\n");
                info.append("Correo Electrónico: ").append(est.getCorreo()).append("\n");

                JOptionPane.showMessageDialog(this, info.toString(), "Detalles del Estudiante", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
    }

    /**
     * Dibuja el título de la sección en la parte superior del panel.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.DARK_GRAY);
        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("INFO. USUARIOS/RESERVAS:", 30, 20);
    }
}
