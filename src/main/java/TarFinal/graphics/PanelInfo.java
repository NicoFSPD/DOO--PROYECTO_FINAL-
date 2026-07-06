package TarFinal.graphics;

import javax.swing.*;
import java.awt.*;

/**Panel para mostrar informacion al usuario en lo que interactua con el programa
 * @author Nicolas Silva
 * @author Daniel López
 * @version v1.1 - 05 de Julio de 2026
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


/// Constructor de la clase
    public PanelInfo(){
        this.contenedor = new JPanel();
        setLayout(null);

        cartel = new CardLayout();
        contenedor.setBounds(10,10,280,280);

        /// Cartel que aparece en la primera interaccion con el programa
        JPanel cartelBienvenida = new JPanel();
        cartelBienvenida.add(new JLabel("Selecciona un dia para comenzar:"));

        /// Cartel de dia seleccionado
        JPanel cartelDia = new JPanel();
        etiquetaDia = new JLabel();
        cartelDia.add(etiquetaDia);

        /// Cartel para tutor seleccionado
        JPanel cartelTutor = new JPanel();
        etiquetaTutor = new JLabel();
        cartelTutor.add(etiquetaTutor);

        /// Cartel para horario seleccionado
        JPanel cartelHorario = new JPanel();


        /// Texto de confirmacion de reserva creada
        JPanel cartelReserva = new JPanel();
        cartelReserva.add(new JLabel("Reserva creada exitosamente!"));

        /// reserva anulada
        JPanel cartelAnulacion = new JPanel();
        cartelAnulacion.add(new JLabel("La reserva ha sido anulada"));

        /// Agregar al contenedor

        contenedor.add(cartelBienvenida,bienvenida);
        contenedor.add(cartelDia, diaSeleccionado);
        contenedor.add(cartelTutor, tutorSeleccionado);
        contenedor.add(cartelReserva, reservaCreada);
        contenedor.add(cartelAnulacion, reservaAnulada);

        add(contenedor);
    }


    /// ### METODOS PARA MOSTRAR MENSAJES DE: ###


    /// Bienvenida
    public void mostrarBienvenida(){
        cartel.show(contenedor,bienvenida);
    }

    /// Dia seleccionado
    public void mostrarDiaSeleccionado(String dia){
        etiquetaDia.setText("Dia seleccionado: "+ dia);
        cartel.show(contenedor, diaSeleccionado);
    }

    /// Tutor seleccionado
    public void mostrarTutorSeleccionado(String nombre){
        etiquetaTutor.setText("Tutor seleccionado:" + nombre);
        cartel.show(contenedor, tutorSeleccionado);
    }

    /// Reserva hecha
    public void mostrarReservaCreada(){
        cartel.show(contenedor, reservaCreada);
    }

    /// Reserva anulada
    public void mostrarReservaAnulada(){
        cartel.show(contenedor, reservaAnulada);
    }
}
