package TarFinal.graphics;

import TarFinal.logica.GestorReservas;
import TarFinal.logica.Reserva;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import TarFinal.logica.ObservadorReservas; // Agregado

/**Panel que representa el calendario y le agrega funcionalidad (no solo visual).
 * Implementa la interfaz ObservadorReservas para actualizar de forma dinámica su estado
 * visual cuando ocurren cambios.
 * @author Nicolas Silva
 * @author Daniel López
 * @version v2.2 - 6 de julio de 2026
 * */
public class PanelCalendario extends JPanel implements ObservadorReservas { // Agregado implements
    private JPanel panelFechas;
    private ArrayList<JButton> fechas;
    private JButton botonSeleccionado;

    /**
     * Constructor de la clase. Inicializa el diseño del panel, construye la cuadrícula de botones para el mes de junio
     * y registra el componente como un observador activo dentro del gestor de datos centralizado.
     */
    public PanelCalendario(){
        setLayout(null);
        this.fechas = new ArrayList<>();
        this.botonSeleccionado = null;
        panelFechas = new JPanel();
        panelFechas.setLayout(new GridLayout(5, 7, 0, 0));
        panelFechas.setBounds(50, 70, 700, 480);
        panelFechas.setOpaque(false);

        /// Registro de la vista en el modelo de datos para habilitar el patrón observer.
        GestorReservas.getInstancia().agregarObservador(this);

        String[] diasJunio = {
                "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
                "13", "14", "15", "16", "17", "18", "19",
                "20", "21", "22", "23", "24", "25", "26",
                "27", "28", "29", "30", "", "", "", "", ""
        };

        for (String dia : diasJunio) {
            if (dia.isEmpty()) {
                panelFechas.add(new JLabel());
            } else {
                JButton btnDia = new JButton(dia);
                btnDia.setOpaque(true);
                btnDia.setBorderPainted(true);
                btnDia.setForeground(Color.BLACK);
                btnDia.setFont(new Font("Arial", Font.BOLD, 16));

                btnDia.addActionListener(e -> {
                    actualizarColoresReservas();
                    btnDia.setBackground(Color.LIGHT_GRAY);
                    botonSeleccionado = btnDia;
                    TarFinal.controlador.GestorSeleccion.getInstancia().setDiaSeleccionado(dia);
                });

                panelFechas.add(btnDia);
                this.fechas.add(btnDia);
            }
        }
        add(panelFechas);

    }

    /**
     * Método de actualización definido por el contrato de la interfaz ObservadorReservas.
     * Se ejecuta automáticamente por el GestorReservas cuando se añade, modifica, o cancela una tutoría,
     * forzando el repintado inmediato de la interfaz.
     */
    @Override
    public void actualizar() {
        actualizarColoresReservas();
        repaint();
    }

    /**
     * Examina el estado del modelo para evaluar las reservas de cada día.
     * Asigna un fondo verde y un tooltip con los identificadores correspondientes
     * a los días que contengan registros, y deja a blanco los días vacíos.
     */
    public void actualizarColoresReservas() {
        for (JButton btn : fechas) {
            String dia = btn.getText();
            java.util.List<Reserva> reservasDelDia = new ArrayList<>();
            for (Reserva r : GestorReservas.getInstancia().getReservas()) {
                if (r.getHorario().getDiaSemana().equals(dia)) {
                    reservasDelDia.add(r);
                }
            }
            if (!reservasDelDia.isEmpty()) {
                btn.setBackground(Color.GREEN);
                StringBuilder tooltip = new StringBuilder("<html>Reservas:<br>");
                for (Reserva r : reservasDelDia) {
                    tooltip.append(r.getIdReserva()).append("<br>");
                }
                tooltip.append("</html>");
                btn.setToolTipText(tooltip.toString());
            } else {
                btn.setBackground(Color.WHITE);
                btn.setToolTipText(null);
            }
        }
    }

    /**
     * Método que controla el renderizado y el dibujo de las líneas de la cuadrícula, los textos
     * de los encabezados de los días de la semana y el fondo contenedor del calendario.
     * @param g El contexto gráfico utilizado para pintar sobre el componente.
     */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        actualizarColoresReservas();

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(30, 30, 740, 540);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(30, 30, 740, 540);
        g.setColor(Color.WHITE);
        g.fillRect(50, 50, 700, 500);
        g.setColor(Color.BLACK);
        g.drawRect(50, 50, 700, 500);

        for(int i=0; i < 6; i++) g.drawLine(50, 70+(96*i), 750, 70+(96*i));
        for(int i=0; i<8; i++) g.drawLine(50+(i*100), 50, 50+(i*100), 550);

        g.drawString("LUNES", 80, 65);
        g.drawString("MARTES", 175, 65);
        g.drawString("MIERCOLES", 265, 65);
        g.drawString("JUEVES", 375, 65);
        g.drawString("VIERNES", 470, 65);
        g.drawString("SABADO", 575, 65);
        g.drawString("DOMINGO", 670, 65);
        g.setColor(Color.BLACK);
        g.drawString("JUNIO 2026", 350, 25);
    }
}
